package com.cv.kdata.model;

import java.io.Serializable;

public class StockInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String cnName;

    private String enName;

    private String cnDesc;

    private String mainProduct;

    private String setupDate;

    private String listDate;

    private String bizRegNo;

    private Long regCapital;

    private String repOfAp;

    private String actCtrl;

    private String finalCtrl;

    private String csrcIndustry;

    private Long employeeNum;

    private String ceo;

    private String boardSecretary;

    private String prov;

    private String city;

    private String regAddr;

    private String officeAddr;

    private String postcode;

    private String tel;

    private String fax;

    private String email;

    private String website;

    private String scopeOfBusiness;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getCnDesc() {
        return cnDesc;
    }

    public void setCnDesc(String cnDesc) {
        this.cnDesc = cnDesc == null ? null : cnDesc.trim();
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct == null ? null : mainProduct.trim();
    }

    public String getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(String setupDate) {
        this.setupDate = setupDate == null ? null : setupDate.trim();
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate == null ? null : listDate.trim();
    }

    public String getBizRegNo() {
        return bizRegNo;
    }

    public void setBizRegNo(String bizRegNo) {
        this.bizRegNo = bizRegNo == null ? null : bizRegNo.trim();
    }

    public Long getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Long regCapital) {
        this.regCapital = regCapital;
    }

    public String getRepOfAp() {
        return repOfAp;
    }

    public void setRepOfAp(String repOfAp) {
        this.repOfAp = repOfAp == null ? null : repOfAp.trim();
    }

    public String getActCtrl() {
        return actCtrl;
    }

    public void setActCtrl(String actCtrl) {
        this.actCtrl = actCtrl == null ? null : actCtrl.trim();
    }

    public String getFinalCtrl() {
        return finalCtrl;
    }

    public void setFinalCtrl(String finalCtrl) {
        this.finalCtrl = finalCtrl == null ? null : finalCtrl.trim();
    }

    public String getCsrcIndustry() {
        return csrcIndustry;
    }

    public void setCsrcIndustry(String csrcIndustry) {
        this.csrcIndustry = csrcIndustry == null ? null : csrcIndustry.trim();
    }

    public Long getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Long employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo == null ? null : ceo.trim();
    }

    public String getBoardSecretary() {
        return boardSecretary;
    }

    public void setBoardSecretary(String boardSecretary) {
        this.boardSecretary = boardSecretary == null ? null : boardSecretary.trim();
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr == null ? null : officeAddr.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getScopeOfBusiness() {
        return scopeOfBusiness;
    }

    public void setScopeOfBusiness(String scopeOfBusiness) {
        this.scopeOfBusiness = scopeOfBusiness == null ? null : scopeOfBusiness.trim();
    }
}