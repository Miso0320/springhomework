package com.mycompany.springhomework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycompany.springhomework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch13LoginCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("실행");
		
		// 요청 처리 메소드가 @Login가 붙어 있는지 확인
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Login login = handlerMethod.getMethodAnnotation(Login.class);
		
		// @Login이 붙어 있다면
		if(login != null) {
			HttpSession session = request.getSession();
			Ch13Member member = (Ch13Member) session.getAttribute("ch13Login");
			
			// 로그인이 되었다면
			if(member != null) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath() + "/ch13/login");
				return false;
			}
		} else {
			// @Login이 붙어있지 않다면
			return true;
		}
	}
}
