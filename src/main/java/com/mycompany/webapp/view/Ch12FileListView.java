package com.mycompany.webapp.view;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Ch12FileListView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("실행");
		
		//파일의 총 개수 및 파일 이름 목록 얻기 -------------------------------
		String fileDir = "C:/Temp/uploadfiles";
		File file = new File(fileDir);  //directory도 file 객체로 만들 수 있다. directory에 대한 file 객체이다.
		String[] fileList = file.list(); //현재 directory에 있는 file의 list를 배열로 만든다.
		int totalFileNum = fileList.length;
		
		//JSON 응답을 생성하고 보내기 -------------------------------------
		response.setContentType("application/json; charset=UTF-8");
		
		//{"totalFileNum":20, "fileList": ["a.jpg", "b.jpg", ... ]}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalFileNum", totalFileNum);
		
		JSONArray jsonArray = new JSONArray();
		for (String fileName : fileList) {
			jsonArray.put(fileName);
		}
		jsonObject.put("fileList", jsonArray);
		String json = jsonObject.toString();
		
		//응답 본문에 JSON 문자열을 싣기
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	 
}
