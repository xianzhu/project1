package com.cv.kdata.model.ci;

public class CiCptocSca {
    private Integer id;

    private String statDate;

    private Double dealYearValue;

    private Double dealGrowthRate;

    private Double entYearValue;

    private Double entGrowthRate;

    private Double chnYearValue;

    private Double chnGrowthRate;

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

    public Double getDealYearValue() {
        return dealYearValue;
    }

    public void setDealYearValue(Double dealYearValue) {
        this.dealYearValue = dealYearValue;
    }

    public Double getDealGrowthRate() {
        return dealGrowthRate;
    }

    public void setDealGrowthRate(Double dealGrowthRate) {
        this.dealGrowthRate = dealGrowthRate;
    }

    public Double getEntYearValue() {
        return entYearValue;
    }

    public void setEntYearValue(Double entYearValue) {
        this.entYearValue = entYearValue;
    }

    public Double getEntGrowthRate() {
        return entGrowthRate;
    }

    public void setEntGrowthRate(Double entGrowthRate) {
        this.entGrowthRate = entGrowthRate;
    }

    public Double getChnYearValue() {
        return chnYearValue;
    }

    public void setChnYearValue(Double chnYearValue) {
        this.chnYearValue = chnYearValue;
    }

    public Double getChnGrowthRate() {
        return chnGrowthRate;
    }

    public void setChnGrowthRate(Double chnGrowthRate) {
        this.chnGrowthRate = chnGrowthRate;
    }
}