package com.cv.peseer.model;

import java.io.Serializable;

public class PMUserInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer organizeId;

    private String organizeName;

    private String userId;

    private String userName;

    private String sex;

    private String title;

    private String focusDomain;

    private String investProjects;

    private String contact;

    private String description;

    public Integer getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Integer organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName == null ? null : organizeName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFocusDomain() {
        return focusDomain;
    }

    public void setFocusDomain(String focusDomain) {
        this.focusDomain = focusDomain == null ? null : focusDomain.trim();
    }

    public String getInvestProjects() {
        return investProjects;
    }

    public void setInvestProjects(String investProjects) {
        this.investProjects = investProjects == null ? null : investProjects.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}