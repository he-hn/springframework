package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect4AfterReturning {
	@AfterReturning(
		pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))", //afterReturning이 예외 없이 실행된다면 return 값울 받아야 한다
		returning="returnValue" //afterReturning의 return 값이 들어가고, 이 값은 다시 method의 매개변수로 들어간다
	)
	public void method(String returnValue) {
		log.info("실행");
		log.info("리턴값: " + returnValue);
	}
}
