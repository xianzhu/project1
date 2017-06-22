package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.model.PMExitEvent;
import com.cv.kdata.model.PMExitEventDetail;
import com.cv.kdata.model.PMInvestEvent;
import com.cv.kdata.model.PMInvestEventDetail;
import com.cv.kdata.model.PMUserInfo;
import com.cv.kdata.model.RptAngelList;
import com.cv.kdata.model.RptOrgerList;
import com.kdata.defined.model.Information;

public class InvestorInfoResponse extends ResponseObject{
	private List<PMUserInfo> userList;  //投资者列表，用于搜索返回
	private PMUserInfo investor; //投资者
	private List<PMInvestEvent> inventList;  //投资事件列表
	private List<PMExitEvent> exitList;  //退出事件列表
	private PMInvestEventDetail inventEvent;  //投资事件详情
	private PMExitEventDetail exitEvent;  //退出事件详情
	private List<RptAngelList> angelList; //天使投资人榜单
	private List<RptOrgerList> orglList; //机构投资人榜单

	public List<RptAngelList> getAngelList() {
		return angelList;
	}

	public void setAngelList(List<RptAngelList> angelList) {
		this.angelList = angelList;
	}

	public List<RptOrgerList> getOrglList() {
		return orglList;
	}

	public void setOrglList(List<RptOrgerList> orglList) {
		this.orglList = orglList;
	}

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

	public PMUserInfo getInvestor() {
		return investor;
	}

	public void setInvestor(PMUserInfo investor) {
		this.investor = investor;
	}

	public List<PMUserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<PMUserInfo> userList) {
		this.userList = userList;
	}

	/**
	 * 情报
	 */
	private List<Information> intelligence;

	public List<Information> getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(List<Information> intelligence) {
		this.intelligence = intelligence;
	}



}
