package com.cv.peseer.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.cv.peseer.conf.ConfigurationHelper;
import com.cv.peseer.util.ThreadSafeSDFUtil;


public class LoginRequestContext extends RequestConext {
	public String user_name;
	public String password;
	public String status;
	
	public LoginRequestContext(){
	}
	
	public LoginRequestContext(RequestConext requestConext){
		super(requestConext);
	}
	
	@Override
	public String toString() {
		final String SEPARATOR = "|";
		StringBuilder sb = new StringBuilder();
		sb.append(server_id).append(SEPARATOR);
		sb.append(ThreadSafeSDFUtil.getDateTimeFormat().format(new Date())).append(SEPARATOR);
		sb.append(client_ip).append(SEPARATOR);
		sb.append(state_id).append(SEPARATOR);
		sb.append(province_id).append(SEPARATOR);
		sb.append(city_id).append(SEPARATOR);
		sb.append(user_name).append(SEPARATOR);
		sb.append(password).append(SEPARATOR);
		sb.append(user_id).append(SEPARATOR);
		sb.append(status).append(SEPARATOR);
		try {
			sb.append(URLEncoder.encode(user_agent, "UTF-8")).append(SEPARATOR);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String getLogFileName() {
		return "login.log";
	}

	@Override
	public String getLogFolderName() {
		return ConfigurationHelper.ACCESS_FOLDER_NAME;
	}
	
	
	
	
}
