package com.cv.kdata.model;

import java.io.Serializable;

public class IdEntStock implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String stockCode;

    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}