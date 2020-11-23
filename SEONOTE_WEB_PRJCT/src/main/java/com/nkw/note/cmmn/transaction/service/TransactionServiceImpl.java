package com.nkw.note.cmmn.transaction.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkw.note.cmmn.transaction.dao.TransactionDao;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionDao trxDao;

	public List<Map<String, Object>> selectList(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		return trxDao.selectList(gMap, queryId, jdbcName);
	}

	public Map<String, Object> selectMap(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String selectString(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectInt(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		
		return 0;
	}

	public int insertList(List<Map<String, Object>> list, String queryId, String jdbcName) throws Exception {
		return trxDao.insertList(list, queryId, jdbcName);
	}

	public void update(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(Map<String, Object> gMapgMap, String queryId, String jdbcName) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
