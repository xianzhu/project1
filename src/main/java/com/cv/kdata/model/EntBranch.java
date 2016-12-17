package com.cv.kdata.model;

import java.io.Serializable;

public class EntBranch implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String bUuid;

    private String bName;

    private String bLegal;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getbUuid() {
        return bUuid;
    }

    public void setbUuid(String bUuid) {
        this.bUuid = bUuid == null ? null : bUuid.trim();
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    public String getbLegal() {
        return bLegal;
    }

    public void setbLegal(String bLegal) {
        this.bLegal = bLegal == null ? null : bLegal.trim();
    }
}