package com.cv.kdata.model.ci;

public class CiTifaAmout {
    private Integer id;

    private String statRate;

    private Double wrAnnualCum;

    private Double wrGrowthRate;

    private Double acAnnualCum;

    private Double acGrowthRate;

    private Double lbAnnualCum;

    private Double lbGrowthRate;

    private Double rsAnnualCum;

    private Double rsGrowthRate;

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

    public Double getWrAnnualCum() {
        return wrAnnualCum;
    }

    public void setWrAnnualCum(Double wrAnnualCum) {
        this.wrAnnualCum = wrAnnualCum;
    }

    public Double getWrGrowthRate() {
        return wrGrowthRate;
    }

    public void setWrGrowthRate(Double wrGrowthRate) {
        this.wrGrowthRate = wrGrowthRate;
    }

    public Double getAcAnnualCum() {
        return acAnnualCum;
    }

    public void setAcAnnualCum(Double acAnnualCum) {
        this.acAnnualCum = acAnnualCum;
    }

    public Double getAcGrowthRate() {
        return acGrowthRate;
    }

    public void setAcGrowthRate(Double acGrowthRate) {
        this.acGrowthRate = acGrowthRate;
    }

    public Double getLbAnnualCum() {
        return lbAnnualCum;
    }

    public void setLbAnnualCum(Double lbAnnualCum) {
        this.lbAnnualCum = lbAnnualCum;
    }

    public Double getLbGrowthRate() {
        return lbGrowthRate;
    }

    public void setLbGrowthRate(Double lbGrowthRate) {
        this.lbGrowthRate = lbGrowthRate;
    }

    public Double getRsAnnualCum() {
        return rsAnnualCum;
    }

    public void setRsAnnualCum(Double rsAnnualCum) {
        this.rsAnnualCum = rsAnnualCum;
    }

    public Double getRsGrowthRate() {
        return rsGrowthRate;
    }

    public void setRsGrowthRate(Double rsGrowthRate) {
        this.rsGrowthRate = rsGrowthRate;
    }
}