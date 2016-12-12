package com.cv.peseer.response;

import java.util.List;
import java.util.Map;


public class CiAssociatedResponse extends ResponseObject{
	private List<Object> object;
	private Map<String,String> propertities;
	public List<Object> getObject() {
		return object;
	}
	public void setObject(List<Object> object) {
		this.object = object;
	}
	public Map<String, String> getPropertities() {
		return propertities;
	}
	public void setPropertities(Map<String, String> propertities) {
		this.propertities = propertities;
	}
}
