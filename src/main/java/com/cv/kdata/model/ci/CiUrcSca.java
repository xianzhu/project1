package com.cv.kdata.model.ci;

public class CiUrcSca {
    private Integer id;

    private String statDate;

    private Double urbanMonthValue;

    private Double urbanMonthRate;

    private Double urbanCumValue;

    private Double urbanCumGrowthRate;

    private Double ruralMonthValue;

    private Double ruralMonthRate;

    private Double ruralCumValue;

    private Double ruralCumGrowthRate;

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

    public Double getUrbanMonthValue() {
        return urbanMonthValue;
    }

    public void setUrbanMonthValue(Double urbanMonthValue) {
        this.urbanMonthValue = urbanMonthValue;
    }

    public Double getUrbanMonthRate() {
        return urbanMonthRate;
    }

    public void setUrbanMonthRate(Double urbanMonthRate) {
        this.urbanMonthRate = urbanMonthRate;
    }

    public Double getUrbanCumValue() {
        return urbanCumValue;
    }

    public void setUrbanCumValue(Double urbanCumValue) {
        this.urbanCumValue = urbanCumValue;
    }

    public Double getUrbanCumGrowthRate() {
        return urbanCumGrowthRate;
    }

    public void setUrbanCumGrowthRate(Double urbanCumGrowthRate) {
        this.urbanCumGrowthRate = urbanCumGrowthRate;
    }

    public Double getRuralMonthValue() {
        return ruralMonthValue;
    }

    public void setRuralMonthValue(Double ruralMonthValue) {
        this.ruralMonthValue = ruralMonthValue;
    }

    public Double getRuralMonthRate() {
        return ruralMonthRate;
    }

    public void setRuralMonthRate(Double ruralMonthRate) {
        this.ruralMonthRate = ruralMonthRate;
    }

    public Double getRuralCumValue() {
        return ruralCumValue;
    }

    public void setRuralCumValue(Double ruralCumValue) {
        this.ruralCumValue = ruralCumValue;
    }

    public Double getRuralCumGrowthRate() {
        return ruralCumGrowthRate;
    }

    public void setRuralCumGrowthRate(Double ruralCumGrowthRate) {
        this.ruralCumGrowthRate = ruralCumGrowthRate;
    }
}