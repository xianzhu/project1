package com.cv.kdata.model;

import java.io.Serializable;

public class PMInvestEventDetail implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer eventId;

    private String eventTitle;

    private String happenDate;

    private String investType;

    private String entCnName;

    private String entUuid;

    private String fundId;

    private String fundUuid;

    private String fundName;

    private String userId;

    private String userName;

    private String orgId;

    private String orgCnName;

    private String orgCnShort;

    private Double amount;

    private Double stockRight;

    private String eventDesc;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle == null ? null : eventTitle.trim();
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    public String getEntCnName() {
        return entCnName;
    }

    public void setEntCnName(String entCnName) {
        this.entCnName = entCnName == null ? null : entCnName.trim();
    }

    public String getEntUuid() {
        return entUuid;
    }

    public void setEntUuid(String entUuid) {
        this.entUuid = entUuid == null ? null : entUuid.trim();
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }

    public String getFundUuid() {
        return fundUuid;
    }

    public void setFundUuid(String fundUuid) {
        this.fundUuid = fundUuid == null ? null : fundUuid.trim();
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgCnName() {
        return orgCnName;
    }

    public void setOrgCnName(String orgCnName) {
        this.orgCnName = orgCnName == null ? null : orgCnName.trim();
    }

    public String getOrgCnShort() {
        return orgCnShort;
    }

    public void setOrgCnShort(String orgCnShort) {
        this.orgCnShort = orgCnShort == null ? null : orgCnShort.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getStockRight() {
        return stockRight;
    }

    public void setStockRight(Double stockRight) {
        this.stockRight = stockRight;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }
}