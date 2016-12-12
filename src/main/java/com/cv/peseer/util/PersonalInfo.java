package com.cv.peseer.util;

import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.model.LoginInfo;

public class PersonalInfo {
	private static Logger logger = LoggerFactory.getLogger(PersonalInfo.class);
	
	public static LoginInfo getLoginInfo(String token){
		LoginInfo login = new LoginInfo();
		logger.info("search the information_customize for token");
		String sql = "select * from login_info where cookie=?";
		Object[] parameters = { token };
		ResultSet rs = null;
		try {
		rs = MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeQuery(sql, parameters);
		if(rs.next()){
			login = (LoginInfo) BeanConverter.convert(rs, LoginInfo.class);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Search Error" + e);
		}finally{
			MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).close(rs);
		}
        return login;
	}
}
