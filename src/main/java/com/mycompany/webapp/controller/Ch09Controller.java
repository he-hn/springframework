package com.mycompany.webapp.controller;

import java.io.File;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2
public class Ch09Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		log.info("실행");
		log.info("title: " + title);
		log.info("desc: " + desc);
		
		//파일의 경로는 클라이언트의 보안을 위해서 받을 수 없다.
		log.info("file originalname: " + attach.getOriginalFilename()); //사용자가 올린 실제 파일의 이름과 서버에서 저장한 파일의 이름 모두를 db에 저장한다.
		log.info("file contenttype: " + attach.getContentType()); //contenttype 도 db에 저장 해야 한다.
		log.info("file size: " + attach.getSize()); //사이즈는 필요에 따라 저장할지 말지 결정한다.
		
		//받은 파일을 DB에 저장할 때 //둘 다 사용 가능. 선택해서 사용한다.
		//byte[] bytes = attach.getBytes(); //배열을 이용 
		//InputStream is = attach.getInputStream(); //인풋 스트림을 이용
		
		//받은 파일을 서버 파일 시스템에 저장할 때
		String saveFilename = new Date().getTime() + "-" + attach.getOriginalFilename(); //시간 정보를 넣고, '-'와 파일 이름을 넘김으로써 동일한 이름의 파일이 올라가도 덮어쓰기가 되지 않도록 한다.
		File file = new File("C:/Temp/uploadfiles/" + saveFilename); //브라우저가 업로한 파일을 서버쪽에 어디에 저장할지 정해주기 위해서 경로를 지정한다
		attach.transferTo(file); //파일을 저장할 때 transferTo를 이용

		return "redirect:/ch09/content";
	}
	
	/*@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(String title, String desc, MultipartFile attach) throws Exception {
		log.info("실행");
		log.info("title: " + title);
		log.info("desc: " + desc);
		
		log.info("file originalname: " + attach.getOriginalFilename()); 
		log.info("file contenttype: " + attach.getContentType()); 
		log.info("file size: " + attach.getSize());
	
		//받은 파일을 DB에 저장할 때
		//byte[] bytes = attach.getBytes(); 
		//InputStream is = attach.getInputStream(); 
		
		//받은 파일을 서버 파일 시스템에 저장할 때
		String saveFilename = new Date().getTime() + "-" + attach.getOriginalFilename(); 
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFilename", saveFilename);
		String json = jsonObject.toString();
		
		return json;
	}*/
	
	//위 코드와 똑같다. dto를 받거나 따로따로 받는 것의 차이
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09Dto dto) throws Exception {
		log.info("실행");
		log.info("title: " + dto.getTitle());
		log.info("desc: " + dto.getDesc());
		
		log.info("file originalname: " + dto.getAttach().getOriginalFilename()); 
		log.info("file contenttype: " + dto.getAttach().getContentType()); 
		log.info("file size: " + dto.getAttach().getSize());

		//받은 파일을 DB에 저장할 때
		//byte[] bytes = attach.getBytes(); 
		//InputStream is = attach.getInputStream(); 
		
		//받은 파일을 서버 파일 시스템에 저장할 때
		String saveFilename = new Date().getTime() + "-" + dto.getAttach().getOriginalFilename(); 
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		dto.getAttach().transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFilename", saveFilename);
		String json = jsonObject.toString();
		
		return json;
	}
}
