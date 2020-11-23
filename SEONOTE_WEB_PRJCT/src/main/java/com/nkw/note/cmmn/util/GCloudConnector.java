package com.nkw.note.cmmn.util;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class GCloudConnector {
	
	public static SqlSessionFactory sqlSessionFactory;
	
//	@PostConstruct
	public void initConnectionPool() throws Exception {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(String.format("jdbc:mysql:///%s", "34.64.215.127:3306"));
		config.setUsername("naru_memo");
		config.setPassword("0wMzvMg84guGs5ba");
		DataSource dataSource = new HikariDataSource(config);
		try {
			sqlSessionFactory.openSession(dataSource.getConnection());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
