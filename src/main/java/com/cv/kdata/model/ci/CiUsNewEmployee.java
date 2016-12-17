package com.cv.kdata.model.ci;

public class CiUsNewEmployee {
    private Integer id;

    private String statDate;

    private Double snaMonthValue;

    private Double snaRingRate;

    private Double neMonthValue;

    private Double neRingRate;

    private Double adpMonthValue;

    private Double adpRingRate;

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

    public Double getSnaMonthValue() {
        return snaMonthValue;
    }

    public void setSnaMonthValue(Double snaMonthValue) {
        this.snaMonthValue = snaMonthValue;
    }

    public Double getSnaRingRate() {
        return snaRingRate;
    }

    public void setSnaRingRate(Double snaRingRate) {
        this.snaRingRate = snaRingRate;
    }

    public Double getNeMonthValue() {
        return neMonthValue;
    }

    public void setNeMonthValue(Double neMonthValue) {
        this.neMonthValue = neMonthValue;
    }

    public Double getNeRingRate() {
        return neRingRate;
    }

    public void setNeRingRate(Double neRingRate) {
        this.neRingRate = neRingRate;
    }

    public Double getAdpMonthValue() {
        return adpMonthValue;
    }

    public void setAdpMonthValue(Double adpMonthValue) {
        this.adpMonthValue = adpMonthValue;
    }

    public Double getAdpRingRate() {
        return adpRingRate;
    }

    public void setAdpRingRate(Double adpRingRate) {
        this.adpRingRate = adpRingRate;
    }
}