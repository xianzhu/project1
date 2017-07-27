package com.kdata.defined.model;

import java.util.Map;

public class TrackModel {

	private String session;
	private String userName;
	private String uri;
	private String time;
	private Map<String,String[]> paras;
	private String status;
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Map<String, String[]> getParas() {
		return paras;
	}
	public void setParas(Map<String, String[]> paras) {
		this.paras = paras;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
