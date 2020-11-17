package com.nkw.note.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String getList(HttpServletRequest req, HttpServletResponse resp) {
		// req 데이터 검증
		
		
		// req 데이터 변환
		
		// 조회
		
		// resp 요청 데이터 반환
		
		return "";
	}
}
