package com.cv.peseer.model.ci;

public class CiRhfsArea {
    private Integer id;

    private String statRate;

    private Double rhfsYearCum;

    private Double rhfsGrowthRate;

    private Double rdfsYearCum;

    private Double rdfsGrowthRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatRate() {
        return statRate;
    }

    public void setStatRate(String statRate) {
        this.statRate = statRate == null ? null : statRate.trim();
    }

    public Double getRhfsYearCum() {
        return rhfsYearCum;
    }

    public void setRhfsYearCum(Double rhfsYearCum) {
        this.rhfsYearCum = rhfsYearCum;
    }

    public Double getRhfsGrowthRate() {
        return rhfsGrowthRate;
    }

    public void setRhfsGrowthRate(Double rhfsGrowthRate) {
        this.rhfsGrowthRate = rhfsGrowthRate;
    }

    public Double getRdfsYearCum() {
        return rdfsYearCum;
    }

    public void setRdfsYearCum(Double rdfsYearCum) {
        this.rdfsYearCum = rdfsYearCum;
    }

    public Double getRdfsGrowthRate() {
        return rdfsGrowthRate;
    }

    public void setRdfsGrowthRate(Double rdfsGrowthRate) {
        this.rdfsGrowthRate = rdfsGrowthRate;
    }
}