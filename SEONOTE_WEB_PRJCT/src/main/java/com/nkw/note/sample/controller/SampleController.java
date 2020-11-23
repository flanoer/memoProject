package com.nkw.note.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkw.note.sample.service.SampleService;

/**
 * 
 * @author nkw
 *
 */
@Controller
@RequestMapping("/sample")
public class SampleController {
	
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@Value("${spring.profiles.active:dev}")
	private String activeProfile;
	
	@Autowired
	private SampleService sampleService;
	
	@RequestMapping({"/main.do"})
	public String main() throws Exception {
		return "/main";
	}
	
	@RequestMapping({"/test.do"})
	public String list() throws Exception {
		return "/board/testPage";
	}
	
	
	
	/** 불필요 소스 **/
	/**
	 * 
	 * @Author : HP
	 * @Date : 2020. 11. 12.
	 * @Return : String
	 */
	@RequestMapping(value="/crawling.do",produces = "application/json")
	@ResponseBody
	public String crawling(HttpServletRequest req, HttpServletResponse resp, @RequestBody Map<String,Object> param) {
		log.info("======================================== crawling [s] ========================================");
		log.info("param check >>> {}",param);
		String crawled = "";
		try {
			crawled = sampleService.getCrawlingData(param, req);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		log.info("======================================== crawling [e] ========================================");
		return crawled;
	}

	/**
	 * 
	 * @Author : HP
	 * @Date : 2020. 11. 12.
	 * @Return : String
	 */
	@RequestMapping(value="/insertCommentList.do",produces = "application/json")
	@ResponseBody
	public String insertCommentList(HttpServletRequest req, HttpServletResponse resp, @RequestBody Map<String,Object> param) {
		log.info("======================================== insertCommentList [s] ========================================");
		log.info("param check >>> {}",param);
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = (List<Map<String,Object>>)param.get("listData");
		log.info("list check >>> {}",list);
		
		int result = 0;
		try {
			sampleService.insertCrawlingListData(list);
		} catch(Exception e) {
			log.error(e.getMessage());
			if("dev".equals(activeProfile)) e.printStackTrace();
		}
		
		log.info("======================================== insertCommentList [e] ========================================");
		return result == 1 ? "success" : "fail";
	}
}
