package com.nkw.note.cmmn.transaction.dao;

import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkw.note.cmmn.util.GCloudConnector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class TransactionDao {
private static final Logger log = LoggerFactory.getLogger(TransactionDao.class);

//	@Autowired
//	public GCloudConnector conn;
	
	@Resource(name = "sqlMapClientCommon")
	protected SqlSessionTemplate templateMydb;
	
	private final String JDBC_NAME_DB = "mydb";
	
	public SqlSessionTemplate getDao(String jdbcName) {
		if (jdbcName.toLowerCase().equals(JDBC_NAME_DB)) {
			return this.templateMydb;
		}
		return this.templateMydb;
	}

	public List<Map<String, Object>> selectList(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		log.debug("### TransactionDao.selectList");
		log.debug("### gMap : {}",gMap);
		return getDao(jdbcName).selectList(queryId, gMap);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMap(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		return (Map<String, Object>) getDao(jdbcName).selectOne(queryId, gMap);
	}

	public String selectString(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		return (String) getDao(jdbcName).selectOne(queryId, gMap);
	}

	public int selectInt(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		return ((Integer) getDao(jdbcName).selectOne(queryId, gMap)).intValue();
	}

	public int insert(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		return getDao(jdbcName).insert(queryId, gMap);
	}
	
	public int insertList(List<Map<String, Object>> list, String queryId, String jdbcName) throws Exception {
		return getDao(jdbcName).insert(queryId, list);
	}

	public void update(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		getDao(jdbcName).update(queryId, gMap);
	}

	public void delete(Map<String, Object> gMap, String queryId, String jdbcName) throws Exception {
		getDao(jdbcName).delete(queryId, gMap);
	}
}
