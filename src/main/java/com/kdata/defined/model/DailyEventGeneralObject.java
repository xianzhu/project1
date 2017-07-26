package com.kdata.defined.model;

import java.io.Serializable;

import com.cv.kdata.util.StringUtil;




public class DailyEventGeneralObject implements Serializable, Comparable<DailyEventGeneralObject>{
	/**
	 *
	 */
	private static final long serialVersionUID = -1026939089294396183L;
	int eventId;
	String eventTitle;
	String entCnName;
	String eventDesc;
	String eventType;
	String createTime;
	String happenDate;
	String eventClass;   //invest or exit
	String orgCnName;
	String userName;
	String fundName;
	double stockRight;
	double amount;
	//exit event
	String firstInvestDate;
	String totalInvest;
	String bringRate;
	String irr;


	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public double getStockRight() {
		return stockRight;
	}
	public void setStockRight(double stockRight) {
		this.stockRight = stockRight;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFirstInvestDate() {
		return firstInvestDate;
	}
	public void setFirstInvestDate(String firstInvestDate) {
		this.firstInvestDate = firstInvestDate;
	}
	public String getTotalInvest() {
		return totalInvest;
	}
	public void setTotalInvest(String totalInvest) {
		this.totalInvest = totalInvest;
	}
	public String getBringRate() {
		return bringRate;
	}
	public void setBringRate(String bringRate) {
		this.bringRate = bringRate;
	}
	public String getIrr() {
		return irr;
	}
	public void setIrr(String irr) {
		this.irr = irr;
	}
	public String getOrgCnName() {
		return orgCnName;
	}
	public void setOrgCnName(String orgCnName) {
		this.orgCnName = orgCnName;
	}
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	public String getEventClass() {
		return eventClass;
	}
	public void setEventClass(String event) {
		this.eventClass = event;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEntCnName() {
		return entCnName;
	}
	public void setEntCnName(String entCnName) {
		this.entCnName = entCnName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	@Override
	public int compareTo(DailyEventGeneralObject o) {
		// TODO Auto-generated method stub
		if(StringUtil.isNullOrEmpty(this.getCreateTime()) || StringUtil.isNullOrEmpty(o.getCreateTime())){
			return 0;
		}
		int i = o.getCreateTime().compareToIgnoreCase(this.getCreateTime());
		return i;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
