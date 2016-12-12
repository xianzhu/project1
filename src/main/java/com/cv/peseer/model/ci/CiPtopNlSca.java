package com.cv.peseer.model.ci;

public class CiPtopNlSca {
    private Integer id;

    private String statDate;

    private Double dealYearValue;

    private Double dealGrowthRate;

    private Double numYearValue;

    private Double numGrowthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate == null ? null : statDate.trim();
    }

    public Double getDealYearValue() {
        return dealYearValue;
    }

    public void setDealYearValue(Double dealYearValue) {
        this.dealYearValue = dealYearValue;
    }

    public Double getDealGrowthRate() {
        return dealGrowthRate;
    }

    public void setDealGrowthRate(Double dealGrowthRate) {
        this.dealGrowthRate = dealGrowthRate;
    }

    public Double getNumYearValue() {
        return numYearValue;
    }

    public void setNumYearValue(Double numYearValue) {
        this.numYearValue = numYearValue;
    }

    public Double getNumGrowthRate() {
        return numGrowthRate;
    }

    public void setNumGrowthRate(Double numGrowthRate) {
        this.numGrowthRate = numGrowthRate;
    }
}