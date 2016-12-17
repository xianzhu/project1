package com.cv.kdata.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cv.kdata.dao.LoginInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.LoginInfo;

@Component
public class AuthcationUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthcationUtil.class);

	private static LoginInfoMapper loginInfoMapper;
	private static Map<String,LoginInfo> loginMap = new HashMap<String,LoginInfo>();

	@Autowired(required = true)
	public void setLoginInfoMapper(LoginInfoMapper loginInfoMapper) {
		AuthcationUtil.loginInfoMapper = loginInfoMapper;
	}
	public static boolean isTokenValid(String token) {
		//如果token为空
		if (StringUtils.isEmpty(token)){
			return false;
		}

		boolean validate = false;
		if(loginMap.get(token) != null ){
			return true;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		LoginInfo loginInfo= loginInfoMapper.selectByCookie(token);
		if(loginInfo != null){
			validate = true;
			loginMap.put(token, loginInfo);
		}
		return validate;
	}
}
