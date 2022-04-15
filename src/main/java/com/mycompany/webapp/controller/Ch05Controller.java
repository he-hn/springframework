package com.mycompany.webapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch05")
@Log4j2
public class Ch05Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		log.info("Client IP: " + request.getRemoteAddr());
		log.info("Request Method: " + request.getMethod()); //시작행에 담겨서 넘어온다
		log.info("Context Path(Root): " + request.getContextPath()); //webapp
		log.info("Request URI: " + request.getRequestURI()); //포트번호를 생략
		log.info("Request URL: " + request.getRequestURL()); //클라이언트가 요청한 전체 내용이 들어있다
		log.info("Header User-Agent: " + request.getHeader("User-Agent")); //헤더행의 User-Agent 정보를 가져온다
		log.info(userAgent);
		
		return "redirect:/ch05/content"; //redirect가 있으면 redirect 뒤에 있는 경로로 요청을 하라는 것을 의미. redirect가 없으면 url에 경로가 남아 있으나 redirect가 있으면 다시 요청된 경로가 남게 된다
	}
	
	@GetMapping("/createCookie") 
	public String createCookie(HttpServletResponse response) { //한번이라도 실행 되었다면 저장된다. 저장 정보는 브라우저가 갖고 있고, 동일한 서버를 요청할 때 쿠키를 들고간다. 응답 헤더는 서버가 브라우저에게 요청하면 브라우저가 서버에게 헤더를 보낸다
		log.info("실행");
		
		Cookie cookie = new Cookie("useremail", "blueskii@naver.com"); //브라우저는 쿠키에 담긴 정보를 가지고 있다.
		cookie.setDomain("localhost");	//localhost 면 전송 			//쿠키를 받은 서버의 ip이다. 해당 웹 서버의 특정 경로로 요청 했을 때만 쿠키를 들고 오게 한다. 
		cookie.setPath("/");			//localhost/... 이면 모두 전송 	//쿠키를 만들어서 보낸다. 쿠키는 해당 경로로 요청할 때만 가져가고 경로가 아니라면 가져가지 않는다.
		cookie.setMaxAge(30*60);		//이 시간동안에만 전송 			//해당 시간이 지나면 클라이언트는 쿠키를 버린다.
		cookie.setHttpOnly(true);		//JavaScript에서 못 읽게함 		//서버와 클라이언트가 통신 할 때만 쿠키를 가져온다. 서버쪽에서만 쿠키를 읽을 수 있고, 브라우저는 읽을 수 없다
		cookie.setSecure(false);		//https: 또는 http:모두 전송 	//'s'는 전송 할 때, 데이터를 암호화해서 보내는 방식이다. 
		response.addCookie(cookie);									//저장. 쿠키에 대한 정보를 실어서 보낸다. 응답에 쿠키를 더해서 보낼 때.
		
		cookie = new Cookie("userid", "spring");
		cookie.setDomain("localhost");	//localhost 면 전송 
		cookie.setPath("/");			//localhost/... 이면 모두 전송 
		cookie.setMaxAge(30*60);		//이 시간동안에만 전송 
		cookie.setHttpOnly(true);		//JavaScript에서 못 읽게함 
		cookie.setSecure(false);		//https: 또는 http:모두 전송 
		response.addCookie(cookie);
		
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie1")
	public String getCookie(HttpServletRequest request) { 
		log.info("실행"); //쿠키도 헤더에 실려서 넘어온다
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			log.info(cookieName + ":" + cookieValue);
		} 
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie2")
	public String getCookie1(@CookieValue String userid, @CookieValue String useremail) { 
		log.info("실행"); //쿠키는 헤더에 실려서 넘어온다
		log.info("userid: " + userid);
		log.info("useremail: " + useremail);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws Exception {
		//String json = "{\"userid\": \"spring\"}";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "홍길동");
		jsonObject.put("useremail", "spring@mycompany.com");
		String json = jsonObject.toString();
		log.info(json); //쿠키는 헤더에 실려서 넘어간다. 만들어진 jsonObject는 {}로 감싸진 형태이다. 해당 문자열은 헤더에 들어갈 수 없다. 그러므로 url 인코딩을 해줘야 한다.
		json = URLEncoder.encode(json, "UTF-8"); //utf-8은 한글의 크기를 결정한다
		log.info(json);
		
		Cookie cookie = new Cookie("user", json); //쿠키의 값은 모두 문자열이다
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("getJsonCookie")
	public String getJsonCookie(@CookieValue String user){ //web.xml에 filter가 쿠키에도 적용이 된다. 자동으로 encoding 된다. 
		log.info(user);
		JSONObject jsonObject = new JSONObject(user);
		String username = jsonObject.getString("username");
		String useremail = jsonObject.getString("useremail");
		log.info("username: " + username);
		log.info("useremail: " + useremail);
		return "redirect:/ch05/content";
	
	}
}
