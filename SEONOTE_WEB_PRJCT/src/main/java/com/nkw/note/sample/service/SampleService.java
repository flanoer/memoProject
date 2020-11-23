package com.nkw.note.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {
	/**
	 * 크롤링 데이터 가져오기
	 * @Author : HP
	 * @Date : 2020. 11. 18.
	 * @Return : String
	 */
	public String getCrawlingData(Map<String,Object> param, HttpServletRequest req) throws Exception;

	/**
	 * 크롤링 데이터 insert하기
	 * @Author : HP
	 * @Date : 2020. 11. 18.
	 * @Return : int
	 */
	public int insertCrawlingListData(List<Map<String, Object>> list) throws Exception;
	
	/**
	 * 메모 리스트 가져오기
	 * @Author : HP
	 * @Date : 2020. 11. 18.
	 * @Return : Map<String,Object>
	 */
	public Map<String,Object> getList(Map<String,Object> param);
}
