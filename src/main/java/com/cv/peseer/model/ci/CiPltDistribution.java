package com.cv.peseer.model.ci;

public class CiPltDistribution {
    private Integer id;

    private String statDate;

    private Double urbanFinalValue;

    private Double urbanProt;

    private Double ruralFinalValue;

    private Double ruralProt;

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

    public Double getUrbanFinalValue() {
        return urbanFinalValue;
    }

    public void setUrbanFinalValue(Double urbanFinalValue) {
        this.urbanFinalValue = urbanFinalValue;
    }

    public Double getUrbanProt() {
        return urbanProt;
    }

    public void setUrbanProt(Double urbanProt) {
        this.urbanProt = urbanProt;
    }

    public Double getRuralFinalValue() {
        return ruralFinalValue;
    }

    public void setRuralFinalValue(Double ruralFinalValue) {
        this.ruralFinalValue = ruralFinalValue;
    }

    public Double getRuralProt() {
        return ruralProt;
    }

    public void setRuralProt(Double ruralProt) {
        this.ruralProt = ruralProt;
    }
}