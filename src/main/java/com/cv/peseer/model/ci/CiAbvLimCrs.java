package com.cv.peseer.model.ci;

public class CiAbvLimCrs {
    private Integer id;

    private String statDate;

    private Double monthValue;

    private Double monthRate;

    private Double cumValue;

    private Double cumRate;

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

    public Double getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(Double monthRate) {
        this.monthRate = monthRate;
    }

    public Double getCumValue() {
        return cumValue;
    }

    public void setCumValue(Double cumValue) {
        this.cumValue = cumValue;
    }

    public Double getCumRate() {
        return cumRate;
    }

    public void setCumRate(Double cumRate) {
        this.cumRate = cumRate;
    }
}