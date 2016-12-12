package com.cv.peseer.model;

import java.io.Serializable;

public class CiSfSca implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String statDate;

    private Double monValue;

    private Double monGrowthRate;

    private Double accumValue;

    private Double accumGrowthRate;

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

    public Double getMonValue() {
        return monValue;
    }

    public void setMonValue(Double monValue) {
        this.monValue = monValue;
    }

    public Double getMonGrowthRate() {
        return monGrowthRate;
    }

    public void setMonGrowthRate(Double monGrowthRate) {
        this.monGrowthRate = monGrowthRate;
    }

    public Double getAccumValue() {
        return accumValue;
    }

    public void setAccumValue(Double accumValue) {
        this.accumValue = accumValue;
    }

    public Double getAccumGrowthRate() {
        return accumGrowthRate;
    }

    public void setAccumGrowthRate(Double accumGrowthRate) {
        this.accumGrowthRate = accumGrowthRate;
    }
}