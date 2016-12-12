package com.cv.peseer.model.ci;

public class CiOlgIncomComp {
    private Integer id;

    private String statDate;

    private Double clientOlg;

    private Double pageOlg;

    private Double mobOlg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate == null ? null : statDate.trim();
    }

    public Double getClientOlg() {
        return clientOlg;
    }

    public void setClientOlg(Double clientOlg) {
        this.clientOlg = clientOlg;
    }

    public Double getPageOlg() {
        return pageOlg;
    }

    public void setPageOlg(Double pageOlg) {
        this.pageOlg = pageOlg;
    }

    public Double getMobOlg() {
        return mobOlg;
    }

    public void setMobOlg(Double mobOlg) {
        this.mobOlg = mobOlg;
    }
}