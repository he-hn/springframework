package com.mycompany.webapp.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.exception.Ch10SoldOutException;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch10")
@Log4j2
public class Ch10Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch10/content";
	}
	
	@RequestMapping("/handlingException1")
	public String handlingException1(String data) {
		try {
			if(data.equals("java")) {
				//NullpointerException 발생
			}
		} catch (NullPointerException e){
			return "ch10/500_null";
		}
		return "redirect:/ch10/content";
	}
	
	@RequestMapping("/handlingException2")
	public String handlingException2(String data) {

		if(data.equals("java")) {
			//NullpointerException 발생
		}
		
		return "redirect:/ch10/content";
	}
	
	@RequestMapping("/handlingException3")
	public String handlingException3() {
		log.info("실행");
		Object data = "abc";
		Date date = (Date) data; //ClassCastException 발생
		return "redirect:/ch10/content";
	}
	
	@RequestMapping("/handlingException4")
	public String handlingException4() {
		log.info("실행");
		int stock = 0;
		if(stock == 0) {
			throw new Ch10SoldOutException("상품 재고가 없음");
		}
	return "redirect:/ch10/content";
	}
	
	@RequestMapping("/handlingException5")
	public String handlingException5() {
		log.info("실행");
		int[] arr = {10, 20, 30};
		arr[3] = 40; //ArrayIndexOutOfBoundsException
	return "redirect:/ch10/content";
	}
   
}
