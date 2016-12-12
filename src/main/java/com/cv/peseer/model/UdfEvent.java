package com.cv.peseer.model;

import java.io.Serializable;
import java.util.Date;

public class UdfEvent implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String uid;

    private Integer type;

    private Integer status;

    private String leader;

    private String fund;

    private String gpName;

    private String lpName;

    private String planLine;

    private String realLine;

    private String vcName;

    private String teamName;

    private String round;

    private String maPay;

    private String maBuyer;

    private String maTarget;

    private String exitName;

    private String exitBringRate;

    private String exitIrr;

    private String happenDate;

    private String srcInfo;

    private Date createTime;

    private Date updateTime;

    private String content;
    
    private String title;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund == null ? null : fund.trim();
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName == null ? null : gpName.trim();
    }

    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName == null ? null : lpName.trim();
    }

    public String getPlanLine() {
        return planLine;
    }

    public void setPlanLine(String planLine) {
        this.planLine = planLine == null ? null : planLine.trim();
    }

    public String getRealLine() {
        return realLine;
    }

    public void setRealLine(String realLine) {
        this.realLine = realLine == null ? null : realLine.trim();
    }

    public String getVcName() {
        return vcName;
    }

    public void setVcName(String vcName) {
        this.vcName = vcName == null ? null : vcName.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public String getMaPay() {
        return maPay;
    }

    public void setMaPay(String maPay) {
        this.maPay = maPay == null ? null : maPay.trim();
    }

    public String getMaBuyer() {
        return maBuyer;
    }

    public void setMaBuyer(String maBuyer) {
        this.maBuyer = maBuyer == null ? null : maBuyer.trim();
    }

    public String getMaTarget() {
        return maTarget;
    }

    public void setMaTarget(String maTarget) {
        this.maTarget = maTarget == null ? null : maTarget.trim();
    }

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName == null ? null : exitName.trim();
    }

    public String getExitBringRate() {
        return exitBringRate;
    }

    public void setExitBringRate(String exitBringRate) {
        this.exitBringRate = exitBringRate == null ? null : exitBringRate.trim();
    }

    public String getExitIrr() {
        return exitIrr;
    }

    public void setExitIrr(String exitIrr) {
        this.exitIrr = exitIrr == null ? null : exitIrr.trim();
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate == null ? null : happenDate.trim();
    }

    public String getSrcInfo() {
        return srcInfo;
    }

    public void setSrcInfo(String srcInfo) {
        this.srcInfo = srcInfo == null ? null : srcInfo.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}