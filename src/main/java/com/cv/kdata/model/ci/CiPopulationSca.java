package com.cv.kdata.model.ci;

public class CiPopulationSca {
    private Integer id;

    private String statDate;

    private Double totalPlt;

    private Double male;

    private Double female;

    private Double death;

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

    public Double getTotalPlt() {
        return totalPlt;
    }

    public void setTotalPlt(Double totalPlt) {
        this.totalPlt = totalPlt;
    }

    public Double getMale() {
        return male;
    }

    public void setMale(Double male) {
        this.male = male;
    }

    public Double getFemale() {
        return female;
    }

    public void setFemale(Double female) {
        this.female = female;
    }

    public Double getDeath() {
        return death;
    }

    public void setDeath(Double death) {
        this.death = death;
    }
}