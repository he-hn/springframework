package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service("c13Service1")
@Log4j2
public class Ch13Service1CreateByAnno {
	public Ch13Service1CreateByAnno() {
		log.info("실행");
	}

}
