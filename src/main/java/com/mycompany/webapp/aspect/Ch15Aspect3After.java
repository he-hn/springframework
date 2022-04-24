package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect3After {
	@After("execution(public * com.mycompany.webapp.controller.Ch15Controller.after*(..))")
	public void method() {
		log.info("실행");
	}
}
