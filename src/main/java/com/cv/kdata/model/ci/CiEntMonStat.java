package com.cv.kdata.model.ci;

public class CiEntMonStat {
    private Integer id;

    private String statDate;

    private Integer ranking;

    private String filmName;

    private Double monBox;

    private Double prop;

    private Double avgPrice;

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

    public Double getMonBox() {
        return monBox;
    }

    public void setMonBox(Double monBox) {
        this.monBox = monBox;
    }

    public Double getProp() {
        return prop;
    }

    public void setProp(Double prop) {
        this.prop = prop;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}