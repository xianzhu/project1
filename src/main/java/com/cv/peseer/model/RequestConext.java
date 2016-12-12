package com.cv.peseer.model;

public class RequestConext {
	public String  server_id;
	public String  user_id;
	public String client_ip;
	public String user_agent;
	public long state_id;
	public long province_id;
	public long city_id;
	
	
	public RequestConext(){
		
	}
	
	public RequestConext(RequestConext requestConext){
		this.server_id = requestConext.server_id;
		this.user_id = requestConext.user_id;
		this.client_ip = requestConext.client_ip;
		this.user_agent = requestConext.user_agent;
		this.state_id = requestConext.state_id;
		this.province_id = requestConext.province_id;
		this.city_id = requestConext.city_id;
	}
	
	@Override
	public String toString(){
		return null;
	}
	
	
	public String getLogFileName(){
		return null;
	}
	
	public String getLogFolderName(){
		return null;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
