package com.cv.kdata.model.ci;

public class CiRegUnempRate {
    private Integer id;

    private String statDate;

    private Double quaternaryValue;

    private Double yearChange;

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

    public Double getQuaternaryValue() {
        return quaternaryValue;
    }

    public void setQuaternaryValue(Double quaternaryValue) {
        this.quaternaryValue = quaternaryValue;
    }

    public Double getYearChange() {
        return yearChange;
    }

    public void setYearChange(Double yearChange) {
        this.yearChange = yearChange;
    }
}