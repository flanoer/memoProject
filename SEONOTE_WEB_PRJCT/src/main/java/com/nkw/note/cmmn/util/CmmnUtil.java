package com.nkw.note.cmmn.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CmmnUtil{
	
	private static final Logger log = LoggerFactory.getLogger(CmmnUtil.class);
	
	public static void viewParam(HttpServletRequest request, String string) {
		log.debug("### viewParam ###");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
//			HashMap<Object, Object> tmpMap = new HashMap<>();
			String paramName = paramNames.nextElement();
		    String value = request.getParameter(paramName);
		    log.debug(paramName+"::"+value);
		}
		log.debug("### viewParam ###");
	}
	
}