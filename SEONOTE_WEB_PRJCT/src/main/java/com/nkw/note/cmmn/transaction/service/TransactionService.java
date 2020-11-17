package com.nkw.note.cmmn.transaction.service;

import java.util.Map;
import java.util.List;

public interface TransactionService {
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectList(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : Map<String,Object>
	 */
	public Map<String, Object> selectMap(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : String
	 */
	public String selectString(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : int
	 */
	public int selectInt(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : int
	 */
	public int insert(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : int
	 */
	public int insertList(List<Map<String, Object>> list, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : void
	 */
	public void update(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception;
	
	/**
	 * @Author : HP
	 * @Date : 2020. 11. 13.
	 * @Return : void
	 */
	public void delete(Map<String, Object> gMapgMap, String queryId, String jdbcName) throws Exception;
}
