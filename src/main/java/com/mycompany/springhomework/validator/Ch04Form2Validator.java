package com.mycompany.springhomework.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springhomework.dto.Ch04Form2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04Form2Validator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		boolean check = Ch04Form2.class.isAssignableFrom(clazz);
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("실행");
		Ch04Form2 ch04Form2 = (Ch04Form2) target;
		
		// param1 검사
		String param1 = ch04Form2.getParam1();
		if(param1 == null || param1.equals("")) {
			errors.rejectValue("param1", "errors.form.required", "필수 입력(D)");
		} else {
			String regExp = "^\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4][0-9]{6}$";
			boolean result = Pattern.matches(regExp, param1);
			if(result == false) {
	            errors.rejectValue("param1", "errors.form.format", "주민번호 형식에 맞지 않음(D)");
	         }
		} 
		
		// param2 검사
		String param2 = ch04Form2.getParam2();
	      if(param2 == null || param2.equals("")) {
	         errors.rejectValue("param2", "errors.form.required", "필수 입력(D)");
	      } else {
	         String regExp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
	         boolean result = Pattern.matches(regExp, param2);
	         if(result == false) {
	            errors.rejectValue("param2", "errors.form.format", "생년월일 형식에 맞지 않음(D)");
	         }
	      }

		// param3 검사
	      String param3 = ch04Form2.getParam3();
	      if(param3 == null || param3.equals("")) {
	          errors.rejectValue("param3", "errors.form.required", "필수 입력(D)");
	       } else {
	          String regExp = "^[a-zA-Z0-9]*$";
	          boolean result = Pattern.matches(regExp, param3);
	          if(result == false) {
	             errors.rejectValue("param3", "errors.form.format", "비밀번호 형식에 맞지 않음(D)");
	          } else if(param3.length() < 8) {
	  			errors.rejectValue("param3", "errors.form.minlength", new Object[] {8}, "최소 8자 입력(D)");
	  		  } else if(param3.length() > 10) {
	  		  	errors.rejectValue("param3", "errors.form.maxlength", new Object[] {15}, "최대 15자 입력(D)");
	  		  }
	       }
	}
}
