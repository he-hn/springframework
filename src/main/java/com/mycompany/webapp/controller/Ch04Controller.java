package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Dto;
import com.mycompany.webapp.dto.Ch04Member;
import com.mycompany.webapp.validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.validator.Ch04MemberIdValidator;
import com.mycompany.webapp.validator.Ch04MemberLoginFormValidator;
import com.mycompany.webapp.validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.validator.Ch04MemberTelValidator;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch04")
@Log4j2
class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}
	
	@PostMapping("/method1")
	public String method1(Ch04Dto dto) {
		log.info(dto.toString());
		return "ch04/content";
	}
	
	//DTO와 유효성 검사기를 연결
	@InitBinder("joinForm") //Valid를 하기 위한 객체를 참조 할 때는 객체 명으로 부르는데 이때, 이름의 맨 앞자리는 소문자로 넣음으로써 참조를 한다.
	public void bindCh04MemberJoinFormValidator(WebDataBinder binder) {
		//binder.setValidator(new Ch04MemberJoinFormValidator()); //add로 시작하는 method는 여러개를 추가하고, set으로 시작하는 method는 하나를 변경한다
		binder.addValidators(
			new Ch04MemberIdValidator(),
	        new Ch04MemberPasswordValidator(),
	        new Ch04MemberEmailValidator(),
	        new Ch04MemberTelValidator()
		); 
	}
	
	//InitBinder의 @InitBinder("ch04Member") 로 @Valid Ch04Member와 연결시킨다. @ModelAttribute를 사용하면 참조명을 바꿀 수 있다. 현재 코드에서는 @ModelAttribute로 참조명을 수정함.
	@PostMapping("/join")
	//public String join(@Valid Ch04Member member, Errors errors) { //@Valid가 빠지면 유효성 검사를 하지 않는다. 유효성 검사를 하고 유효성 검사의 결과(error 객체)가 errors에 들어온다.
	public String join(@ModelAttribute("joinForm") @Valid Ch04Member member, Errors errors) { //@ModelAttribute 를 이용하여 참조명을 바꿀 수 있다.
		log.info("실행");
		
		//유효성 검사 확인
		if (errors.hasErrors()) { //error 가 발생하면 다시 양식으로 돌아간다 
			// 다시 입력 폼으로 돌아가기
			return "ch04/content";
		} 
		
		//회원 가입 처리
		//...
		
		//홈페이지로 이동
		return "redirect:/"; 
	}
	
	@InitBinder("loginForm") 
	public void bindCh04MemberLoginFormValidator(WebDataBinder binder) {
		//binder.setValidator(new Ch04MemberLoginFormValidator());
		binder.addValidators(
				new Ch04MemberIdValidator(),
		        new Ch04MemberPasswordValidator()
        );
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid Ch04Member member, Errors errors) { 
		log.info("실행");
		
		if (errors.hasErrors()) {
			return "ch04/content";
		} 
		return "redirect:/"; 
	
	}
}
