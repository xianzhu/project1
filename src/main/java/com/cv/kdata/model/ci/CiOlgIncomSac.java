package com.cv.kdata.model.ci;

public class CiOlgIncomSac {
    private Integer id;

    private String statDate;

    private Double olgQuarterValue;

    private Double olgGrowthRate;

    private Double olgRg;

    private Double mobQuarterValue;

    private Double mobGrowthRate;

    private Double mobRg;

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

    public Double getOlgQuarterValue() {
        return olgQuarterValue;
    }

    public void setOlgQuarterValue(Double olgQuarterValue) {
        this.olgQuarterValue = olgQuarterValue;
    }

    public Double getOlgGrowthRate() {
        return olgGrowthRate;
    }

    public void setOlgGrowthRate(Double olgGrowthRate) {
        this.olgGrowthRate = olgGrowthRate;
    }

    public Double getOlgRg() {
        return olgRg;
    }

    public void setOlgRg(Double olgRg) {
        this.olgRg = olgRg;
    }

    public Double getMobQuarterValue() {
        return mobQuarterValue;
    }

    public void setMobQuarterValue(Double mobQuarterValue) {
        this.mobQuarterValue = mobQuarterValue;
    }

    public Double getMobGrowthRate() {
        return mobGrowthRate;
    }

    public void setMobGrowthRate(Double mobGrowthRate) {
        this.mobGrowthRate = mobGrowthRate;
    }

    public Double getMobRg() {
        return mobRg;
    }

    public void setMobRg(Double mobRg) {
        this.mobRg = mobRg;
    }
}