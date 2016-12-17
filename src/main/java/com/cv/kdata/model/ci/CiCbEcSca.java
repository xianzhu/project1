package com.cv.kdata.model.ci;

public class CiCbEcSca {
    private Integer id;

    private String statDate;

    private Double totYearValue;

    private Double totGrowthRate;

    private Double scaYearValue;

    private Double scaGrowthRate;

    private Double propYearValue;

    private Double propGrowthRate;

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

    public Double getTotYearValue() {
        return totYearValue;
    }

    public void setTotYearValue(Double totYearValue) {
        this.totYearValue = totYearValue;
    }

    public Double getTotGrowthRate() {
        return totGrowthRate;
    }

    public void setTotGrowthRate(Double totGrowthRate) {
        this.totGrowthRate = totGrowthRate;
    }

    public Double getScaYearValue() {
        return scaYearValue;
    }

    public void setScaYearValue(Double scaYearValue) {
        this.scaYearValue = scaYearValue;
    }

    public Double getScaGrowthRate() {
        return scaGrowthRate;
    }

    public void setScaGrowthRate(Double scaGrowthRate) {
        this.scaGrowthRate = scaGrowthRate;
    }

    public Double getPropYearValue() {
        return propYearValue;
    }

    public void setPropYearValue(Double propYearValue) {
        this.propYearValue = propYearValue;
    }

    public Double getPropGrowthRate() {
        return propGrowthRate;
    }

    public void setPropGrowthRate(Double propGrowthRate) {
        this.propGrowthRate = propGrowthRate;
    }
}