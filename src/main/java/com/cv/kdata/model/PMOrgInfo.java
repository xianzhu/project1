package com.cv.kdata.model;

import java.io.Serializable;

public class PMOrgInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer orgId;

    private String orgCnName;

    private String orgCnShort;

    private String orgNickname;

    private String orgEnName;

    private String orgMapEntity;

    private String fundInfo;

    private String investInfo;

    private String orgCnDesc;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String getOrgNickname() {
        return orgNickname;
    }

    public void setOrgNickname(String orgNickname) {
        this.orgNickname = orgNickname == null ? null : orgNickname.trim();
    }

    public String getOrgEnName() {
        return orgEnName;
    }

    public void setOrgEnName(String orgEnName) {
        this.orgEnName = orgEnName == null ? null : orgEnName.trim();
    }

    public String getOrgMapEntity() {
        return orgMapEntity;
    }

    public void setOrgMapEntity(String orgMapEntity) {
        this.orgMapEntity = orgMapEntity == null ? null : orgMapEntity.trim();
    }

    public String getFundInfo() {
        return fundInfo;
    }

    public void setFundInfo(String fundInfo) {
        this.fundInfo = fundInfo == null ? null : fundInfo.trim();
    }

    public String getInvestInfo() {
        return investInfo;
    }

    public void setInvestInfo(String investInfo) {
        this.investInfo = investInfo == null ? null : investInfo.trim();
    }

    public String getOrgCnDesc() {
        return orgCnDesc;
    }

    public void setOrgCnDesc(String orgCnDesc) {
        this.orgCnDesc = orgCnDesc == null ? null : orgCnDesc.trim();
    }
}