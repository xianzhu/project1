package com.cv.peseer.model.ci;

public class CiDtourIncome {
    private Integer id;

    private String statDate;

    private Double itourYearValue;

    private Double itourGrowthRate;

    private Double rtourYearValue;

    private Double rtourGrowthRate;

    private Double utourYearValue;

    private Double utourGrowthRate;

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

    public Double getItourYearValue() {
        return itourYearValue;
    }

    public void setItourYearValue(Double itourYearValue) {
        this.itourYearValue = itourYearValue;
    }

    public Double getItourGrowthRate() {
        return itourGrowthRate;
    }

    public void setItourGrowthRate(Double itourGrowthRate) {
        this.itourGrowthRate = itourGrowthRate;
    }

    public Double getRtourYearValue() {
        return rtourYearValue;
    }

    public void setRtourYearValue(Double rtourYearValue) {
        this.rtourYearValue = rtourYearValue;
    }

    public Double getRtourGrowthRate() {
        return rtourGrowthRate;
    }

    public void setRtourGrowthRate(Double rtourGrowthRate) {
        this.rtourGrowthRate = rtourGrowthRate;
    }

    public Double getUtourYearValue() {
        return utourYearValue;
    }

    public void setUtourYearValue(Double utourYearValue) {
        this.utourYearValue = utourYearValue;
    }

    public Double getUtourGrowthRate() {
        return utourGrowthRate;
    }

    public void setUtourGrowthRate(Double utourGrowthRate) {
        this.utourGrowthRate = utourGrowthRate;
    }
}