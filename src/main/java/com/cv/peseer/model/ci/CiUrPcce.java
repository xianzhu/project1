package com.cv.peseer.model.ci;

public class CiUrPcce {
    private Integer id;

    private String statDate;

    private Double ubAnnualCum;

    private Double ubGrowthRate;

    private Double ubSeason;

    private Double ruAnnualCum;

    private Double ruGrowthRate;

    private Double ruSeason;

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

    public Double getUbAnnualCum() {
        return ubAnnualCum;
    }

    public void setUbAnnualCum(Double ubAnnualCum) {
        this.ubAnnualCum = ubAnnualCum;
    }

    public Double getUbGrowthRate() {
        return ubGrowthRate;
    }

    public void setUbGrowthRate(Double ubGrowthRate) {
        this.ubGrowthRate = ubGrowthRate;
    }

    public Double getUbSeason() {
        return ubSeason;
    }

    public void setUbSeason(Double ubSeason) {
        this.ubSeason = ubSeason;
    }

    public Double getRuAnnualCum() {
        return ruAnnualCum;
    }

    public void setRuAnnualCum(Double ruAnnualCum) {
        this.ruAnnualCum = ruAnnualCum;
    }

    public Double getRuGrowthRate() {
        return ruGrowthRate;
    }

    public void setRuGrowthRate(Double ruGrowthRate) {
        this.ruGrowthRate = ruGrowthRate;
    }

    public Double getRuSeason() {
        return ruSeason;
    }

    public void setRuSeason(Double ruSeason) {
        this.ruSeason = ruSeason;
    }
}