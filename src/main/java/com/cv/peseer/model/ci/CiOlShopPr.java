package com.cv.peseer.model.ci;

public class CiOlShopPr {
    private Integer id;

    private String stateDate;

    private Double scale;

    private Double chnPr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateDate() {
        return stateDate;
    }

    public void setStateDate(String stateDate) {
        this.stateDate = stateDate == null ? null : stateDate.trim();
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getChnPr() {
        return chnPr;
    }

    public void setChnPr(Double chnPr) {
        this.chnPr = chnPr;
    }
}