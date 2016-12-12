package com.cv.peseer.model.ci;

public class CiBitPrice {
    private Integer id;

    private String statDate;

    private Double newest;

    private Double riseFall;

    private Double riseFallChg;

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

    public Double getNewest() {
        return newest;
    }

    public void setNewest(Double newest) {
        this.newest = newest;
    }

    public Double getRiseFall() {
        return riseFall;
    }

    public void setRiseFall(Double riseFall) {
        this.riseFall = riseFall;
    }

    public Double getRiseFallChg() {
        return riseFallChg;
    }

    public void setRiseFallChg(Double riseFallChg) {
        this.riseFallChg = riseFallChg;
    }
}