package com.cv.kdata.response;

public class TrendResponse extends ResponseObject{

	private Object orgTrend;
	private Object mergeTrend;
	private Object fundTrend;
	private Object eventTrend;

	public Object getOrgTrend() {
		return orgTrend;
	}
	public void setOrgTrend(Object orgTrend) {
		this.orgTrend = orgTrend;
	}
	public Object getMergeTrend() {
		return mergeTrend;
	}
	public void setMergeTrend(Object mergeTrend) {
		this.mergeTrend = mergeTrend;
	}
	public Object getFundTrend() {
		return fundTrend;
	}
	public void setFundTrend(Object fundTrend) {
		this.fundTrend = fundTrend;
	}
	public Object getEventTrend() {
		return eventTrend;
	}
	public void setEventTrend(Object eventTrend) {
		this.eventTrend = eventTrend;
	}
}
