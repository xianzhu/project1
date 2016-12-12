package com.cv.peseer.response;

import java.util.List;

import com.cv.peseer.model.UdfConsult;
import com.cv.peseer.model.UdfEvent;
import com.cv.peseer.model.UdfMonitor;
import com.cv.peseer.model.UdfRptCV;
import com.cv.peseer.model.UdfRptTrader;

public class UserInfoCustResponse extends ResponseObject{
	private List<UdfEvent> eventList;   //事件列表，用于搜索
	private List<UdfRptCV> cvList;	//行业分析报告列表
	private List<UdfRptTrader> traderList;	//研究报告列表
	private List<UdfConsult> consultList;	//业务咨询列表
	private List<UdfMonitor> monitorList;	//监控列表

	public List<UdfRptCV> getCvList() {
		return cvList;
	}

	public void setCvList(List<UdfRptCV> cvList) {
		this.cvList = cvList;
	}

	public List<UdfRptTrader> getTraderList() {
		return traderList;
	}

	public void setTraderList(List<UdfRptTrader> traderList) {
		this.traderList = traderList;
	}

	public List<UdfConsult> getConsultList() {
		return consultList;
	}

	public void setConsultList(List<UdfConsult> consultList) {
		this.consultList = consultList;
	}

	public List<UdfMonitor> getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(List<UdfMonitor> monitorList) {
		this.monitorList = monitorList;
	}

	public List<UdfEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<UdfEvent> eventList) {
		this.eventList = eventList;
	}
	
}
