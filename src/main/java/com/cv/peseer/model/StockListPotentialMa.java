package com.cv.peseer.model;

import java.io.Serializable;

public class StockListPotentialMa implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String listStockCode;

    private String xsbStockCode;

    private Byte isListMa;

    private Byte isXsbMa;

    public String getListStockCode() {
        return listStockCode;
    }

    public void setListStockCode(String listStockCode) {
        this.listStockCode = listStockCode == null ? null : listStockCode.trim();
    }

    public String getXsbStockCode() {
        return xsbStockCode;
    }

    public void setXsbStockCode(String xsbStockCode) {
        this.xsbStockCode = xsbStockCode == null ? null : xsbStockCode.trim();
    }

    public Byte getIsListMa() {
        return isListMa;
    }

    public void setIsListMa(Byte isListMa) {
        this.isListMa = isListMa;
    }

    public Byte getIsXsbMa() {
        return isXsbMa;
    }

    public void setIsXsbMa(Byte isXsbMa) {
        this.isXsbMa = isXsbMa;
    }
}