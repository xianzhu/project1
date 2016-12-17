package com.cv.kdata.model.ci;

public class CiCcIndex {
    private Integer id;

    private String statDate;

    private Double ccIndex;

    private Double csIndex;

    private Double ceIndex;

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

    public Double getCcIndex() {
        return ccIndex;
    }

    public void setCcIndex(Double ccIndex) {
        this.ccIndex = ccIndex;
    }

    public Double getCsIndex() {
        return csIndex;
    }

    public void setCsIndex(Double csIndex) {
        this.csIndex = csIndex;
    }

    public Double getCeIndex() {
        return ceIndex;
    }

    public void setCeIndex(Double ceIndex) {
        this.ceIndex = ceIndex;
    }
}