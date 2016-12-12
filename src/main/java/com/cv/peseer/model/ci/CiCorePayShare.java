package com.cv.peseer.model.ci;

public class CiCorePayShare {
    private Integer id;

    private String statDate;

    private Double zfb;

    private Double cft;

    private Double ylzx;

    private Double kq;

    private Double hftx;

    private Double ybzf;

    private Double hxzf;

    private Double others;

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

    public Double getZfb() {
        return zfb;
    }

    public void setZfb(Double zfb) {
        this.zfb = zfb;
    }

    public Double getCft() {
        return cft;
    }

    public void setCft(Double cft) {
        this.cft = cft;
    }

    public Double getYlzx() {
        return ylzx;
    }

    public void setYlzx(Double ylzx) {
        this.ylzx = ylzx;
    }

    public Double getKq() {
        return kq;
    }

    public void setKq(Double kq) {
        this.kq = kq;
    }

    public Double getHftx() {
        return hftx;
    }

    public void setHftx(Double hftx) {
        this.hftx = hftx;
    }

    public Double getYbzf() {
        return ybzf;
    }

    public void setYbzf(Double ybzf) {
        this.ybzf = ybzf;
    }

    public Double getHxzf() {
        return hxzf;
    }

    public void setHxzf(Double hxzf) {
        this.hxzf = hxzf;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }
}