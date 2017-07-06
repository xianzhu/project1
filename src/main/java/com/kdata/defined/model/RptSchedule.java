package com.kdata.defined.model;

import java.util.Date;

public class RptSchedule {
	private int id;
	private Date time;
	private String name;
	private String type;
	private String category;
	private String orgCnShort;
	private String geo;
	private String url;
	private String comment;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOrgCnShort() {
		return orgCnShort;
	}
	public void setOrgCnShort(String orgCnShort) {
		this.orgCnShort = orgCnShort;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
