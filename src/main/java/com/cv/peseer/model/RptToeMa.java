package com.cv.peseer.model;

import java.io.Serializable;
import java.util.Date;

public class RptToeMa implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer maId;

    private String toeCode;

    private String toeName;

    private String toeUuid;

    private String happenDate;

    private String startDate;

    private String tips;

    private String targetName;

    private String targetUuid;

    private String targetRelation;

    private String targetIndustry;

    private String buyerName;

    private String buyerToeUuid;

    private String buyerToeCode;

    private String buyerType;

    private String buyerRelation;

    private String buyerIndustry;

    private Double buyerStockNum;

    private Double buyerStockRate;

    private String ownerName;

    private String ownerToeUuid;

    private String ownerToeCode;

    private String ownerType;

    private String ownerRelation;

    private String ownerIndustry;

    private Double ownerStockNum;

    private Double ownerStockRate;

    private String maSeason;

    private String maType;

    private String payMode;

    private Double pay;

    private String payKind;

    private Double transPrice;

    private String transKind;

    private String targetType;

    private Double transStockRate;

    private String ifChanged;

    private Date createTime;

    private Date updateTime;

    private String abst;

    public Integer getMaId() {
        return maId;
    }

    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    public String getToeCode() {
        return toeCode;
    }

    public void setToeCode(String toeCode) {
        this.toeCode = toeCode == null ? null : toeCode.trim();
    }

    public String getToeName() {
        return toeName;
    }

    public void setToeName(String toeName) {
        this.toeName = toeName == null ? null : toeName.trim();
    }

    public String getToeUuid() {
        return toeUuid;
    }

    public void setToeUuid(String toeUuid) {
        this.toeUuid = toeUuid == null ? null : toeUuid.trim();
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate == null ? null : happenDate.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName == null ? null : targetName.trim();
    }

    public String getTargetUuid() {
        return targetUuid;
    }

    public void setTargetUuid(String targetUuid) {
        this.targetUuid = targetUuid == null ? null : targetUuid.trim();
    }

    public String getTargetRelation() {
        return targetRelation;
    }

    public void setTargetRelation(String targetRelation) {
        this.targetRelation = targetRelation == null ? null : targetRelation.trim();
    }

    public String getTargetIndustry() {
        return targetIndustry;
    }

    public void setTargetIndustry(String targetIndustry) {
        this.targetIndustry = targetIndustry == null ? null : targetIndustry.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerToeUuid() {
        return buyerToeUuid;
    }

    public void setBuyerToeUuid(String buyerToeUuid) {
        this.buyerToeUuid = buyerToeUuid == null ? null : buyerToeUuid.trim();
    }

    public String getBuyerToeCode() {
        return buyerToeCode;
    }

    public void setBuyerToeCode(String buyerToeCode) {
        this.buyerToeCode = buyerToeCode == null ? null : buyerToeCode.trim();
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType == null ? null : buyerType.trim();
    }

    public String getBuyerRelation() {
        return buyerRelation;
    }

    public void setBuyerRelation(String buyerRelation) {
        this.buyerRelation = buyerRelation == null ? null : buyerRelation.trim();
    }

    public String getBuyerIndustry() {
        return buyerIndustry;
    }

    public void setBuyerIndustry(String buyerIndustry) {
        this.buyerIndustry = buyerIndustry == null ? null : buyerIndustry.trim();
    }

    public Double getBuyerStockNum() {
        return buyerStockNum;
    }

    public void setBuyerStockNum(Double buyerStockNum) {
        this.buyerStockNum = buyerStockNum;
    }

    public Double getBuyerStockRate() {
        return buyerStockRate;
    }

    public void setBuyerStockRate(Double buyerStockRate) {
        this.buyerStockRate = buyerStockRate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerToeUuid() {
        return ownerToeUuid;
    }

    public void setOwnerToeUuid(String ownerToeUuid) {
        this.ownerToeUuid = ownerToeUuid == null ? null : ownerToeUuid.trim();
    }

    public String getOwnerToeCode() {
        return ownerToeCode;
    }

    public void setOwnerToeCode(String ownerToeCode) {
        this.ownerToeCode = ownerToeCode == null ? null : ownerToeCode.trim();
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    public String getOwnerRelation() {
        return ownerRelation;
    }

    public void setOwnerRelation(String ownerRelation) {
        this.ownerRelation = ownerRelation == null ? null : ownerRelation.trim();
    }

    public String getOwnerIndustry() {
        return ownerIndustry;
    }

    public void setOwnerIndustry(String ownerIndustry) {
        this.ownerIndustry = ownerIndustry == null ? null : ownerIndustry.trim();
    }

    public Double getOwnerStockNum() {
        return ownerStockNum;
    }

    public void setOwnerStockNum(Double ownerStockNum) {
        this.ownerStockNum = ownerStockNum;
    }

    public Double getOwnerStockRate() {
        return ownerStockRate;
    }

    public void setOwnerStockRate(Double ownerStockRate) {
        this.ownerStockRate = ownerStockRate;
    }

    public String getMaSeason() {
        return maSeason;
    }

    public void setMaSeason(String maSeason) {
        this.maSeason = maSeason == null ? null : maSeason.trim();
    }

    public String getMaType() {
        return maType;
    }

    public void setMaType(String maType) {
        this.maType = maType == null ? null : maType.trim();
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public String getPayKind() {
        return payKind;
    }

    public void setPayKind(String payKind) {
        this.payKind = payKind == null ? null : payKind.trim();
    }

    public Double getTransPrice() {
        return transPrice;
    }

    public void setTransPrice(Double transPrice) {
        this.transPrice = transPrice;
    }

    public String getTransKind() {
        return transKind;
    }

    public void setTransKind(String transKind) {
        this.transKind = transKind == null ? null : transKind.trim();
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    public Double getTransStockRate() {
        return transStockRate;
    }

    public void setTransStockRate(Double transStockRate) {
        this.transStockRate = transStockRate;
    }

    public String getIfChanged() {
        return ifChanged;
    }

    public void setIfChanged(String ifChanged) {
        this.ifChanged = ifChanged == null ? null : ifChanged.trim();
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

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst == null ? null : abst.trim();
    }
}