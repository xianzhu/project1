package com.cv.kdata.service;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.conf.ConfigurationHelper;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.LoginInfoMapper;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.LoginInfo;
import com.cv.kdata.model.LoginRequestContext;
import com.cv.kdata.model.RequestConext;
import com.cv.kdata.model.UserInfoWithBLOBs;
import com.cv.kdata.response.LoginLogoutResponse;
import com.cv.kdata.util.LogWriterHelper;
import com.cv.kdata.util.MD5Util;
import com.cv.kdata.util.StringUtil;

@Service
public class LoginLogoutService {
	@Autowired
	LoginInfoMapper loginInfoMapper;
	@Autowired
	UserInfoMapper userInfoMapper;

	private static Logger LOGGER = LoggerFactory.getLogger(LoginLogoutService.class);
	public void login(HttpServletRequest req, LoginLogoutResponse response){
		String username = req.getParameter("user_name");
		String password = req.getParameter("password");

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		LoginInfo loginInfo = loginInfoMapper.selectByUserName(username);

		if(null != loginInfo && MD5Util.strMD5Validation(loginInfo.decryptPwd(), password)){
			response.setStatus("success");
			response.setMessage("登录成功");
			String token = loginInfo.getCookie();
			if(StringUtil.isNullOrEmpty(token)){
				token = UUID.randomUUID().toString();
				loginInfo.setCookie(token);
				loginInfoMapper.updateByPrimaryKey(loginInfo);
			}
			LoginInfoCache.getInstance().putUid(token, loginInfo.getUid());
			UserInfoWithBLOBs user = userInfoMapper.selectByPrimaryKey(loginInfo.getUid());
			if(user != null){
				user.setLastLoginTime(new Date());
				userInfoMapper.updateByPrimaryKeySelective(user);
			}

			response.setToken(token);
			req.getSession().setAttribute(RDDWebConst.TOKEN, token);
			req.getSession().setAttribute(RDDWebConst.USERNAME, username);
			writeLog(req, loginInfo.getUid(), response.getStatus());
		}else{
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("用户名或密码错误");
			LOGGER.info(String.format("The userName = %s and password = %s is not correct!", username,password));
		}
	}

	private void writeLog(HttpServletRequest req, String uid, String status){
		RequestConext requestConext = new RequestConext();
		parseRequest(req, uid, requestConext);
		LoginRequestContext loginRequestContext = new LoginRequestContext(requestConext);

		loginRequestContext.user_name = req.getParameter("user_name");
		loginRequestContext.password = req.getParameter("password");
		loginRequestContext.status = status;

		LogWriterHelper.getInstance().offer(loginRequestContext);
	}

	private void parseRequest(HttpServletRequest req, String uid,RequestConext requestConext) {

		if (uid == null) {
			String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
			LoginInfo loginInfo = loginInfoMapper.selectByCookie(token);
			uid = loginInfo.getUid();
		}

		String client_ip = req.getRemoteAddr();
		requestConext.user_id = uid;
		requestConext.server_id = ConfigurationHelper.SERVER_ID;
		requestConext.client_ip = client_ip;
		requestConext.user_agent = req.getHeader("user-agent");
	}

	public void logout(HttpServletRequest req, LoginLogoutResponse response){
		req.getSession().removeAttribute(RDDWebConst.TOKEN);
		req.getSession().removeAttribute(RDDWebConst.USERNAME);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("logout success!");
	}
}
