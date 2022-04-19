package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch11City;
import com.mycompany.webapp.dto.Ch11Member;
import com.mycompany.webapp.dto.Ch11Skill;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch11")
@Log4j2
public class Ch11Controller {
	@RequestMapping("/content")
	public String ontent() {
		return "ch11/content";
	}
	
	@GetMapping("/form1")
	public String form1(@ModelAttribute("member") Ch11Member member) { //초기값 주려는 목적
		member.setMnation("한국");
		
		return "ch11/form1";
	}
	
	@PostMapping("/form1")
	public String handleform1(@ModelAttribute("member") Ch11Member member) {
		log.info(member);
		log.info("mid: " + member.getMid());
		log.info("mname: " + member.getMname());
		log.info("mpassword: " + member.getMpassword());
		log.info("mnation: " + member.getMnation());
		log.info("mlanguage: " + Arrays.toString(member.getMskill())); 
		log.info(member);
		return "redirect:/ch11/content";
	}
	
	@GetMapping("/form2")
	public String form2(@ModelAttribute("member") Ch11Member member, Model model) { //양식의 초기값을 제공할 목적
		log.info("실행");
		
		//드롭다운리스트의 항목을 추가할 목적
		List<String> typeList = new ArrayList<>();
		typeList.add("일반회원");
		typeList.add("기업회원");
		typeList.add("헤드헌터회원");
		model.addAttribute("typeList", typeList); 
		
		//기본 선택 항목을 설정
		member.setMtype("기업회원");
		
		//드롭다운리스트의 항목을 추가할 목적
		List<String> jobList = new ArrayList<>();
		jobList.add("학생");
		jobList.add("개발자");
		jobList.add("디자이너");
		model.addAttribute("jobList", jobList);
		
		//기본 선택 항목을 설정
		member.setMjob("개발자");
		
		//드롭다운리스트의 항목을 추가할 목적
		List<Ch11City> cityList = new ArrayList<>();
		cityList.add(new Ch11City(1, "서울"));
		cityList.add(new Ch11City(2, "부산"));
		cityList.add(new Ch11City(3, "제주"));
		model.addAttribute("cityList", cityList);
		
		//기본 선택 항목을 설정
		member.setMcity(3);
		
		log.info("mtype: " + member.getMtype());
		log.info("mjob: " + member.getMjob());
		log.info("mcity: " + member.getMcity());
		log.info(member);
		
		return "ch11/form2";
	}
	
	@GetMapping("/form3")
	public String form3(@ModelAttribute("member") Ch11Member member, Model model) {
		log.info("실행");
		
		//checkbox만들 때 사용
		List<String> languageList = new ArrayList<>();
		languageList.add("C");
		languageList.add("Python");
		languageList.add("Java");
		languageList.add("JavaScript");
		languageList.add("Spring");
		languageList.add("Vue");
		model.addAttribute("languageList", languageList);
		
		//기본적으로 체크하는 것들
		member.setMlanguage(new String[] {"Python", "JavaScript"});
		
		List<Ch11Skill> skillList = new ArrayList<>();
		skillList.add(new Ch11Skill(1, "SpringFramework"));
		skillList.add(new Ch11Skill(2, "SpringBoot"));
		skillList.add(new Ch11Skill(3, "Vue"));
		model.addAttribute("skillList", skillList);
		
		member.setMskill(new int[] {1, 3});
		
		log.info("mlanguage: " + Arrays.toString(member.getMlanguage())); 
		log.info("mskill: " + Arrays.toString(member.getMskill())); 
		
		return "ch11/form3";
	}

	@GetMapping("/form4")
	public String form4(@ModelAttribute("member") Ch11Member member, Model model) {
		log.info("실행");
		
		//드롭다운리스트의 항목을 추가할 목적
		List<String> jobList = new ArrayList<>();
		jobList.add("학생");
		jobList.add("개발자");
		jobList.add("디자이너");
		model.addAttribute("jobList", jobList);
		
		//기본 선택 항목을 설정
		member.setMjob("개발자");
		
		//드롭다운리스트의 항목을 추가할 목적
		List<Ch11City> cityList = new ArrayList<>();
		cityList.add(new Ch11City(1, "서울"));
		cityList.add(new Ch11City(2, "부산"));
		cityList.add(new Ch11City(3, "제주"));
		model.addAttribute("cityList", cityList);
		
		//기본 선택 항목을 설정
		member.setMcity(3);
		
		log.info("mjob: " + member.getMjob());
		log.info("mcity: " + member.getMcity());
		
		return "ch11/form4";
	}
	
	@GetMapping("/form5")
	public String form5(@ModelAttribute("member") Ch11Member member) {
		return "ch11/form5";
	}
	
}
