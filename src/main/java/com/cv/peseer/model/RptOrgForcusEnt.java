package com.cv.peseer.model;

import java.io.Serializable;

public class RptOrgForcusEnt implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer orgId;

    private String entCnName;

    private String entUuid;

    private String happenDate;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate == null ? null : happenDate.trim();
    }
}