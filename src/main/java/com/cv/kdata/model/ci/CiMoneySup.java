package com.cv.kdata.model.ci;

public class CiMoneySup {
    private Integer id;

    private String statDate;

    private Double m0ClosingBal;

    private Double m1ClosingBal;

    private Double m2ClosingBal;

    private Double m0GrowthRate;

    private Double m1GrowthRate;

    private Double m2GrowthRate;

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

    public Double getM0ClosingBal() {
        return m0ClosingBal;
    }

    public void setM0ClosingBal(Double m0ClosingBal) {
        this.m0ClosingBal = m0ClosingBal;
    }

    public Double getM1ClosingBal() {
        return m1ClosingBal;
    }

    public void setM1ClosingBal(Double m1ClosingBal) {
        this.m1ClosingBal = m1ClosingBal;
    }

    public Double getM2ClosingBal() {
        return m2ClosingBal;
    }

    public void setM2ClosingBal(Double m2ClosingBal) {
        this.m2ClosingBal = m2ClosingBal;
    }

    public Double getM0GrowthRate() {
        return m0GrowthRate;
    }

    public void setM0GrowthRate(Double m0GrowthRate) {
        this.m0GrowthRate = m0GrowthRate;
    }

    public Double getM1GrowthRate() {
        return m1GrowthRate;
    }

    public void setM1GrowthRate(Double m1GrowthRate) {
        this.m1GrowthRate = m1GrowthRate;
    }

    public Double getM2GrowthRate() {
        return m2GrowthRate;
    }

    public void setM2GrowthRate(Double m2GrowthRate) {
        this.m2GrowthRate = m2GrowthRate;
    }
}