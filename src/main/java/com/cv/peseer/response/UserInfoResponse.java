package com.cv.peseer.response;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfoResponse extends ResponseObject{
	@JSONField(ordinal = 1)
	public String organizeName;
	@JSONField(ordinal = 2)
	public String userName;
	@JSONField(ordinal = 3)
	public int score;

	public String getOrganizeName() {
		return organizeName;
	}

	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
