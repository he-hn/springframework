package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service //기본적으로 기본생성자로 객체를 만든다. 기본생성자가 있으면 기본 생성자만 실행된다.
@Log4j2
public class Ch13Service8InjectPropByAnno {
	@Value("${service.prop1}") private int prop1; //필드 주입
	private double prop2;
	private boolean prop3;
	private String prop4;
	
	/*public Ch13Service8InjectPropByAnno() {
		log.info("실행1: " + toString());
	}*/
	
	public Ch13Service8InjectPropByAnno( //생성자 주입
			@Value("${service.prop2}") double prop2, 
			@Value("${service.prop3}") boolean prop3) { 
		this.prop2 = prop2;
		this.prop3 = prop3;
		log.info("실행2: " + toString());
	}
	
	@Value("${service.prop4}")
	public void setProp4(String prop4) { //세터 주입
		this.prop4 = prop4;
		log.info("실행: " + toString());
	}
	
	@Override
	public String toString() {
		String str = "{";
		str += "pro1:" + prop1 +", ";
		str += "pro2:" + prop2 +", ";
		str += "pro3:" + prop3 +", ";
		str += "pro4:" + prop4;
		str += "}";
		return str;
	}

}
