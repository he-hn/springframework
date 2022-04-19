package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service("ch13ServiceCreateByAnno")
@Log4j2
public class Ch13ServiceCreateByAnno {
	public Ch13ServiceCreateByAnno() {
		log.info("실행");
	}

}
