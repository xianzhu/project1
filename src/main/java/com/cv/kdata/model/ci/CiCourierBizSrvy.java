package com.cv.kdata.model.ci;

public class CiCourierBizSrvy {
    private Integer id;

    private String statDate;

    private Double numMonValue;

    private Double numMonGrowth;

    private Double numYearAccum;

    private Double numAccumGrowth;

    private Double incmMonValue;

    private Double incmMonGrowth;

    private Double incmYearAccum;

    private Double incmAccumGrowth;

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

    public Double getNumMonValue() {
        return numMonValue;
    }

    public void setNumMonValue(Double numMonValue) {
        this.numMonValue = numMonValue;
    }

    public Double getNumMonGrowth() {
        return numMonGrowth;
    }

    public void setNumMonGrowth(Double numMonGrowth) {
        this.numMonGrowth = numMonGrowth;
    }

    public Double getNumYearAccum() {
        return numYearAccum;
    }

    public void setNumYearAccum(Double numYearAccum) {
        this.numYearAccum = numYearAccum;
    }

    public Double getNumAccumGrowth() {
        return numAccumGrowth;
    }

    public void setNumAccumGrowth(Double numAccumGrowth) {
        this.numAccumGrowth = numAccumGrowth;
    }

    public Double getIncmMonValue() {
        return incmMonValue;
    }

    public void setIncmMonValue(Double incmMonValue) {
        this.incmMonValue = incmMonValue;
    }

    public Double getIncmMonGrowth() {
        return incmMonGrowth;
    }

    public void setIncmMonGrowth(Double incmMonGrowth) {
        this.incmMonGrowth = incmMonGrowth;
    }

    public Double getIncmYearAccum() {
        return incmYearAccum;
    }

    public void setIncmYearAccum(Double incmYearAccum) {
        this.incmYearAccum = incmYearAccum;
    }

    public Double getIncmAccumGrowth() {
        return incmAccumGrowth;
    }

    public void setIncmAccumGrowth(Double incmAccumGrowth) {
        this.incmAccumGrowth = incmAccumGrowth;
    }
}