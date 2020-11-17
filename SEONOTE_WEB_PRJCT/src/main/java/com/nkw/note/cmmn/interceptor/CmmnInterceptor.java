package com.nkw.note.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nkw.note.cmmn.interceptor.CmmnInterceptor;

public class CmmnInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor{
	private static final Logger log = LoggerFactory.getLogger(CmmnInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("####################### CmmnInterceptor 시작 #######################");
		
		String nowUrl = request.getRequestURI().toString();
		log.info("nowUrl ::: " + nowUrl);
		
		return super.preHandle(request, response, handler);
	}
	
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		super.postHandle(request, response, handler, mv);
	}
	
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
