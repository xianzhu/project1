package com.cv.peseer.model.ci;

public class CiAutomatSalInv {
    private Integer id;

    private String statDate;

    private Double salYearValue;

    private Double salGrowthRate;

    private Double keepYearValue;

    private Double keepGrowthRate;

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

    public Double getSalYearValue() {
        return salYearValue;
    }

    public void setSalYearValue(Double salYearValue) {
        this.salYearValue = salYearValue;
    }

    public Double getSalGrowthRate() {
        return salGrowthRate;
    }

    public void setSalGrowthRate(Double salGrowthRate) {
        this.salGrowthRate = salGrowthRate;
    }

    public Double getKeepYearValue() {
        return keepYearValue;
    }

    public void setKeepYearValue(Double keepYearValue) {
        this.keepYearValue = keepYearValue;
    }

    public Double getKeepGrowthRate() {
        return keepGrowthRate;
    }

    public void setKeepGrowthRate(Double keepGrowthRate) {
        this.keepGrowthRate = keepGrowthRate;
    }
}