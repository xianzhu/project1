package com.cv.kdata.model;

public class UserInfoWithBLOBs extends UserInfo {
    private String peopleInfo;

    private String orgInfo;

    private String domainTips;

    private String telephone;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getVentureTips() {
        return ventureTips;
    }

    public void setVentureTips(String ventureTips) {
        this.ventureTips = ventureTips == null ? null : ventureTips.trim();
    }
}