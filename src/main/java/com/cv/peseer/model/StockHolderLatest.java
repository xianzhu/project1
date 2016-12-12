package com.cv.peseer.model;

import java.io.Serializable;

public class StockHolderLatest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String reportDate;

    private String holderType;

    private String stockChar;

    private String qty;

    private String holderChar;

    private String stockRatio;

    private String stockRatioChange;

    private String qtyChange;

    private String holderName;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    public String getStockChar() {
        return stockChar;
    }

    public void setStockChar(String stockChar) {
        this.stockChar = stockChar == null ? null : stockChar.trim();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty == null ? null : qty.trim();
    }

    public String getHolderChar() {
        return holderChar;
    }

    public void setHolderChar(String holderChar) {
        this.holderChar = holderChar == null ? null : holderChar.trim();
    }

    public String getStockRatio() {
        return stockRatio;
    }

    public void setStockRatio(String stockRatio) {
        this.stockRatio = stockRatio == null ? null : stockRatio.trim();
    }

    public String getStockRatioChange() {
        return stockRatioChange;
    }

    public void setStockRatioChange(String stockRatioChange) {
        this.stockRatioChange = stockRatioChange == null ? null : stockRatioChange.trim();
    }

    public String getQtyChange() {
        return qtyChange;
    }

    public void setQtyChange(String qtyChange) {
        this.qtyChange = qtyChange == null ? null : qtyChange.trim();
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }
}