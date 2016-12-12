package com.cv.peseer.model.ci;

public class CiPltGrowth {
    private Integer id;

    private String statDate;

    private Double birth;

    private Double death;

    private Double increase;

    private Double birthRate;

    private Double deathRate;

    private Double increaseRate;

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

    public Double getBirth() {
        return birth;
    }

    public void setBirth(Double birth) {
        this.birth = birth;
    }

    public Double getDeath() {
        return death;
    }

    public void setDeath(Double death) {
        this.death = death;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public Double getBirthRate() {
        return birthRate;
    }

    public void setBirthRate(Double birthRate) {
        this.birthRate = birthRate;
    }

    public Double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(Double deathRate) {
        this.deathRate = deathRate;
    }

    public Double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(Double increaseRate) {
        this.increaseRate = increaseRate;
    }
}