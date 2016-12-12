package com.cv.peseer.model.ci;

public class CiPayRank {
    private Integer id;

    private String statDate;

    private Integer ranking;

    private String appName;

    private Double coverRate;

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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public Double getCoverRate() {
        return coverRate;
    }

    public void setCoverRate(Double coverRate) {
        this.coverRate = coverRate;
    }
}