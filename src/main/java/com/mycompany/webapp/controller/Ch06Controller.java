package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/"; //'/'를 처리하는 controller는 HomeController이다 
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		//{"fileName": "photo6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		// Controller가 응답을 바로 만들어냄. 이럴때는 jsp를 return 할 필요가 없다. 그렇기 때문에 jsp로 가는 forward가 발생할 수 없다.
		// json을 보낼 때는 jsp로 보내는 것보다는 Controller에서 처리하는 것이 더 편리하다
		response.setContentType("application/json; charset=UTF-8"); //브라우저는 최소한 WAS 보내는 데이터의 타입이 뭔지 알아야 한다. 데이터 타입이 json이라면 setContentType 에 application/json 으로 넘김으로써 json 타입임을 알려줘야 한다
		PrintWriter pw = response.getWriter(); //클라이언트로 가는 출력 스트림을 얻어냄. 다루는 데이터 타입은 문자열
		pw.println(json);
		pw.flush();
		pw.close();
		
	}
	
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8") //value 가 들어간다는 것은 값을 하나만 넣는 것이 아니라 여러개를 넣기 위함이다. produces 응답 헤드에 contentType으로 들어간다.
	@ResponseBody //리턴되는 내용이 응답 본문에 들어간다. 
	public String getJson2() throws Exception {
		//{"fileName": "photo6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo2.jpg");
		String json = jsonObject.toString();
		return json; //json 자체가 응답 내용(바디에 들어감)이 된다. 
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		return "redirect:/";
	}
	
}
