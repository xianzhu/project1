package com.cv.kdata.model.ci;

public class CiAddRmbLoan {
    private Integer id;

    private String statDate;

    private Double numAnnualCum;

    private Double numGrowthRate;

    private Double numMonth;

    private Double resAnnualCum;

    private Double resGrowthRate;

    private Double resMonth;

    private Double nfAnnualCum;

    private Double nfGrowthRate;

    private Double nfMonth;

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

    public Double getNumAnnualCum() {
        return numAnnualCum;
    }

    public void setNumAnnualCum(Double numAnnualCum) {
        this.numAnnualCum = numAnnualCum;
    }

    public Double getNumGrowthRate() {
        return numGrowthRate;
    }

    public void setNumGrowthRate(Double numGrowthRate) {
        this.numGrowthRate = numGrowthRate;
    }

    public Double getNumMonth() {
        return numMonth;
    }

    public void setNumMonth(Double numMonth) {
        this.numMonth = numMonth;
    }

    public Double getResAnnualCum() {
        return resAnnualCum;
    }

    public void setResAnnualCum(Double resAnnualCum) {
        this.resAnnualCum = resAnnualCum;
    }

    public Double getResGrowthRate() {
        return resGrowthRate;
    }

    public void setResGrowthRate(Double resGrowthRate) {
        this.resGrowthRate = resGrowthRate;
    }

    public Double getResMonth() {
        return resMonth;
    }

    public void setResMonth(Double resMonth) {
        this.resMonth = resMonth;
    }

    public Double getNfAnnualCum() {
        return nfAnnualCum;
    }

    public void setNfAnnualCum(Double nfAnnualCum) {
        this.nfAnnualCum = nfAnnualCum;
    }

    public Double getNfGrowthRate() {
        return nfGrowthRate;
    }

    public void setNfGrowthRate(Double nfGrowthRate) {
        this.nfGrowthRate = nfGrowthRate;
    }

    public Double getNfMonth() {
        return nfMonth;
    }

    public void setNfMonth(Double nfMonth) {
        this.nfMonth = nfMonth;
    }
}