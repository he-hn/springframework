package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class Ch13DaoImpl1 implements Ch13DaoI{
	public Ch13DaoImpl1() {
		log.info("실행");
	}
}
