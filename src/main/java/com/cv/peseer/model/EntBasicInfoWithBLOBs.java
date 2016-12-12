package com.cv.peseer.model;

public class EntBasicInfoWithBLOBs extends EntBasicInfo {
    private String bizArea;

    private String legalPerson;

    private String regAddr;

    private String operAddr;

    public String getBizArea() {
        return bizArea;
    }

    public void setBizArea(String bizArea) {
        this.bizArea = bizArea == null ? null : bizArea.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    public String getOperAddr() {
        return operAddr;
    }

    public void setOperAddr(String operAddr) {
        this.operAddr = operAddr == null ? null : operAddr.trim();
    }
}