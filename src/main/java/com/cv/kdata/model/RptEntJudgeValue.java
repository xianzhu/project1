package com.cv.kdata.model;

import java.io.Serializable;

public class RptEntJudgeValue implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private Double bs;

    private Double ts;

    private Double crds;

    private Double caps;

    private Double ss;

    private Double avgBs;

    private Double avgTs;

    private Double avgCrds;

    private Double avgCaps;

    private Double avgSs;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Double getBs() {
        return bs;
    }

    public void setBs(Double bs) {
        this.bs = bs;
    }

    public Double getTs() {
        return ts;
    }

    public void setTs(Double ts) {
        this.ts = ts;
    }

    public Double getCrds() {
        return crds;
    }

    public void setCrds(Double crds) {
        this.crds = crds;
    }

    public Double getCaps() {
        return caps;
    }

    public void setCaps(Double caps) {
        this.caps = caps;
    }

    public Double getSs() {
        return ss;
    }

    public void setSs(Double ss) {
        this.ss = ss;
    }

    public Double getAvgBs() {
        return avgBs;
    }

    public void setAvgBs(Double avgBs) {
        this.avgBs = avgBs;
    }

    public Double getAvgTs() {
        return avgTs;
    }

    public void setAvgTs(Double avgTs) {
        this.avgTs = avgTs;
    }

    public Double getAvgCrds() {
        return avgCrds;
    }

    public void setAvgCrds(Double avgCrds) {
        this.avgCrds = avgCrds;
    }

    public Double getAvgCaps() {
        return avgCaps;
    }

    public void setAvgCaps(Double avgCaps) {
        this.avgCaps = avgCaps;
    }

    public Double getAvgSs() {
        return avgSs;
    }

    public void setAvgSs(Double avgSs) {
        this.avgSs = avgSs;
    }
}