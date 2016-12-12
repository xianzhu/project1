package com.cv.peseer.model.ci;

public class CiCcNum {
    private Integer id;

    private String statTime;

    private Double totAccumVaule;

    private Double totGrowthRate;

    private Double avgAccumVaule;

    private Double avgGrowthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime == null ? null : statTime.trim();
    }

    public Double getTotAccumVaule() {
        return totAccumVaule;
    }

    public void setTotAccumVaule(Double totAccumVaule) {
        this.totAccumVaule = totAccumVaule;
    }

    public Double getTotGrowthRate() {
        return totGrowthRate;
    }

    public void setTotGrowthRate(Double totGrowthRate) {
        this.totGrowthRate = totGrowthRate;
    }

    public Double getAvgAccumVaule() {
        return avgAccumVaule;
    }

    public void setAvgAccumVaule(Double avgAccumVaule) {
        this.avgAccumVaule = avgAccumVaule;
    }

    public Double getAvgGrowthRate() {
        return avgGrowthRate;
    }

    public void setAvgGrowthRate(Double avgGrowthRate) {
        this.avgGrowthRate = avgGrowthRate;
    }
}