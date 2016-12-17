package com.cv.kdata.model;

import java.io.Serializable;

public class StockEquityCtrl implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String name;

    private String eqComp;

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

    public String getEqComp() {
        return eqComp;
    }

    public void setEqComp(String eqComp) {
        this.eqComp = eqComp == null ? null : eqComp.trim();
    }
}