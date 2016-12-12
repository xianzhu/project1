package com.cv.peseer.model.ci;

public class CiChnFilmStat {
    private Integer id;

    private String statDate;

    private Double filmValue;

    private Double filmNum;

    private Double playNum;

    private Double adim;

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

    public Double getFilmValue() {
        return filmValue;
    }

    public void setFilmValue(Double filmValue) {
        this.filmValue = filmValue;
    }

    public Double getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(Double filmNum) {
        this.filmNum = filmNum;
    }

    public Double getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Double playNum) {
        this.playNum = playNum;
    }

    public Double getAdim() {
        return adim;
    }

    public void setAdim(Double adim) {
        this.adim = adim;
    }
}