package com.nkw.note.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {
	public String getCrawlingData(Map<String,Object> param, HttpServletRequest req) throws Exception;

	public int insertCrawlingListData(List<Map<String, Object>> list) throws Exception;
}
