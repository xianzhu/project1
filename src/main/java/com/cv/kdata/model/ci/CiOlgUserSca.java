package com.cv.kdata.model.ci;

public class CiOlgUserSca {
    private Integer id;

    private String statDate;

    private Double totValue;

    private Double totGrowthRate;

    private Double totRg;

    private Double clientValue;

    private Double clientGrowthRate;

    private Double clientRg;

    private Double pageValue;

    private Double pageGrowthRate;

    private Double pageRg;

    private Double mobValue;

    private Double mobGrowthRate;

    private Double mobRg;

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

    public Double getTotValue() {
        return totValue;
    }

    public void setTotValue(Double totValue) {
        this.totValue = totValue;
    }

    public Double getTotGrowthRate() {
        return totGrowthRate;
    }

    public void setTotGrowthRate(Double totGrowthRate) {
        this.totGrowthRate = totGrowthRate;
    }

    public Double getTotRg() {
        return totRg;
    }

    public void setTotRg(Double totRg) {
        this.totRg = totRg;
    }

    public Double getClientValue() {
        return clientValue;
    }

    public void setClientValue(Double clientValue) {
        this.clientValue = clientValue;
    }

    public Double getClientGrowthRate() {
        return clientGrowthRate;
    }

    public void setClientGrowthRate(Double clientGrowthRate) {
        this.clientGrowthRate = clientGrowthRate;
    }

    public Double getClientRg() {
        return clientRg;
    }

    public void setClientRg(Double clientRg) {
        this.clientRg = clientRg;
    }

    public Double getPageValue() {
        return pageValue;
    }

    public void setPageValue(Double pageValue) {
        this.pageValue = pageValue;
    }

    public Double getPageGrowthRate() {
        return pageGrowthRate;
    }

    public void setPageGrowthRate(Double pageGrowthRate) {
        this.pageGrowthRate = pageGrowthRate;
    }

    public Double getPageRg() {
        return pageRg;
    }

    public void setPageRg(Double pageRg) {
        this.pageRg = pageRg;
    }

    public Double getMobValue() {
        return mobValue;
    }

    public void setMobValue(Double mobValue) {
        this.mobValue = mobValue;
    }

    public Double getMobGrowthRate() {
        return mobGrowthRate;
    }

    public void setMobGrowthRate(Double mobGrowthRate) {
        this.mobGrowthRate = mobGrowthRate;
    }

    public Double getMobRg() {
        return mobRg;
    }

    public void setMobRg(Double mobRg) {
        this.mobRg = mobRg;
    }
}