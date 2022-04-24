package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch12")
@Log4j2
public class Ch12Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch12/content";
	}
	
	@RequestMapping("/fileList")
	public String fileList() {
		log.info("실행");
		return "ch12FileListView";
	}

	@RequestMapping("/fileDownload")
	public String fileDownload(
			@ModelAttribute("fileName") String fileName,
			@ModelAttribute("userAgent") @RequestHeader("User-Agent") String userAgent) { //header에 User-Agent 값을 userAgent 로 넘기고, @ModelAttribute를 이용하여 model에 값을 넣을 수 있게 한다
		log.info("실행");
		return "ch12FileDownloadView";
	}
	
	
}
