package com.cv.peseer.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cv.peseer.datasource.DBContextHolder;

public class SqlSessionUtil {
	static private SqlSessionFactory sessionFactory;

	public static SqlSession getSqlSession(String db) {
		if (sessionFactory == null) {
			String resource = "spring-mybatis.xml";
			Reader reader;
			try {
				reader = Resources.getResourceAsReader(resource);
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DBContextHolder.setDbType(db);
		return sessionFactory.openSession();

	}
}
