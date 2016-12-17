package com.cv.kdata.facade;

import com.alibaba.fastjson.annotation.JSONField;

public class ReportInfo {
	@JSONField(ordinal = 1)
	public String id;
	@JSONField(ordinal = 2)
	public String report_name;
	@JSONField(ordinal = 3)
	public String url;
	@JSONField(ordinal = 4)
	public String rpt_type;
	@JSONField(ordinal = 5)
	public String industry_id;
	@JSONField(ordinal = 6)
	public String industry_type;
	@JSONField(ordinal = 7)
	public String time;
	@JSONField(ordinal = 8)
	public String author;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getIndustry_type() {
		return industry_type;
	}

	public void setIndustry_type(String industry_type) {
		this.industry_type = industry_type;
	}

	public String getRpt_type() {
		return rpt_type;
	}

	public void setRpt_type(String rpt_type) {
		this.rpt_type = rpt_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
