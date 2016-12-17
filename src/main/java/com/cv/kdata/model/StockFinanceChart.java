package com.cv.kdata.model;

import java.io.Serializable;

public class StockFinanceChart implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String reportDate;

    private String financeItem;

    private Double financeValue;

    private String emIndustry;

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

    public String getFinanceItem() {
        return financeItem;
    }

    public void setFinanceItem(String financeItem) {
        this.financeItem = financeItem == null ? null : financeItem.trim();
    }

    public Double getFinanceValue() {
        return financeValue;
    }

    public void setFinanceValue(Double financeValue) {
        this.financeValue = financeValue;
    }

    public String getEmIndustry() {
        return emIndustry;
    }

    public void setEmIndustry(String emIndustry) {
        this.emIndustry = emIndustry == null ? null : emIndustry.trim();
    }
}