package com.cv.peseer.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.cv.peseer.facade.ReportInfo;



public class ReportResponse extends ResponseObject{
	@JSONField(ordinal = 3)
	private List<ReportInfo> trader_report_list;
	@JSONField(ordinal = 4)
	private List<ReportInfo> cv_report_list;


	public List<ReportInfo> getTrader_report_list() {
		return trader_report_list;
	}

	public void setTrader_report_list(List<ReportInfo> trader_report_list) {
		this.trader_report_list = trader_report_list;
	}

	public List<ReportInfo> getCv_report_list() {
		return cv_report_list;
	}

	public void setCv_report_list(List<ReportInfo> cv_report_list) {
		this.cv_report_list = cv_report_list;
	}
}
