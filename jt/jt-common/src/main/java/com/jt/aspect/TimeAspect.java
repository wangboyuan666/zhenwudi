package com.jt.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TimeAspect {

	@Around("within(com.jt.controller.*)")
	public Object timeAround(ProceedingJoinPoint joinPoint) {
		//System.out.println("around"+Thread.currentThread());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println("这个aop执行了");
		Object result = null;
		String methodName = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();

		long start = System.currentTimeMillis();
		try {
			result = joinPoint.proceed();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		long spendTime = System.currentTimeMillis()-start;
		log.info("花费时间:"+spendTime);
		new Thread() {
			public void run() {
				//System.out.println(Thread.currentThread());
				FileWriter out = null;
				try {
					out = new FileWriter("F:\\jt\\methodTime.txt",true);
					out.write(methodName+":-->花费时间:"+spendTime+"ms,-->执行时间:"+sdf.format(new Date())+"\t\n");
					out.flush();
				} catch (Throwable e) {
					e.printStackTrace(); 
					throw new RuntimeException(e);
				}finally {
					try {
						if(out != null)
							out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		return result;
	}
}
