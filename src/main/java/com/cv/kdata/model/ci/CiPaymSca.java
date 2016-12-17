package com.cv.kdata.model.ci;

public class CiPaymSca {
    private Integer id;

    private String statDate;

    private Double quarterValue;

    private Double growthRate;

    private Double rg;

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

    public Double getQuarterValue() {
        return quarterValue;
    }

    public void setQuarterValue(Double quarterValue) {
        this.quarterValue = quarterValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getRg() {
        return rg;
    }

    public void setRg(Double rg) {
        this.rg = rg;
    }
}