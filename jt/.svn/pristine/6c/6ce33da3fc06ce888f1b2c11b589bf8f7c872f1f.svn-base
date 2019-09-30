package com.jt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 该注解主要实现缓存查询
 * @author BoYuan
 *操作规范:
 *key:
 *1.用户没有赋值, 有默认值
 *如果将来的key为空串,标识用户使用自动生成的key
 *包名.类名.方法名 拼接第一个参数
 *2.如果用户赋值,
 *key'使用用户的数据
 *
 *seconds:如果时间不为零 ,标识用户需要设定超时时间
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequiredCacheSelect {
	String   key() default "";
	int seconds() default 0;
	//Class<?>  target() ; 
}
