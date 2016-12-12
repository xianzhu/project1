package com.cv.peseer.model;

import java.io.Serializable;

public class RptOrgOverallTrends implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String durTime;

    private Integer iNum;

    private Integer eNum;

    private Double psIbmpa;

    public String getDurTime() {
        return durTime;
    }

    public void setDurTime(String durTime) {
        this.durTime = durTime == null ? null : durTime.trim();
    }

    public Integer getiNum() {
        return iNum;
    }

    public void setiNum(Integer iNum) {
        this.iNum = iNum;
    }

    public Integer geteNum() {
        return eNum;
    }

    public void seteNum(Integer eNum) {
        this.eNum = eNum;
    }

    public Double getPsIbmpa() {
        return psIbmpa;
    }

    public void setPsIbmpa(Double psIbmpa) {
        this.psIbmpa = psIbmpa;
    }
}