package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect5AfterThrowing {
	@AfterThrowing(
		pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterThrowing*(..))",
		throwing="e" 
	)
	public void  method(Throwable e) {
		log.info("실행");
		log.info("예외 메시지: " + e.getMessage());
	}
}
