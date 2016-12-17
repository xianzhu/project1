package com.cv.kdata.model.ci;

public class CiEngelCoefficient {
    private Integer id;

    private String statDate;

    private Double town;

    private Double village;

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

    public Double getTown() {
        return town;
    }

    public void setTown(Double town) {
        this.town = town;
    }

    public Double getVillage() {
        return village;
    }

    public void setVillage(Double village) {
        this.village = village;
    }
}