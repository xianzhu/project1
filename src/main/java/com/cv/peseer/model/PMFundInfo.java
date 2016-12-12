package com.cv.peseer.model;

import java.io.Serializable;

public class PMFundInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer fundId;

    private String fundName;

    private String city;

    private String town;

    private String collectFinishDate;

    private String investDate;

    private String fundDesc;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getCollectFinishDate() {
        return collectFinishDate;
    }

    public void setCollectFinishDate(String collectFinishDate) {
        this.collectFinishDate = collectFinishDate == null ? null : collectFinishDate.trim();
    }

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate == null ? null : investDate.trim();
    }

    public String getFundDesc() {
        return fundDesc;
    }

    public void setFundDesc(String fundDesc) {
        this.fundDesc = fundDesc == null ? null : fundDesc.trim();
    }
}