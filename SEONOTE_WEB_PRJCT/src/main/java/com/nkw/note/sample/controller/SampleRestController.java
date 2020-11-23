package com.nkw.note.sample.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkw.note.sample.service.SampleService;

/**
 * 
 * @author nkw
 *
 */

@RestController
@RequestMapping("/sampleRest")
public class SampleRestController {
	
	private static final Logger log = LoggerFactory.getLogger(SampleRestController.class);
	
	@Value("${spring.profiles.active:dev}")
	private String activeProfile;
	
	@Autowired
	private SampleService sampleService;
	
	
	@RequestMapping("/getList.do")
	public Map<String,Object> getList(HttpServletRequest req, HttpServletResponse resp) {
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> result = new HashMap<String,Object>();
		
		// req 데이터 검증
		String categoryId = StringUtils.defaultIfEmpty(req.getParameter("categoryId"), "");
		
		// req 데이터 변환
		if(!StringUtils.isEmpty(categoryId)) {
			param.put("categoryId",categoryId);
		}
		
		// 조회
		result = sampleService.getList(param);
		
		// resp 요청 데이터 반환
		return result;
	}
}
