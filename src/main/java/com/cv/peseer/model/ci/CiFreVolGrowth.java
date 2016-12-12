package com.cv.peseer.model.ci;

public class CiFreVolGrowth {
    private Integer id;

    private String statDate;

    private Double railwayMonUpRate;

    private Double railwayCumUpRate;

    private Double roadMonUpRate;

    private Double roadCumUpRate;

    private Double waterMonUpRate;

    private Double waterCumUpRate;

    private Double airMonUpRate;

    private Double airCumUpRate;

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

    public Double getRailwayMonUpRate() {
        return railwayMonUpRate;
    }

    public void setRailwayMonUpRate(Double railwayMonUpRate) {
        this.railwayMonUpRate = railwayMonUpRate;
    }

    public Double getRailwayCumUpRate() {
        return railwayCumUpRate;
    }

    public void setRailwayCumUpRate(Double railwayCumUpRate) {
        this.railwayCumUpRate = railwayCumUpRate;
    }

    public Double getRoadMonUpRate() {
        return roadMonUpRate;
    }

    public void setRoadMonUpRate(Double roadMonUpRate) {
        this.roadMonUpRate = roadMonUpRate;
    }

    public Double getRoadCumUpRate() {
        return roadCumUpRate;
    }

    public void setRoadCumUpRate(Double roadCumUpRate) {
        this.roadCumUpRate = roadCumUpRate;
    }

    public Double getWaterMonUpRate() {
        return waterMonUpRate;
    }

    public void setWaterMonUpRate(Double waterMonUpRate) {
        this.waterMonUpRate = waterMonUpRate;
    }

    public Double getWaterCumUpRate() {
        return waterCumUpRate;
    }

    public void setWaterCumUpRate(Double waterCumUpRate) {
        this.waterCumUpRate = waterCumUpRate;
    }

    public Double getAirMonUpRate() {
        return airMonUpRate;
    }

    public void setAirMonUpRate(Double airMonUpRate) {
        this.airMonUpRate = airMonUpRate;
    }

    public Double getAirCumUpRate() {
        return airCumUpRate;
    }

    public void setAirCumUpRate(Double airCumUpRate) {
        this.airCumUpRate = airCumUpRate;
    }
}