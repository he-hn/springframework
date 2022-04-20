package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class Ch13DaoImpl2 implements Ch13DaoI{
	public Ch13DaoImpl2() {
		log.info("실행");
	}
}
