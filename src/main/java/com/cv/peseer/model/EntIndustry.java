package com.cv.peseer.model;

import java.io.Serializable;

public class EntIndustry implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String csrcIndustry;

    private String csrcCode;

    private String swIndustry;

    private String swCode;

    private String emIndustry;

    private String emCode;

    private String xsbManageIndustry;

    private String xsbManageCode;

    private String xsbInvestIndustry;

    private String xsbInvestCode;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getCsrcIndustry() {
        return csrcIndustry;
    }

    public void setCsrcIndustry(String csrcIndustry) {
        this.csrcIndustry = csrcIndustry == null ? null : csrcIndustry.trim();
    }

    public String getCsrcCode() {
        return csrcCode;
    }

    public void setCsrcCode(String csrcCode) {
        this.csrcCode = csrcCode == null ? null : csrcCode.trim();
    }

    public String getSwIndustry() {
        return swIndustry;
    }

    public void setSwIndustry(String swIndustry) {
        this.swIndustry = swIndustry == null ? null : swIndustry.trim();
    }

    public String getSwCode() {
        return swCode;
    }

    public void setSwCode(String swCode) {
        this.swCode = swCode == null ? null : swCode.trim();
    }

    public String getEmIndustry() {
        return emIndustry;
    }

    public void setEmIndustry(String emIndustry) {
        this.emIndustry = emIndustry == null ? null : emIndustry.trim();
    }

    public String getEmCode() {
        return emCode;
    }

    public void setEmCode(String emCode) {
        this.emCode = emCode == null ? null : emCode.trim();
    }

    public String getXsbManageIndustry() {
        return xsbManageIndustry;
    }

    public void setXsbManageIndustry(String xsbManageIndustry) {
        this.xsbManageIndustry = xsbManageIndustry == null ? null : xsbManageIndustry.trim();
    }

    public String getXsbManageCode() {
        return xsbManageCode;
    }

    public void setXsbManageCode(String xsbManageCode) {
        this.xsbManageCode = xsbManageCode == null ? null : xsbManageCode.trim();
    }

    public String getXsbInvestIndustry() {
        return xsbInvestIndustry;
    }

    public void setXsbInvestIndustry(String xsbInvestIndustry) {
        this.xsbInvestIndustry = xsbInvestIndustry == null ? null : xsbInvestIndustry.trim();
    }

    public String getXsbInvestCode() {
        return xsbInvestCode;
    }

    public void setXsbInvestCode(String xsbInvestCode) {
        this.xsbInvestCode = xsbInvestCode == null ? null : xsbInvestCode.trim();
    }
}