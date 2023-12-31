package com.mycompany.springhomework.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springhomework.exception.Ch10SoldOutException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch10")
public class Ch10Controller {
	
	@RequestMapping("/content")
	public String content(HttpSession session) {
		return "ch10/content";
	}
	
	@GetMapping("/handlingException1")
	public String handlingException1(String data) {
		try {
			// NullPointerException 발생
			if(data.equals("java")) {}
		} catch (NullPointerException e) {
			return "ch10/500_null";
		}
		
		return "redirect:/ch10/content";
	}
	
	@GetMapping("/handlingException2")
	public String handlingException2(String data) {
		// NullPointerException 발생
		if(data.equals("java")) {}
		
		return "redirect:/ch10/content";
	}
	
	@GetMapping("/handlingException3")
	public String handlingException3() {
		Object data = "abc";
		Date date = (Date) data;
		// ClassCastException 발생
		return "redirect:/ch10/content";
	}
	
	@GetMapping("/handlingException4")
	public String handlingException4() {
		int stock = 0;
		if(stock == 0) {
			throw new Ch10SoldOutException(" 이벤트 상품 잔고가 없음");
		}
		return "ch10/content";
	}
	
	@GetMapping("/handlingException5")
	public String handlingException5() {
		String data = "abc";
		int number = Integer.parseInt(data);
		// NumberFormatException 발생
		return "ch10/content";
	}
	
	
}
