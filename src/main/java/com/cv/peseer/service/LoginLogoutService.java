package com.cv.peseer.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.conf.ConfigurationHelper;
import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.LoginInfoMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.LoginInfo;
import com.cv.peseer.model.LoginRequestContext;
import com.cv.peseer.model.RequestConext;
import com.cv.peseer.response.LoginLogoutResponse;
import com.cv.peseer.util.LogWriterHelper;
import com.cv.peseer.util.LoginInfoCache;
import com.cv.peseer.util.StringUtil;

@Service
public class LoginLogoutService {
	@Autowired
	LoginInfoMapper loginInfoMapper;
	private static Logger LOGGER = LoggerFactory.getLogger(LoginLogoutService.class);
	public void login(HttpServletRequest req, LoginLogoutResponse response){
		String username = req.getParameter("user_name");
		String password = req.getParameter("password");

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		LoginInfo loginInfo = loginInfoMapper.selectByUser(username, password);

		if(loginInfo != null){
			response.setStatus("success");
			response.setMessage("登录成功");
			String token = loginInfo.getCookie();
			if(StringUtil.isNullOrEmpty(token)){
				token = UUID.randomUUID().toString();
				loginInfo.setCookie(token);
				loginInfoMapper.insertSelective(loginInfo);
			}
			LoginInfoCache.getInstance().putUid(token, loginInfo.getUid());
			response.setToken(token);
			req.getSession().setAttribute(RDDWebConst.TOKEN, token);
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
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("logout success!");
	}
}
