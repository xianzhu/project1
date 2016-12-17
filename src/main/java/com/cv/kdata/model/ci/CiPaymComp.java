package com.cv.kdata.model.ci;

public class CiPaymComp {
    private Integer id;

    private String statDate;

    private Double olShop;

    private Double airTrav;

    private Double teleCost;

    private Double eBtob;

    private Double olGame;

    private Double others;

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

    public Double getOlShop() {
        return olShop;
    }

    public void setOlShop(Double olShop) {
        this.olShop = olShop;
    }

    public Double getAirTrav() {
        return airTrav;
    }

    public void setAirTrav(Double airTrav) {
        this.airTrav = airTrav;
    }

    public Double getTeleCost() {
        return teleCost;
    }

    public void setTeleCost(Double teleCost) {
        this.teleCost = teleCost;
    }

    public Double geteBtob() {
        return eBtob;
    }

    public void seteBtob(Double eBtob) {
        this.eBtob = eBtob;
    }

    public Double getOlGame() {
        return olGame;
    }

    public void setOlGame(Double olGame) {
        this.olGame = olGame;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }
}