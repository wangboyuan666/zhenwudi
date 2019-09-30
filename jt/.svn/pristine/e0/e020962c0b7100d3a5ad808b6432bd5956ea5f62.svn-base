package com.jt.aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;


@Component
@RestControllerAdvice //controller层切面的定义
@Slf4j
public class ExceptionAspect {
	@ExceptionHandler(value = RuntimeException.class)
	public SysResult result(Exception e){
		e.printStackTrace(); // 为程序员调错方便
		log.error(e.getMessage());
		return SysResult.fail();	
	}
}
