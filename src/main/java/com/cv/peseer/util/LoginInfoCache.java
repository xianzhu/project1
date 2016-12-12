package com.cv.peseer.util;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cv.peseer.cont.RDDWebConst;


public class LoginInfoCache {
	private static LoginInfoCache instance = new LoginInfoCache();
	private Map<String, String> loginMap = new HashMap<>();

	public static LoginInfoCache getInstance() {
		return instance;
	}

	private LoginInfoCache(){
		ResultSet rs = null;
		String sql = "select cookie, uid from login_info";
		try{
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).getResultSet(sql);
			while (rs.next()){
				loginMap.put(rs.getString("cookie"), rs.getString("uid"));

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).close(rs);
		}
	}

	public String getUid(String cookie){
		return loginMap.get(cookie);
	}

	public void putUid(String cookie, String uid){
		loginMap.put(cookie, uid);
	}
}
