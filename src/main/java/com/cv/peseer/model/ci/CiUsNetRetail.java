package com.cv.peseer.model.ci;

public class CiUsNetRetail {
    private Integer id;

    private String statDate;

    private Double retailValue;

    private Double growthRate;

    private Double prop;

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

    public Double getRetailValue() {
        return retailValue;
    }

    public void setRetailValue(Double retailValue) {
        this.retailValue = retailValue;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getProp() {
        return prop;
    }

    public void setProp(Double prop) {
        this.prop = prop;
    }
}