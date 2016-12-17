package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.facade.PMOrgInfoFacade;
import com.cv.kdata.model.PMExitEvent;
import com.cv.kdata.model.PMExitEventDetail;
import com.cv.kdata.model.PMFundInfo;
import com.cv.kdata.model.PMInvestEvent;
import com.cv.kdata.model.PMInvestEventDetail;
import com.cv.kdata.model.PMOrgExtendEntity;
import com.cv.kdata.model.PMOrgInfo;
import com.cv.kdata.model.PMStartupInfo;
import com.cv.kdata.model.PMUserInfo;
import com.cv.kdata.model.RptOrgForcusEnt;
import com.cv.kdata.model.RptOrgOverallTrends;
import com.cv.kdata.model.RptPeFund;

public class OrganizeResponse extends ResponseObject{
	private PMOrgInfoFacade orgInfo;
	private List<PMUserInfo> teams;		//团队列表
	private List<PMOrgInfo> orgList;	//机构列表，用于返回关键字查询
	private List<PMInvestEvent> inventList;  //投资事件列表
	private List<PMExitEvent> exitList;  //退出事件列表
	private PMInvestEventDetail inventEvent;  //投资事件详情
	private PMExitEventDetail exitEvent;  //退出事件详情
	private List<PMFundInfo> fundList;  //退出事件列表
	private List<PMStartupInfo> projectList;  //最新项目列表
	private List<RptOrgForcusEnt> focusList;  //企业关注列表
	private List<RptOrgOverallTrends> tendsList;	//股权投资行为的趋势和边际利润率
	private List<RptPeFund> rptPeFundList;		//上市公司的并购事件
	private List<PMOrgExtendEntity> extendList;		//机构族谱预测
	
	public List<RptPeFund> getRptPeFundList() {
		return rptPeFundList;
	}

	public void setRptPeFundList(List<RptPeFund> rptPeFundList) {
		this.rptPeFundList = rptPeFundList;
	}

	public List<PMOrgExtendEntity> getExtendList() {
		return extendList;
	}

	public void setExtendList(List<PMOrgExtendEntity> extendList) {
		this.extendList = extendList;
	}

	public List<RptOrgOverallTrends> getTendsList() {
		return tendsList;
	}

	public void setTendsList(List<RptOrgOverallTrends> tendsList) {
		this.tendsList = tendsList;
	}

	public List<RptOrgForcusEnt> getFocusList() {
		return focusList;
	}

	public void setFocusList(List<RptOrgForcusEnt> focusList) {
		this.focusList = focusList;
	}

	public List<PMStartupInfo> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<PMStartupInfo> projectList) {
		this.projectList = projectList;
	}

	public List<PMFundInfo> getFundList() {
		return fundList;
	}

	public void setFundList(List<PMFundInfo> fundList) {
		this.fundList = fundList;
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

	public List<PMOrgInfo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<PMOrgInfo> orgList) {
		this.orgList = orgList;
	}

	public List<PMUserInfo> getTeams() {
		return teams;
	}

	public void setTeams(List<PMUserInfo> teams) {
		this.teams = teams;
	}

	public PMOrgInfoFacade getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(PMOrgInfoFacade orgInfo) {
		this.orgInfo = orgInfo;
	}
	
}
