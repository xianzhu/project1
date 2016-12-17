package com.cv.kdata.model;

import java.io.Serializable;

public class RptPeFund implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String fundName;

    private String fundUuid;

    private String regTime;

    private String manager;

    private String mUuid;

    private String domain;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    public String getFundUuid() {
        return fundUuid;
    }

    public void setFundUuid(String fundUuid) {
        this.fundUuid = fundUuid == null ? null : fundUuid.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime == null ? null : regTime.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getmUuid() {
        return mUuid;
    }

    public void setmUuid(String mUuid) {
        this.mUuid = mUuid == null ? null : mUuid.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }
}