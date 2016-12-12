package com.cv.peseer.model.ci;

public class CiMiaData {
    private Integer id;

    private String statDate;

    private Double monthValue;

    private Double growthRate;

    private Double cumValue;

    private Double cumGrowthRate;

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

    public Double getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Double monthValue) {
        this.monthValue = monthValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
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