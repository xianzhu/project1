package com.cv.peseer.model.ci;

public class CiUsNpRetail {
    private Integer id;

    private String statDate;

    private Double totNpRetail;

    private Double totGrowthRate;

    private Double eNpRetail;

    private Double eGrowthRate;

    private Double autoNpRetail;

    private Double autoGrowthRate;

    private Double directNpRetail;

    private Double directGrowthRate;

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

    public Double getTotNpRetail() {
        return totNpRetail;
    }

    public void setTotNpRetail(Double totNpRetail) {
        this.totNpRetail = totNpRetail;
    }

    public Double getTotGrowthRate() {
        return totGrowthRate;
    }

    public void setTotGrowthRate(Double totGrowthRate) {
        this.totGrowthRate = totGrowthRate;
    }

    public Double geteNpRetail() {
        return eNpRetail;
    }

    public void seteNpRetail(Double eNpRetail) {
        this.eNpRetail = eNpRetail;
    }

    public Double geteGrowthRate() {
        return eGrowthRate;
    }

    public void seteGrowthRate(Double eGrowthRate) {
        this.eGrowthRate = eGrowthRate;
    }

    public Double getAutoNpRetail() {
        return autoNpRetail;
    }

    public void setAutoNpRetail(Double autoNpRetail) {
        this.autoNpRetail = autoNpRetail;
    }

    public Double getAutoGrowthRate() {
        return autoGrowthRate;
    }

    public void setAutoGrowthRate(Double autoGrowthRate) {
        this.autoGrowthRate = autoGrowthRate;
    }

    public Double getDirectNpRetail() {
        return directNpRetail;
    }

    public void setDirectNpRetail(Double directNpRetail) {
        this.directNpRetail = directNpRetail;
    }

    public Double getDirectGrowthRate() {
        return directGrowthRate;
    }

    public void setDirectGrowthRate(Double directGrowthRate) {
        this.directGrowthRate = directGrowthRate;
    }
}