package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;
import com.mycompany.webapp.service.Ch14MemberService.LoginResult;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch14")
@Log4j2
public class Ch14Controller { //컨트롤러가 service를 이용하고 service가 dao를 이용하므로 주입 해야 한다. 컨트롤러에서 dao를 바로 이용해서는 안된다. 
	@Resource //자동으로 서비스객체 들어온다
	private Ch14MemberService memberService;
	
	@RequestMapping("/content")
	public String content() {	
		return "ch14/content";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "ch14/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch14Member member, Model model) {
		member.setMenabled(true);
		member.setMrole("ROLE_USER");
		JoinResult jr = memberService.join(member);
		if(jr == JoinResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (jr == JoinResult.DUPLICATED) {
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch14/joinForm";
		} else {
			model.addAttribute("error", "회원 가입 실패, 다시 시도해 주세요.");
			return "ch14/joinForm";
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch14/loginForm";
	}
	
	@PostMapping("/login")
	public String login(Ch14Member member, HttpSession session, Model model) { //Dto도 같이 넘어감
		LoginResult result= memberService.login(member);
		if(result == LoginResult.SUCCESS) {
			session.setAttribute("sessionMid", member.getMid()); 
			return "redirect:/ch14/content";
		} else if(result == LoginResult.FAIL_MID) {
			model.addAttribute("error", "아이디가 존재하지 않습니다.");
			return "ch14/loginForm";
		} else {
			model.addAttribute("error", "패스워드가 틀립니다.");
			return "ch14/loginForm";
		} //model에 담으면 해당 View안에서만 사용이 가능하지만 request에 담으면 다른 페이지에서도 사용 가능하다
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionMid");
		return "redirect:/ch14/content";
	}
	
	@Resource
	private Ch14BoardService boardService;
	
	
	@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue="1") int pageNo, Model model) { //값이 안 넘어오는 것을 대비해서 default 값을 준다
		int totalBoardNum = boardService.getTotalBoardNum();
		Pager pager = new Pager(5, 5, totalBoardNum, pageNo);
		model.addAttribute("pager", pager);
		
		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards", boards);
		return "ch14/boardList";
	}
	
	@GetMapping("/boardWriteForm")
	public String boardWriteForm() {
		return "ch14/boardWriteForm";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(Ch14Board board) throws IOException {
		if(!board.getBattach().isEmpty()) {
			board.setBattachoname(board.getBattach().getOriginalFilename());                                                               
			board.setBattachtype(board.getBattach().getContentType());
			board.setBattachsname(new Date().getTime() + "-" + board.getBattachoname());
			File file = new File("C:/Temp/uploadfiles/" + board.getBattachsname());
			board.getBattach().transferTo(file);
		}
		boardService.writeBoard(board);
		return "redirect:/ch14/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardDetail";
	}
	
	@GetMapping("/filedownload")
	public void filedownload(
			int bno, 
			HttpServletResponse response,
			@RequestHeader("User-Agent") String userAgent) throws Exception {
		
		//DB에서 bno에 대한 게시물 가져오기
		Ch14Board board = boardService.getBoard(bno);
		String contentType = board.getBattachtype();
		String originalFilename = board.getBattachoname();
		String saveFilename = board.getBattachsname();
		
		//응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType);
		
		//다운로드할 파일명을 헤더에 추가
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE 브라우저일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else {
			//크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");

		//파일 데이터를 응답 본문에 싣기
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		if(file.exists()) {
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
	
	@GetMapping("/boardUpdateForm")
	public String boardUpdateForm(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(Ch14Board board) {
		//Ch14Board dbBoard = boardService.getBoard(board.getBno());
		//dbBoard.setBtitle(board.getBtitle());
		//dbBoard.setBcontent(board.getBcontent());
		boardService.updateBoard(board);
		return "redirect:/ch14/boardDetail?bno=" + board.getBno();
	}
	
	@GetMapping("/boardDelete")
	public String boardDelete(int bno) {
		boardService.removeBoard(bno);
		return "redirect:/ch14/boardList";
	}
}
