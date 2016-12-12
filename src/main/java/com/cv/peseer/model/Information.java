package com.cv.peseer.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class Information implements Comparable<Information>{
	@JSONField(ordinal = 1)
	public String type_id; // 行业一级分类
	@JSONField(ordinal = 2)
	public String type_name;// 行业一级分类中文名
	@JSONField(ordinal = 3)
	public String channel_id;// 行业二级分类
	@JSONField(ordinal = 4)
	public String channel_name;// 行业二级分类中文名
	@JSONField(ordinal = 5)
	public String title;
	@JSONField(ordinal = 6)
	public String content;
	@JSONField(ordinal = 7)
	public String url;
	@JSONField(ordinal = 8)
	public String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Information() {

	}

	public Information(String type_id, String type_name, String channel_id, String channel_name, String title,
			String content, String url, String time) {
		this.type_id = type_id;
		this.type_name = type_name;
		this.channel_id = channel_id;
		this.channel_name = channel_name;
		this.title = title;
		this.content = content;
		this.url = url;
		this.time = time;
	}

	public Information(TypeInfo typeInfo, String title, String content, String url, String time) {
		if(typeInfo != null){
			this.type_id = typeInfo.type_id;
			this.type_name = typeInfo.type_name;
			this.channel_id = typeInfo.industry_id;
			this.channel_name = typeInfo.industry_name;
		}
		this.title = title;
		this.content = content;
		this.url = url;
		this.time = time;
	}
	
	@Override  
	public int compareTo(Information o){
//		int i = this.getTime().compareToIgnoreCase(o.getTime());
		int i = o.getTime().compareToIgnoreCase(this.getTime());
		return i;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Information other = (Information) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<Information> fromSolrResponse() {
		return null;
	}

	@Override
	public String toString() {
		return "Information [type_id=" + type_id + ", type_name=" + type_name + ", channel_id=" + channel_id
				+ ", channel_name=" + channel_name + ", title=" + title + ", content=" + content + ", url=" + url + "]";
	}
}
