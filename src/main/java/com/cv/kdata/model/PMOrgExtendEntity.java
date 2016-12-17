package com.cv.kdata.model;

import java.io.Serializable;

public class PMOrgExtendEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer orgId;

    private String orgCnShort;

    private String orgPrimeEntity;

    private String orgEntity;

    private String uuid;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCnShort() {
        return orgCnShort;
    }

    public void setOrgCnShort(String orgCnShort) {
        this.orgCnShort = orgCnShort == null ? null : orgCnShort.trim();
    }

    public String getOrgPrimeEntity() {
        return orgPrimeEntity;
    }

    public void setOrgPrimeEntity(String orgPrimeEntity) {
        this.orgPrimeEntity = orgPrimeEntity == null ? null : orgPrimeEntity.trim();
    }

    public String getOrgEntity() {
        return orgEntity;
    }

    public void setOrgEntity(String orgEntity) {
        this.orgEntity = orgEntity == null ? null : orgEntity.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
}