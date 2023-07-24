package com.mycompany.springhomework.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springhomework.dto.Ch04Form1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04Form1Validator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		// isAssignableFrom : ()안의 값이 Ch04Form1에 대입될 수 있는지 조사
		boolean check = Ch04Form1.class.isAssignableFrom(clazz);
		// 리턴값이 true일 때만 validate()를 실행
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("실행");
		Ch04Form1 ch04Form1 = (Ch04Form1) target;
		
		// param1 검사
		String param1 = ch04Form1.getParam1();
		if(param1 == null || param1.equals("")) {
			errors.rejectValue("param1", "errors.form.required", "필수 입력(D)");
		} else if(param1.length() < 8) {
			errors.rejectValue("param1", "errors.form.minlength", new Object[] {8}, "최소 8자 입력(D)");
		} else if(param1.length() > 15) {
			errors.rejectValue("param1", "errors.form.maxlength", new Object[] {15}, "최대 15자 입력(D)");
		}
		
		// param2 검사
		String param2 = ch04Form1.getParam2();
	      if(param2 == null || param2.equals("")) {
	         errors.rejectValue("param2", "errors.form.required", "필수 입력(D)");
	      } else {
	         String regExp = "^(010|011)-[0-9]{3,4}-[0-9]{4}$";
	         boolean result = Pattern.matches(regExp, param2);
	         if(result == false) {
	            errors.rejectValue("param2", "errors.form.format", "전화번호 형식에 맞지 않음(D)");
	         }
	      }
		
		// param3 검사
	      String param3 = ch04Form1.getParam3();
	      if(param3 == null || param3.equals("")) {
	          errors.rejectValue("param3", "errors.form.required", "필수 입력(D)");
	       } else {
	          String regExp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
	          boolean result = Pattern.matches(regExp, param3);
	          if(result == false) {
	             errors.rejectValue("param3", "errors.form.format", "이메일 형식에 맞지 않음(D)");
	          }
	       }
	}
}