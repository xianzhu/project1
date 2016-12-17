package com.cv.kdata.model;

import java.io.Serializable;

public class EntHolder implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String name;

    private String stockType;
    
    private String rCapi;

    private String sCapi;

    public String getrCapi() {
        return rCapi;
    }

    public void setrCapi(String rCapi) {
        this.rCapi = rCapi == null ? null : rCapi.trim();
    }

    public String getsCapi() {
        return sCapi;
    }

    public void setsCapi(String sCapi) {
        this.sCapi = sCapi == null ? null : sCapi.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }
}