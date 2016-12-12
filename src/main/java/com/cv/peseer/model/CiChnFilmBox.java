package com.cv.peseer.model;

import java.io.Serializable;

public class CiChnFilmBox implements Serializable {
    @Override
	public String toString() {
		return "CiChnFilmBox [id=" + id + ", statDate=" + statDate + ", totYearValue=" + totYearValue
				+ ", totGrowthRate=" + totGrowthRate + ", domYearValue=" + domYearValue + ", domGrowthRate="
				+ domGrowthRate + ", impYearValue=" + impYearValue + ", impGrowthRate=" + impGrowthRate + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 946694080680697703L;

	private Integer id;

    private String statDate;

    private Double totYearValue;

    private Double totGrowthRate;

    private Double domYearValue;

    private Double domGrowthRate;

    private Double impYearValue;

    private Double impGrowthRate;

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

    public Double getTotYearValue() {
        return totYearValue;
    }

    public void setTotYearValue(Double totYearValue) {
        this.totYearValue = totYearValue;
    }

    public Double getTotGrowthRate() {
        return totGrowthRate;
    }

    public void setTotGrowthRate(Double totGrowthRate) {
        this.totGrowthRate = totGrowthRate;
    }

    public Double getDomYearValue() {
        return domYearValue;
    }

    public void setDomYearValue(Double domYearValue) {
        this.domYearValue = domYearValue;
    }

    public Double getDomGrowthRate() {
        return domGrowthRate;
    }

    public void setDomGrowthRate(Double domGrowthRate) {
        this.domGrowthRate = domGrowthRate;
    }

    public Double getImpYearValue() {
        return impYearValue;
    }

    public void setImpYearValue(Double impYearValue) {
        this.impYearValue = impYearValue;
    }

    public Double getImpGrowthRate() {
        return impGrowthRate;
    }

    public void setImpGrowthRate(Double impGrowthRate) {
        this.impGrowthRate = impGrowthRate;
    }
}