package com.cv.peseer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.LoginInfoMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.LoginInfo;
import com.cv.peseer.response.UserInfoResponse;

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
