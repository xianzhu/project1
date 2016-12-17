package com.cv.kdata.model;

import java.io.Serializable;

public class EntEquity implements Serializable , Comparable<EntEquity>{

    /**
	 * 
	 */
	@Override
	public int compareTo(EntEquity ent) {
		int i = ent.getDate().compareTo(this.getDate());
		return i;
	}
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String date;

    private String number;

    private String pdgAmount;

    private String pdg;

    private String pdgIdentifyNo;

    private String pw;

    private String pwIdNo;

    private String status;

    private String remark;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPdgAmount() {
        return pdgAmount;
    }

    public void setPdgAmount(String pdgAmount) {
        this.pdgAmount = pdgAmount == null ? null : pdgAmount.trim();
    }

    public String getPdg() {
        return pdg;
    }

    public void setPdg(String pdg) {
        this.pdg = pdg == null ? null : pdg.trim();
    }

    public String getPdgIdentifyNo() {
        return pdgIdentifyNo;
    }

    public void setPdgIdentifyNo(String pdgIdentifyNo) {
        this.pdgIdentifyNo = pdgIdentifyNo == null ? null : pdgIdentifyNo.trim();
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw == null ? null : pw.trim();
    }

    public String getPwIdNo() {
        return pwIdNo;
    }

    public void setPwIdNo(String pwIdNo) {
        this.pwIdNo = pwIdNo == null ? null : pwIdNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}