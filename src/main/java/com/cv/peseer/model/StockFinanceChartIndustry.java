package com.cv.peseer.model;

import java.io.Serializable;

public class StockFinanceChartIndustry implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String emIndustry;

    private String reportDate;

    private String financeItem;

    private Double maxValue;

    private Double minValue;

    private Double avgValue;

    private Double percentile90;

    private Double percentile10;

    public String getEmIndustry() {
        return emIndustry;
    }

    public void setEmIndustry(String emIndustry) {
        this.emIndustry = emIndustry == null ? null : emIndustry.trim();
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

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(Double avgValue) {
        this.avgValue = avgValue;
    }

    public Double getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(Double percentile90) {
        this.percentile90 = percentile90;
    }

    public Double getPercentile10() {
        return percentile10;
    }

    public void setPercentile10(Double percentile10) {
        this.percentile10 = percentile10;
    }
}