package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.security.Ch17UserDetails;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch17")
@Log4j2
public class Ch17Controller {
	@RequestMapping("/content")
	public String content(Model model) {
		log.info("실행");
		return "ch17/content";
	}
	   
	@RequestMapping("/loginForm")
	public String loginForm() {
		log.info("실행");
		return "ch17/loginForm";
	}
	   
	@RequestMapping("/adminAction")
	public String adminAction() {
		log.info("실행");
		return "redirect:/ch17/content";
	}
	   
	@RequestMapping("/managerAction")
	public String managerAction() {
		log.info("실행");
		return "redirect:/ch17/content";
	}
	   
	@RequestMapping("/userAction")
	public String userAction() {
		log.info("실행");
		return "redirect:/ch17/content";
	}
	   
	@RequestMapping("/error403")
	public String error403() {
		log.info("실행");
		return "ch17/error403";
	}
	
	@Resource
	private Ch14MemberService memberService;
	   
	@RequestMapping("/joinForm")
	public String joinForm() {
		log.info("실행");
		return "ch17/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(Ch14Member member, Model model) {
		log.info("실행");
		      
		//활성화 설정
		member.setMenabled(true);
	
		//회원 가입 처리
		JoinResult jr = memberService.join(member);
		if(jr == JoinResult.SUCCESS) {
			return "redirect:/ch17/loginForm";
		} else if(jr == JoinResult.DUPLICATED) {
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch17/joinForm";
		} else {
			model.addAttribute("error", "회원 가입이 실패되었습니다. 다시 시도해 주세요.");
			return "ch17/joinForm";
		}
	}
	
	@RequestMapping(value="/userInfo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String userInfo(Authentication authentication) {
		//사용자 아이디
		String mid = authentication.getName();
		
		//방법1
		//Ch14Member member = memberService.getMember(mid);
		//String mname = member.getMname();
		//String memail = member.getMemail();
		
		//방법2
		Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		String mname = userDetails.getMname();
		String memail = userDetails.getMemail();
		
		//사용자 권한 얻기(ROLE_xxx)
		List<String> authorityList = new ArrayList<>();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			authorityList.add(authority.getAuthority());
		}
		
		//String autority = (String) authentication.getAuthorities().toArray()[0];
		
		//사용자가 로그인 PC의 IP 주소 얻기
		WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
		String ip = wad.getRemoteAddress();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("mid", mid);
		jsonObject.put("mname", mname);
		jsonObject.put("memail", memail);
		jsonObject.put("mrole", authorityList);
		jsonObject.put("ip", ip);
		String json = jsonObject.toString();
		
		return json;
	}
}
	