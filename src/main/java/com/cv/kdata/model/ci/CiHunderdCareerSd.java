package com.cv.kdata.model.ci;

public class CiHunderdCareerSd {
    private Integer id;

    private String statDate;

    private Double peopleInNeed;

    private Double sumJobSeek;

    private Double vacancySeekRatio;

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

    public Double getPeopleInNeed() {
        return peopleInNeed;
    }

    public void setPeopleInNeed(Double peopleInNeed) {
        this.peopleInNeed = peopleInNeed;
    }

    public Double getSumJobSeek() {
        return sumJobSeek;
    }

    public void setSumJobSeek(Double sumJobSeek) {
        this.sumJobSeek = sumJobSeek;
    }

    public Double getVacancySeekRatio() {
        return vacancySeekRatio;
    }

    public void setVacancySeekRatio(Double vacancySeekRatio) {
        this.vacancySeekRatio = vacancySeekRatio;
    }
}