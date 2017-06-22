package com.kdata.defined.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Information implements Comparable<Information>{
	@JSONField(ordinal = 1)
	public String channel_id;// 行业二级分类
	@JSONField(ordinal = 2)
	public String channel_name;// 行业二级分类中文名
	@JSONField(ordinal = 3)
	public String title;
	@JSONField(ordinal = 4)
	public String content;
	@JSONField(ordinal = 5)
	public String url;
	@JSONField(ordinal = 6)
	public String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Information() {

	}

	public Information(String channel_id, String channel_name, String title,
			String content, String url, String time) {
		this.channel_id = channel_id;
		this.channel_name = channel_name;
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
		return "Information [channel_id=" + channel_id
				+ ", channel_name=" + channel_name + ", title=" + title + ", content=" + content + ", url=" + url + "]";
	}
}
