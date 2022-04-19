package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller("ch13Controller") //이름은 중복되어서는 안된다
@RequestMapping("/ch13")
@Log4j2
public class Ch13Controller {
	
	public Ch13Controller() { //생성자가 호출이 되었다는 것은 객체로 만들어졌다는 것을 의미한다.
		log.info("실행");
	}
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch13/content";
	}
}
