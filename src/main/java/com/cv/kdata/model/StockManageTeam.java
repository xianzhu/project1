package com.cv.kdata.model;

import java.io.Serializable;

public class StockManageTeam implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String name;

    private String position;

    private String posType;

    private String posStartdate;

    private String posEnddate;

    private String sex;

    private String country;

    private String education;

    private String birthdate;

    private String resume;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType == null ? null : posType.trim();
    }

    public String getPosStartdate() {
        return posStartdate;
    }

    public void setPosStartdate(String posStartdate) {
        this.posStartdate = posStartdate == null ? null : posStartdate.trim();
    }

    public String getPosEnddate() {
        return posEnddate;
    }

    public void setPosEnddate(String posEnddate) {
        this.posEnddate = posEnddate == null ? null : posEnddate.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate == null ? null : birthdate.trim();
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }
}