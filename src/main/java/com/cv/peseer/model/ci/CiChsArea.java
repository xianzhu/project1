package com.cv.peseer.model.ci;

public class CiChsArea {
    private Integer id;

    private String statDate;

    private Double csYearCum;

    private Double csGrowthRate;

    private Double chYearCum;

    private Double chGrowthRate;

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

    public Double getCsYearCum() {
        return csYearCum;
    }

    public void setCsYearCum(Double csYearCum) {
        this.csYearCum = csYearCum;
    }

    public Double getCsGrowthRate() {
        return csGrowthRate;
    }

    public void setCsGrowthRate(Double csGrowthRate) {
        this.csGrowthRate = csGrowthRate;
    }

    public Double getChYearCum() {
        return chYearCum;
    }

    public void setChYearCum(Double chYearCum) {
        this.chYearCum = chYearCum;
    }

    public Double getChGrowthRate() {
        return chGrowthRate;
    }

    public void setChGrowthRate(Double chGrowthRate) {
        this.chGrowthRate = chGrowthRate;
    }
}