package com.cv.peseer.model.ci;

public class CiNetScy {
    private Integer id;

    private String statDate;

    private Double netScyIndexValue;

    private Double caseTot;

    private Double caseSy;

    private Double caseFkrc;

    private Double netTot;

    private Double netGov;

    private Double placeTot;

    private Double placeGov;

    private Double fakeValue;

    private Double bugTot;

    private Double bugHighRisk;

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

    public Double getNetScyIndexValue() {
        return netScyIndexValue;
    }

    public void setNetScyIndexValue(Double netScyIndexValue) {
        this.netScyIndexValue = netScyIndexValue;
    }

    public Double getCaseTot() {
        return caseTot;
    }

    public void setCaseTot(Double caseTot) {
        this.caseTot = caseTot;
    }

    public Double getCaseSy() {
        return caseSy;
    }

    public void setCaseSy(Double caseSy) {
        this.caseSy = caseSy;
    }

    public Double getCaseFkrc() {
        return caseFkrc;
    }

    public void setCaseFkrc(Double caseFkrc) {
        this.caseFkrc = caseFkrc;
    }

    public Double getNetTot() {
        return netTot;
    }

    public void setNetTot(Double netTot) {
        this.netTot = netTot;
    }

    public Double getNetGov() {
        return netGov;
    }

    public void setNetGov(Double netGov) {
        this.netGov = netGov;
    }

    public Double getPlaceTot() {
        return placeTot;
    }

    public void setPlaceTot(Double placeTot) {
        this.placeTot = placeTot;
    }

    public Double getPlaceGov() {
        return placeGov;
    }

    public void setPlaceGov(Double placeGov) {
        this.placeGov = placeGov;
    }

    public Double getFakeValue() {
        return fakeValue;
    }

    public void setFakeValue(Double fakeValue) {
        this.fakeValue = fakeValue;
    }

    public Double getBugTot() {
        return bugTot;
    }

    public void setBugTot(Double bugTot) {
        this.bugTot = bugTot;
    }

    public Double getBugHighRisk() {
        return bugHighRisk;
    }

    public void setBugHighRisk(Double bugHighRisk) {
        this.bugHighRisk = bugHighRisk;
    }
}