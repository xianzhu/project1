package com.cv.kdata.response;

import com.alibaba.fastjson.annotation.JSONField;

public class ResponseObject {
	@JSONField(ordinal = 1)
	String status;
	@JSONField(ordinal = 2)
	String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
