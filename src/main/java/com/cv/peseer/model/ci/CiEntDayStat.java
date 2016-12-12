package com.cv.peseer.model.ci;

public class CiEntDayStat {
    private Integer id;

    private String statDate;

    private Integer ranking;

    private String filmName;

    private Double dayBox;

    private Double rg;

    private Double accumBox;

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

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName == null ? null : filmName.trim();
    }

    public Double getDayBox() {
        return dayBox;
    }

    public void setDayBox(Double dayBox) {
        this.dayBox = dayBox;
    }

    public Double getRg() {
        return rg;
    }

    public void setRg(Double rg) {
        this.rg = rg;
    }

    public Double getAccumBox() {
        return accumBox;
    }

    public void setAccumBox(Double accumBox) {
        this.accumBox = accumBox;
    }
}