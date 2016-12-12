package com.cv.peseer.model.ci;

public class CiGdp {
    private Integer id;

    private String statDate;

    private Double annualCum;

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

    public Double getAnnualCum() {
        return annualCum;
    }

    public void setAnnualCum(Double annualCum) {
        this.annualCum = annualCum;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}