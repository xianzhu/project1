package com.cv.kdata.model.ci;

public class CiCeVecSales {
    private Integer id;

    private String statRate;

    private Double monthValue;

    private Double monthGrowthRate;

    private Double cumValue;

    private Double cumGrowthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatRate() {
        return statRate;
    }

    public void setStatRate(String statRate) {
        this.statRate = statRate == null ? null : statRate.trim();
    }

    public Double getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Double monthValue) {
        this.monthValue = monthValue;
    }

    public Double getMonthGrowthRate() {
        return monthGrowthRate;
    }

    public void setMonthGrowthRate(Double monthGrowthRate) {
        this.monthGrowthRate = monthGrowthRate;
    }

    public Double getCumValue() {
        return cumValue;
    }

    public void setCumValue(Double cumValue) {
        this.cumValue = cumValue;
    }

    public Double getCumGrowthRate() {
        return cumGrowthRate;
    }

    public void setCumGrowthRate(Double cumGrowthRate) {
        this.cumGrowthRate = cumGrowthRate;
    }
}