package com.cv.peseer.model;

import java.io.Serializable;

public class EntStockInstInvest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String name;

    private String instInv;

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

    public String getInstInv() {
        return instInv;
    }

    public void setInstInv(String instInv) {
        this.instInv = instInv == null ? null : instInv.trim();
    }
}