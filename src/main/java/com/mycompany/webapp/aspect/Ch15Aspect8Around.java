package com.mycompany.webapp.aspect;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect8Around {
	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect8LoginCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sessionMid");
		if(mid == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "fail");
			String json = jsonObject.toString();
			
			HttpServletResponse response = sra.getResponse();
			response.setContentType("application/json; chartset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
			pw.flush();
			pw.close();
			return null;
		} else {
			Object result = joinPoint.proceed();
			return result;
		}
		
	}
	
}
