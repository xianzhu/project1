package com.cv.kdata.model.ci;

public class CiUsConsuGrowth {
    private Integer id;

    private String statDate;

    private Double persConsu;

    private Double goodsConsu;

    private Double durabGoods;

    private Double nDurabGoods;

    private Double servConsu;

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

    public Double getPersConsu() {
        return persConsu;
    }

    public void setPersConsu(Double persConsu) {
        this.persConsu = persConsu;
    }

    public Double getGoodsConsu() {
        return goodsConsu;
    }

    public void setGoodsConsu(Double goodsConsu) {
        this.goodsConsu = goodsConsu;
    }

    public Double getDurabGoods() {
        return durabGoods;
    }

    public void setDurabGoods(Double durabGoods) {
        this.durabGoods = durabGoods;
    }

    public Double getnDurabGoods() {
        return nDurabGoods;
    }

    public void setnDurabGoods(Double nDurabGoods) {
        this.nDurabGoods = nDurabGoods;
    }

    public Double getServConsu() {
        return servConsu;
    }

    public void setServConsu(Double servConsu) {
        this.servConsu = servConsu;
    }
}