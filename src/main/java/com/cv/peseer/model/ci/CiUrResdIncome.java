package com.cv.peseer.model.ci;

public class CiUrResdIncome {
    private Integer id;

    private String statDate;

    private Double udAnnualCum;

    private Double udGrowthRate;

    private Double udSeasonValue;

    private Double rcAnnualCum;

    private Double rcGrowthRate;

    private Double rcSeasonValue;

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

    public Double getUdAnnualCum() {
        return udAnnualCum;
    }

    public void setUdAnnualCum(Double udAnnualCum) {
        this.udAnnualCum = udAnnualCum;
    }

    public Double getUdGrowthRate() {
        return udGrowthRate;
    }

    public void setUdGrowthRate(Double udGrowthRate) {
        this.udGrowthRate = udGrowthRate;
    }

    public Double getUdSeasonValue() {
        return udSeasonValue;
    }

    public void setUdSeasonValue(Double udSeasonValue) {
        this.udSeasonValue = udSeasonValue;
    }

    public Double getRcAnnualCum() {
        return rcAnnualCum;
    }

    public void setRcAnnualCum(Double rcAnnualCum) {
        this.rcAnnualCum = rcAnnualCum;
    }

    public Double getRcGrowthRate() {
        return rcGrowthRate;
    }

    public void setRcGrowthRate(Double rcGrowthRate) {
        this.rcGrowthRate = rcGrowthRate;
    }

    public Double getRcSeasonValue() {
        return rcSeasonValue;
    }

    public void setRcSeasonValue(Double rcSeasonValue) {
        this.rcSeasonValue = rcSeasonValue;
    }
}