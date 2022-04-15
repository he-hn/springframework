package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2
@SessionAttributes({"inputForm"})
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces="application/json; charset=UTF-8") //value 값이 나오는 mapping 다음에 method는 view를 return 하는 것이 아니라 응답 내용을 받는 것이다
	@ResponseBody //리턴 값을 받는 본문이 필요하다. 본문이 없으면 안된다.
	public String saveData(String name, HttpSession session) {
		log.info("실행");
		
		session.setAttribute("name", name); //setAttibutes는 저장
		
		//{"result":"success"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping(value="/readData", produces="application/json; charset=UTF-8") //value 값이 나오는 mapping 다음에 method는 view를 return 하는 것이 아니라 응답 내용을 받는 것이다
	@ResponseBody //리턴 값을 받는 본문이 필요하다. 본문이 없으면 안된다.
	public String readData(HttpSession session) {
		log.info("실행");
		
		String name = (String) session.getAttribute("name"); //키 이름 getAttribute는 가져옴
		
		//{"result":"success"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		if(mid.equals("spring") && mpassword.equals("12345")) {
			//로그인 성공시 세션에 회원 아이디를 저장
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//세션에서 주어진 키를 삭제
		//session.removeAttribute("sessionMid");
		//세션 객체 자체를 제거
		session.invalidate();
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/userinfo")
	public String userInfo( 
			HttpSession session,
			@SessionAttribute String sessionMid, 
			@SessionAttribute("sessionMid") String mid) { //세션에 저장된 이름이 매개변수의 이름과 같다면 세션에 저장된 이름을 넣어주지 않아도 되지만 같지 않다면 넣어줘야 한다.
		
		String memberId = (String) session.getAttribute("sessionMid");
		
		log.info("memberId: " + memberId);
		log.info("sessionMid: " + sessionMid);
		log.info("mid: " + mid);
		return "redirect:/ch08/content";
	}
	
	@RequestMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
	@ResponseBody //ResponseBody를 넣게 되면 본문이 넘어오게 된다. 그리고 이것은 view를 리턴하는 것이 아니라 값을 return 한다.
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		String result = null;
		if (mid.equals("spring")) {
			if(mpassword.equals("12345")) { //아이디와 비밀번호가 맞더
				result = "success";
				session.setAttribute("sessionMid", mid); //로그인 성공시 세션에 저장함을 빠트리면 안된다.
			} else { //비밀번호가 틀리다
				result = "wrongMpassword";
			}
		} else { //아이디가 틀리다
			result = "wrongMid";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();
		return json;	
	}
	
	@RequestMapping(value="/logoutAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		//세션에서 주어진 키를 삭제
		//session.removeAttribute("sessionMid");
		//세션 객체 자체를 제거
		session.invalidate();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;	
	}
	
	
	@GetMapping("/inputStep1")
	public String inputStep1() {
		return "ch08/inputStep1";
	}

	//새로운 세션 저장소에 객체를 저장하는 역할
	@ModelAttribute("inputForm") //ModelAttribute("inptForm")은 session에 저장되어 있는 데이터이다. 해당 객체는 inputStep2와 inputStep3에서 사용된다
	public Ch08InputForm getCh08InputForm() {
		log.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	} //@SessionAttributes가 없다면 이 메소드는 다른 메소드를 실행 할때마다 계속해서 자동적으로 실행된다. 하지만 @SessionAttributes가 있다면 session에 딱 한번 데이터가 저장 되기 때문에 다음부터는 실행 되지 않는다.
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		log.info("data1: " + inputForm.getData1());
		log.info("data2: " + inputForm.getData2());
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		log.info("data1: " + inputForm.getData1());
		log.info("data2: " + inputForm.getData2());
		log.info("data3: " + inputForm.getData3());
		log.info("data4: " + inputForm.getData4());
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
}
