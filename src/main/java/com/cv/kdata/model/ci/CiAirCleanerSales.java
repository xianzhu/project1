package com.cv.kdata.model.ci;

public class CiAirCleanerSales {
    private Integer id;

    private String statDate;

    private Double monthValue;

    private Double monGrowth;

    private Double domeMonthValue;

    private Double domeMonGrowth;

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

    public Double getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Double monthValue) {
        this.monthValue = monthValue;
    }

    public Double getMonGrowth() {
        return monGrowth;
    }

    public void setMonGrowth(Double monGrowth) {
        this.monGrowth = monGrowth;
    }

    public Double getDomeMonthValue() {
        return domeMonthValue;
    }

    public void setDomeMonthValue(Double domeMonthValue) {
        this.domeMonthValue = domeMonthValue;
    }

    public Double getDomeMonGrowth() {
        return domeMonGrowth;
    }

    public void setDomeMonGrowth(Double domeMonGrowth) {
        this.domeMonGrowth = domeMonGrowth;
    }
}