package com.cv.kdata.model;

public class TypeInfo //行业分类信息
{
	public String type_id; //一级分类
	public String type_name; //一级分类中文名
	public String industry_id;//二级行业Id
	public String industry_name; //二级行业中文名
	
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getIndustry_id() {
		return industry_id;
	}
	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}
	public String getIndustry_name() {
		return industry_name;
	}
	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}
	
	public TypeInfo(String type_id, String type_name, String industry_id, String industry_name) {
		this.type_id = type_id;
		this.type_name = type_name;
		this.industry_id = industry_id;
		this.industry_name = industry_name;
	}
}