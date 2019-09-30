package com.jt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jt.annotation.RequiredCacheSelect;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

@Aspect
@Component
public class CacheAspect {
	@Autowired(required = false)
	private JedisCluster jedis;
	/**
	 * 利用aop的规则,动态的获取注解对象
	 * 1.根据key查询redis 
	 * 2,如果没有数据,需要让目标方法执行,将插叙你的结果放在redis中
	 * 3.如果有数据,直接将json串转化为返回值对象返回
	 * @param joinPoint
	 * @param cacheSelect
	 * @return
	 * @throws Throwable
	 */
	//@Around("@annotation(com.jt.annnotation.RequiredCacheSelect)") 都是弟弟
	@Around("@annotation(cacheSelect)")
	public Object cacheAround(ProceedingJoinPoint joinPoint ,RequiredCacheSelect cacheSelect){
		//System.out.println("aop生效了");
		String key = getKey(joinPoint,cacheSelect);
		String  dataJSON= jedis.get(key);
		Object result= null;
		try {
			if(StringUtils.isEmpty(dataJSON)) {
				result = joinPoint.proceed();
				dataJSON = ObjectMapperUtil.toJSON(result);
				//判断用户是否编辑时间 如果有时间则必须设定超时时间
				if(cacheSelect.seconds() > 0)
					jedis.setex(key, cacheSelect.seconds(), dataJSON);
				else
					jedis.set(key, dataJSON);
			}else {
				Class<?> returnClass = getReturn(joinPoint);
				result =ObjectMapperUtil.toObject(dataJSON, returnClass);
			}
			return result;
		}catch(Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	} 
	/**
	 * 获取目标方法的返回值类型
	 * MethodSignature可以获取方法的返回值类型
	 * @param joinPoint
	 * @return
	 */
	private Class<?> getReturn(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		return signature.getReturnType();
	}
	/**
	 * 动态获取key
	 * @param joinPoint
	 * @param cacheSelect
	 * @return
	 */
	private String getKey(ProceedingJoinPoint joinPoint, RequiredCacheSelect cacheSelect) {
		String key = cacheSelect.key();
		if(StringUtils.isEmpty(key)) {
			//key自动生成
			//Object objt = joinPoint.getTarget();
			//System.out.println(objt);
			String className = joinPoint.getSignature().getDeclaringTypeName(); //获取当前目标对象的类的全名称
			String methodName = joinPoint.getSignature().getName(); // 具体要执行的方法的名字
			if(joinPoint.getArgs().length == 0)
				key = className+"."+methodName;
			else
				key = className+"." + methodName+"::"+joinPoint.getArgs()[0];				
		}
		return key;
	}
}
