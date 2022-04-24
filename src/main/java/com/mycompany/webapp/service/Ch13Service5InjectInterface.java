package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13DaoI;
import com.mycompany.webapp.dao.Ch13DaoImpl1;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service5InjectInterface {
	public Ch13Service5InjectInterface() {
		log.info("실행");
	}
	
	@Autowired @Qualifier("ch13DaoImpl1")
	private Ch13DaoI ch13DaoI1;
	
	@Resource(name="ch13DaoImpl2") 
	private Ch13DaoI ch13DaoI2;
	
	@Autowired @Qualifier("ch13DaoImpl1")
	private Ch13DaoImpl1 ch13DaoImpl1;
	
	@Autowired
	public void setCh13DaoI1(@Qualifier("ch13DaoImpl1") Ch13DaoI ch13DaoI1) {
		log.info("실행");
		this.ch13DaoI1 = ch13DaoI1;
	}
	
	@Resource(name="ch13DaoImpl2")
	public void setCh13DaoI2(Ch13DaoI ch1DaoI2) {
		log.info("실행");
		this.ch13DaoI2 = ch1DaoI2;
	}
	
	@Autowired
	public void setCh13DaoImpl1(@Qualifier("ch13DaoImpl1") Ch13DaoImpl1 ch13DaoImpl1) {
		log.info("실행: setCh13DaoImpl1");
		this.ch13DaoImpl1 = ch13DaoImpl1;
	}
}
