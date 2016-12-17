package com.cv.kdata.model;

import java.io.Serializable;

public class EntBasicInfo implements Serializable {
    @Override
	public String toString() {
		return "EntBasicInfo [entId=" + entId + ", entName=" + entName + ", econKind=" + econKind + ", regCapital="
				+ regCapital + ", unit=" + unit + ", optTimeStart=" + optTimeStart + ", optTimeEnd=" + optTimeEnd
				+ ", regAuth=" + regAuth + ", legalPerson=" + legalPerson + ", checkDate=" + checkDate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", bizStat=" + bizStat + ", orgCode=" + orgCode + ", creditCode="
				+ creditCode + ", regCode=" + regCode + ", province=" + province + ", city=" + city + ", tel=" + tel
				+ ", email=" + email + ", regAddr=" + regAddr + ", operAddr=" + operAddr + ", pDomain=" + pDomain
				+ ", bizArea=" + bizArea + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805109071529466347L;

	private String entId;

    private String entName;

    private String econKind;

    private String regCapital;

    private String unit;

    private String optTimeStart;

    private String optTimeEnd;

    private String regAuth;

    private String legalPerson;

    private String checkDate;

    private String startDate;

    private String endDate;

    private String bizStat;

    private String orgCode;

    private String creditCode;

    private String regCode;

    private String province;

    private String city;

    private String tel;

    private String email;

    private String regAddr;

    private String operAddr;

    private String pDomain;

    private String bizArea;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId == null ? null : entId.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getEconKind() {
        return econKind;
    }

    public void setEconKind(String econKind) {
        this.econKind = econKind == null ? null : econKind.trim();
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital == null ? null : regCapital.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getOptTimeStart() {
        return optTimeStart;
    }

    public void setOptTimeStart(String optTimeStart) {
        this.optTimeStart = optTimeStart == null ? null : optTimeStart.trim();
    }

    public String getOptTimeEnd() {
        return optTimeEnd;
    }

    public void setOptTimeEnd(String optTimeEnd) {
        this.optTimeEnd = optTimeEnd == null ? null : optTimeEnd.trim();
    }

    public String getRegAuth() {
        return regAuth;
    }

    public void setRegAuth(String regAuth) {
        this.regAuth = regAuth == null ? null : regAuth.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate == null ? null : checkDate.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getBizStat() {
        return bizStat;
    }

    public void setBizStat(String bizStat) {
        this.bizStat = bizStat == null ? null : bizStat.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    public String getOperAddr() {
        return operAddr;
    }

    public void setOperAddr(String operAddr) {
        this.operAddr = operAddr == null ? null : operAddr.trim();
    }

    public String getpDomain() {
        return pDomain;
    }

    public void setpDomain(String pDomain) {
        this.pDomain = pDomain == null ? null : pDomain.trim();
    }

    public String getBizArea() {
        return bizArea;
    }

    public void setBizArea(String bizArea) {
        this.bizArea = bizArea == null ? null : bizArea.trim();
    }
}