package com.cv.kdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.LoginInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.LoginInfo;
import com.cv.kdata.response.UserInfoResponse;

@Service
public class UserInfoService {
	@Autowired
	LoginInfoMapper loginInfoMapper;

	public void getSimpleInfo(String token, UserInfoResponse personResponse) {

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		LoginInfo login = loginInfoMapper.selectByCookie(token);

		if(login == null){
			personResponse.setStatus(RDDWebConst.FAILURE);
			personResponse.setMessage("token is invalid!");
			return;
		}

		personResponse.setOrganizeName(login.getOrgName());
		personResponse.setUserName(login.getUid());
		personResponse.setScore(login.getScore());

		personResponse.setStatus(RDDWebConst.SUCCESS);
		personResponse.setMessage("get simple personal info success!");
	}
}
