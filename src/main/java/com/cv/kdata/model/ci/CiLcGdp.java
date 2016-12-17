package com.cv.kdata.model.ci;

public class CiLcGdp {
    private Integer id;

    private String statDate;

    private Double cumValue;

    private Double growthRate;

    private Double gdpRate;

    private Double gdpGrowthRate;

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

    public Double getCumValue() {
        return cumValue;
    }

    public void setCumValue(Double cumValue) {
        this.cumValue = cumValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getGdpRate() {
        return gdpRate;
    }

    public void setGdpRate(Double gdpRate) {
        this.gdpRate = gdpRate;
    }

    public Double getGdpGrowthRate() {
        return gdpGrowthRate;
    }

    public void setGdpGrowthRate(Double gdpGrowthRate) {
        this.gdpGrowthRate = gdpGrowthRate;
    }
}