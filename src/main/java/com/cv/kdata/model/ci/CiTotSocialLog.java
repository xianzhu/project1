package com.cv.kdata.model.ci;

public class CiTotSocialLog {
    private Integer id;

    private String statDate;

    private Double accumValue;

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

    public Double getAccumValue() {
        return accumValue;
    }

    public void setAccumValue(Double accumValue) {
        this.accumValue = accumValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}