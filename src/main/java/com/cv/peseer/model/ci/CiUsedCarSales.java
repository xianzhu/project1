package com.cv.peseer.model.ci;

public class CiUsedCarSales {
    private Integer id;

    private String statDate;

    private Double numMonthValue;

    private Double numGrowthRate;

    private Double numCumValue;

    private Double numCumGrowthRate;

    private Double scaMonthValue;

    private Double scaGrowthRate;

    private Double scaCumValue;

    private Double scaCumGrowthRate;

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

    public Double getNumCumValue() {
        return numCumValue;
    }

    public void setNumCumValue(Double numCumValue) {
        this.numCumValue = numCumValue;
    }

    public Double getNumCumGrowthRate() {
        return numCumGrowthRate;
    }

    public void setNumCumGrowthRate(Double numCumGrowthRate) {
        this.numCumGrowthRate = numCumGrowthRate;
    }

    public Double getScaMonthValue() {
        return scaMonthValue;
    }

    public void setScaMonthValue(Double scaMonthValue) {
        this.scaMonthValue = scaMonthValue;
    }

    public Double getScaGrowthRate() {
        return scaGrowthRate;
    }

    public void setScaGrowthRate(Double scaGrowthRate) {
        this.scaGrowthRate = scaGrowthRate;
    }

    public Double getScaCumValue() {
        return scaCumValue;
    }

    public void setScaCumValue(Double scaCumValue) {
        this.scaCumValue = scaCumValue;
    }

    public Double getScaCumGrowthRate() {
        return scaCumGrowthRate;
    }

    public void setScaCumGrowthRate(Double scaCumGrowthRate) {
        this.scaCumGrowthRate = scaCumGrowthRate;
    }
}