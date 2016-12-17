package com.cv.kdata.model.ci;

public class CiDmpDeli {
    private Integer id;

    private String statDate;

    private Double numMonthValue;

    private Double numGrowthRate;

    private Double twogMonthValue;

    private Double twogGrowthRate;

    private Double threegMonthValue;

    private Double threegGrowthRate;

    private Double fourgMonthValue;

    private Double fourgGrowthRate;

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

    public Double getNumMonthValue() {
        return numMonthValue;
    }

    public void setNumMonthValue(Double numMonthValue) {
        this.numMonthValue = numMonthValue;
    }

    public Double getNumGrowthRate() {
        return numGrowthRate;
    }

    public void setNumGrowthRate(Double numGrowthRate) {
        this.numGrowthRate = numGrowthRate;
    }

    public Double getTwogMonthValue() {
        return twogMonthValue;
    }

    public void setTwogMonthValue(Double twogMonthValue) {
        this.twogMonthValue = twogMonthValue;
    }

    public Double getTwogGrowthRate() {
        return twogGrowthRate;
    }

    public void setTwogGrowthRate(Double twogGrowthRate) {
        this.twogGrowthRate = twogGrowthRate;
    }

    public Double getThreegMonthValue() {
        return threegMonthValue;
    }

    public void setThreegMonthValue(Double threegMonthValue) {
        this.threegMonthValue = threegMonthValue;
    }

    public Double getThreegGrowthRate() {
        return threegGrowthRate;
    }

    public void setThreegGrowthRate(Double threegGrowthRate) {
        this.threegGrowthRate = threegGrowthRate;
    }

    public Double getFourgMonthValue() {
        return fourgMonthValue;
    }

    public void setFourgMonthValue(Double fourgMonthValue) {
        this.fourgMonthValue = fourgMonthValue;
    }

    public Double getFourgGrowthRate() {
        return fourgGrowthRate;
    }

    public void setFourgGrowthRate(Double fourgGrowthRate) {
        this.fourgGrowthRate = fourgGrowthRate;
    }
}