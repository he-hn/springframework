package com.mycompany.webapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberJoinFormValidator implements Validator{ //검사기를 만들려면 먼저 implements Validator를 만든다. support에서는 검사 가능한 클래스 인지 확인. 검사 가능한 타입 조사. 

	//유효성 검사가 가능한 객체인지를 조사
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		boolean result = Ch04Member.class.isAssignableFrom(clazz); //Ch04Member 클래스나 자식 객체가 들어오면 유효성 검사기를 쓸 수 있으나, 관련 없는 클래스 라면 유효성 검사를 할 수 없다 
		return result;
	}

	//supports 메소드가 true를 리턴할 경우 휴효성 검사 실시
	@Override
	public void validate(Object target, Errors errors) { //유효성 검사하는 method. Object는 Ch04Dto 타입이 들어온다
		log.info("실행");
		Ch04Member member = (Ch04Member) target;
		
		//rejectValue는 field error 이다 객체 자체는 문제가 없으나, 필드에 문제가 있다. 문제가 발생하면 errors에 저장한다. errors에는 error 이유가 저장된다.
		
		//mid 검사
		if (member.getMid() == null || member.getMid().trim().equals("")) { //errors.mid.required는 국제화를 위해서 사용 하는 것이다. "mid는 필수 입력 사항입니다"는 default이 되고, 키 값이 있다면 키가 실행되지만 키가 없다면 default가 실행된다
			errors.rejectValue("mid", "errors.mid.required", "mid는 필수 입력 사항입니다.");
		} else {
			if (member.getMid().length() < 4 ) {
				errors.rejectValue("mid", "errors.mid.minlength", new Object[] {4}, "mid는 4자 이상입니다."); //new Object[] {4}s는 error message의 값으로 넘어간다
			}
		}
		
		//mpassword 검사
		if (member.getMpassword() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required", " mpassword는 필수 입력 사항입니다.");
		} else {
			if (member.getMid().length() < 8 ) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength", new Object[] {8}, "mpassword는 8자 이상입니다.");
			}
		}
		
		//memail 검사
		if (member.getMemail() == null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail", "errors.memail.required", " memail은 필수 입력 사항입니다.");
		} else {
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(member.getMemail());
			if (!matcher.matches()) {
				errors.rejectValue("memail", "errors.memail.invalid", "meamil은 이메일 형식이 아닙니다.");
			}
		}
		
		//mtel 검사
	    if(member.getMtel() == null || member.getMtel().trim().equals("")) {
	       errors.rejectValue("mtel", "errors.mtel.required", "mtel은 필수 입력 사항입니다.");
	    } else {
	    	String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(member.getMtel());
	        if(!matcher.matches()) {
	           errors.rejectValue("mtel", "errors.mtel.invalid", "mtel은 전화번호 형식이 아닙니다."); 
	        }
	    }
	}

	
}
