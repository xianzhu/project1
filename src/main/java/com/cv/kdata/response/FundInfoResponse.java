package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.model.PMExitEvent;
import com.cv.kdata.model.PMExitEventDetail;
import com.cv.kdata.model.PMFundInfo;
import com.cv.kdata.model.PMInvestEvent;
import com.cv.kdata.model.PMInvestEventDetail;

public class FundInfoResponse  extends ResponseObject{
	List<PMFundInfo> fundList;	//基金列表，用于返回基金信息
	PMFundInfo fundInfo;	//基金详情
	private List<PMInvestEvent> inventList;  //投资事件列表
	private List<PMExitEvent> exitList;  //退出事件列表
	private PMInvestEventDetail inventEvent;  //投资事件详情
	private PMExitEventDetail exitEvent;  //退出事件详情

	public List<PMInvestEvent> getInventList() {
		return inventList;
	}

	public void setInventList(List<PMInvestEvent> inventList) {
		this.inventList = inventList;
	}

	public List<PMExitEvent> getExitList() {
		return exitList;
	}

	public void setExitList(List<PMExitEvent> exitList) {
		this.exitList = exitList;
	}

	public PMInvestEventDetail getInventEvent() {
		return inventEvent;
	}

	public void setInventEvent(PMInvestEventDetail inventEvent) {
		this.inventEvent = inventEvent;
	}

	public PMExitEventDetail getExitEvent() {
		return exitEvent;
	}

	public void setExitEvent(PMExitEventDetail exitEvent) {
		this.exitEvent = exitEvent;
	}

	public PMFundInfo getFundInfo() {
		return fundInfo;
	}

	public void setFundInfo(PMFundInfo fundInfo) {
		this.fundInfo = fundInfo;
	}

	public List<PMFundInfo> getFundList() {
		return fundList;
	}

	public void setFundList(List<PMFundInfo> fundList) {
		this.fundList = fundList;
	}
	
}
