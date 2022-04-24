package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck;
import com.mycompany.webapp.aspect.Ch15Aspect8LoginCheck;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch15")
@Log4j2
public class Ch15Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch15/content";
	}
	
	@RequestMapping("/before1")
	public String before1() {
		log.info("실행");
		return "redirect:/ch15/content";
	}
	
	@RequestMapping("/before2")
	public String before2() {
		log.info("실행");
		return "redirect:/ch15/content";
	}
	
	@RequestMapping("/after")
	public String afterXXX() {
		log.info("실행");
		return "redirect:/ch15/content";
	}
	
	@RequestMapping("/afterReturning")
	public String afterReturning() {
		log.info("실행");
		return "redirect:/ch15/content";
	}
	
	@RequestMapping("/afterThrowing")
	public String afterThrowing() {
		log.info("실행");
		boolean result = true;
		if (result) {
			throw new RuntimeException("테스트 예외입니다.");
		}
		return "redirect:/ch15/content";
	}
	
	@RequestMapping("/around")
	public String around() {
		log.info("실행");
		return "redirect:/ch15/content";
   }
		
	@Resource
	private Ch14BoardService boardService;
	
	@RequestMapping("/runtimeCheck")
	@Ch15Aspect7RuntimeCheck
	public String runtimeCheck() {
		log.info("실행");
		Pager pager = new Pager(10, 5, boardService.getTotalBoardNum(), 1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		return "redirect:/ch15/content";
	}
	
	@GetMapping("/boardList")
	@Ch15Aspect8LoginCheck //로그인 체크 기능이 들어간다고 가정하고 정의한다
	public String boardList(Model model) {
		log.info("실행");
		Pager pager = new Pager(5, 5, boardService.getTotalBoardNum(), 1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards", boards);
		return "ch15/boardList";
   }
}
