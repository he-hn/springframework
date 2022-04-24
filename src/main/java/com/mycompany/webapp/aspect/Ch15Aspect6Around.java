package com.mycompany.webapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect6Around {
	@Around("execution(public * com.mycompany.webapp.controller.Ch15Controller.around*(..))")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리(핵심 코드 이전에 실행할 코드)
		log.info("전처리 실행");
		
		Object result = joinPoint.proceed();
		
		//후처리(핵심 코드 이후에 실행할 코드)
		log.info("후처리 실행");
		
		return result;
	}
}
