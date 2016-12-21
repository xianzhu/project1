package com.cv.kdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.UserInfoWithBLOBs;
import com.cv.kdata.response.UserInfoResponse;
import com.cv.kdata.util.StringUtil;

@Service
public class UserInfoService {
	@Autowired
	UserInfoMapper userInfoMapper;

	public void getSimpleInfo(String token, UserInfoResponse personResponse) {

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		UserInfoWithBLOBs userInfo = null;
		if(!StringUtil.isNullOrEmpty(token)){
			String uid = LoginInfoCache.getInstance().getUid(token);
			if(!StringUtil.isNullOrEmpty(uid)){
				userInfo = userInfoMapper.selectByPrimaryKey(uid);
			}
		}

		if(userInfo == null){
			personResponse.setStatus(RDDWebConst.FAILURE);
			personResponse.setMessage("token is invalid!");
			return;
		}

		personResponse.setOrganizeName(userInfo.getOrgName());
		personResponse.setUserName(userInfo.getUid());
		personResponse.setScore(userInfo.getScore());

		personResponse.setStatus(RDDWebConst.SUCCESS);
		personResponse.setMessage("get simple personal info success!");
	}
}
