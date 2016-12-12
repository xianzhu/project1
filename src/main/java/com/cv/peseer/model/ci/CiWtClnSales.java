package com.cv.peseer.model.ci;

public class CiWtClnSales {
    private Integer id;

    private String statDate;

    private Double sumMonthValue;

    private Double sumMonthRate;

    private Double inMonthValue;

    private Double inMonthRate;

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

    public Double getSumMonthValue() {
        return sumMonthValue;
    }

    public void setSumMonthValue(Double sumMonthValue) {
        this.sumMonthValue = sumMonthValue;
    }

    public Double getSumMonthRate() {
        return sumMonthRate;
    }

    public void setSumMonthRate(Double sumMonthRate) {
        this.sumMonthRate = sumMonthRate;
    }

    public Double getInMonthValue() {
        return inMonthValue;
    }

    public void setInMonthValue(Double inMonthValue) {
        this.inMonthValue = inMonthValue;
    }

    public Double getInMonthRate() {
        return inMonthRate;
    }

    public void setInMonthRate(Double inMonthRate) {
        this.inMonthRate = inMonthRate;
    }
}