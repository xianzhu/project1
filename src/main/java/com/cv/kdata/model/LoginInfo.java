package com.cv.kdata.model;

import java.io.Serializable;
import java.util.Date;

public class LoginInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String uid;

    private String pwd;

    private String type;

    private String cookie;

    private Integer orgId;

    private String orgName;

    private String orgAlias;

    private String cnName;

    private Integer sex;

    private String level;

    private String rptTips;

    private String otherTips;

    private Integer score;

    private Date createTime;

    private Date updateTime;
    
    private String peopleInfo;

    private String orgInfo;

    private String domainTips;

    private String ventureTips;

    public String getPeopleInfo() {
        return peopleInfo;
    }

    public void setPeopleInfo(String peopleInfo) {
        this.peopleInfo = peopleInfo == null ? null : peopleInfo.trim();
    }

    public String getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(String orgInfo) {
        this.orgInfo = orgInfo == null ? null : orgInfo.trim();
    }

    public String getDomainTips() {
        return domainTips;
    }

    public void setDomainTips(String domainTips) {
        this.domainTips = domainTips == null ? null : domainTips.trim();
    }

    public String getVentureTips() {
        return ventureTips;
    }

    public void setVentureTips(String ventureTips) {
        this.ventureTips = ventureTips == null ? null : ventureTips.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias == null ? null : orgAlias.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getRptTips() {
        return rptTips;
    }

    public void setRptTips(String rptTips) {
        this.rptTips = rptTips == null ? null : rptTips.trim();
    }

    public String getOtherTips() {
        return otherTips;
    }

    public void setOtherTips(String otherTips) {
        this.otherTips = otherTips == null ? null : otherTips.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}