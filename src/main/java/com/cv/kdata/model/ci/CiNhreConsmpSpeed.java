package com.cv.kdata.model.ci;

public class CiNhreConsmpSpeed {
    private Integer id;

    private String statDate;

    private Double sumRetail;

    private Double food;

    private Double clothes;

    private Double makeUp;

    private Double dailyUse;

    private Double jewelry;

    private Double appliance;

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

    public Double getSumRetail() {
        return sumRetail;
    }

    public void setSumRetail(Double sumRetail) {
        this.sumRetail = sumRetail;
    }

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public Double getClothes() {
        return clothes;
    }

    public void setClothes(Double clothes) {
        this.clothes = clothes;
    }

    public Double getMakeUp() {
        return makeUp;
    }

    public void setMakeUp(Double makeUp) {
        this.makeUp = makeUp;
    }

    public Double getDailyUse() {
        return dailyUse;
    }

    public void setDailyUse(Double dailyUse) {
        this.dailyUse = dailyUse;
    }

    public Double getJewelry() {
        return jewelry;
    }

    public void setJewelry(Double jewelry) {
        this.jewelry = jewelry;
    }

    public Double getAppliance() {
        return appliance;
    }

    public void setAppliance(Double appliance) {
        this.appliance = appliance;
    }
}