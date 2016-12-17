package com.cv.kdata.model;

import java.io.Serializable;

public class StockXsbListMatch implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String xsbStockCode;

    private String listStockCode;

    private Double diff;

    private Byte isXsbMatching;

    private Byte isListMatching;

    public String getXsbStockCode() {
        return xsbStockCode;
    }

    public void setXsbStockCode(String xsbStockCode) {
        this.xsbStockCode = xsbStockCode == null ? null : xsbStockCode.trim();
    }

    public String getListStockCode() {
        return listStockCode;
    }

    public void setListStockCode(String listStockCode) {
        this.listStockCode = listStockCode == null ? null : listStockCode.trim();
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public Byte getIsXsbMatching() {
        return isXsbMatching;
    }

    public void setIsXsbMatching(Byte isXsbMatching) {
        this.isXsbMatching = isXsbMatching;
    }

    public Byte getIsListMatching() {
        return isListMatching;
    }

    public void setIsListMatching(Byte isListMatching) {
        this.isListMatching = isListMatching;
    }
}