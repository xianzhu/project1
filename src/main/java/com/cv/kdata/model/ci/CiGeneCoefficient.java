package com.cv.kdata.model.ci;

public class CiGeneCoefficient {
    private Integer id;

    private String statDate;

    private Double yearValue;

    private Double growthRate;

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

    public Double getYearValue() {
        return yearValue;
    }

    public void setYearValue(Double yearValue) {
        this.yearValue = yearValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}