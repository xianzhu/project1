package com.cv.kdata.model.ci;

public class CiLusConsuSca {
    private Integer id;

    private String statDate;

    private Double yearValue;

    private Double compGrowth;

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

    public Double getYearValue() {
        return yearValue;
    }

    public void setYearValue(Double yearValue) {
        this.yearValue = yearValue;
    }

    public Double getCompGrowth() {
        return compGrowth;
    }

    public void setCompGrowth(Double compGrowth) {
        this.compGrowth = compGrowth;
    }
}