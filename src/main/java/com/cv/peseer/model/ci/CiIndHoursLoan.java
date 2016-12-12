package com.cv.peseer.model.ci;

public class CiIndHoursLoan {
    private Integer id;

    private String statDate;

    private Double fnlVaule;

    private Double growthRate;

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

    public Double getFnlVaule() {
        return fnlVaule;
    }

    public void setFnlVaule(Double fnlVaule) {
        this.fnlVaule = fnlVaule;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}