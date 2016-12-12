package com.cv.peseer.model;

import java.io.Serializable;

public class EntMortgages implements Serializable , Comparable<EntMortgages>{

    /**
	 *
	 */
	@Override
	public int compareTo(EntMortgages ent) {
		int i = ent.getDate().compareTo(this.getDate());
		return i;
	}
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String number;

    private String date;

    private String status;

    private String department;

    private String amount;

    private String type;

    private String period;

    private String debitType;

    private String debitCurrency;

    private String debitAmount;

    private String debitPeriod;

    private String debitRemarks;

    private String closeDate;

    private String closeReason;

    private String seqNo;

    private String remarksGoods;

    private String belongTo;

    private String nameGoods;

    private String seqGoods;

    private String identifyNo;

    private String seqPerson;

    private String namePerson;

    private String identifyType;
    
    private String scope;

    private String remarks;

    private String debitScope;

    private String content;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDebitScope() {
        return debitScope;
    }

    public void setDebitScope(String debitScope) {
        this.debitScope = debitScope == null ? null : debitScope.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getDebitType() {
        return debitType;
    }

    public void setDebitType(String debitType) {
        this.debitType = debitType == null ? null : debitType.trim();
    }

    public String getDebitCurrency() {
        return debitCurrency;
    }

    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency == null ? null : debitCurrency.trim();
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount == null ? null : debitAmount.trim();
    }

    public String getDebitPeriod() {
        return debitPeriod;
    }

    public void setDebitPeriod(String debitPeriod) {
        this.debitPeriod = debitPeriod == null ? null : debitPeriod.trim();
    }

    public String getDebitRemarks() {
        return debitRemarks;
    }

    public void setDebitRemarks(String debitRemarks) {
        this.debitRemarks = debitRemarks == null ? null : debitRemarks.trim();
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate == null ? null : closeDate.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getRemarksGoods() {
        return remarksGoods;
    }

    public void setRemarksGoods(String remarksGoods) {
        this.remarksGoods = remarksGoods == null ? null : remarksGoods.trim();
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo == null ? null : belongTo.trim();
    }

    public String getNameGoods() {
        return nameGoods;
    }

    public void setNameGoods(String nameGoods) {
        this.nameGoods = nameGoods == null ? null : nameGoods.trim();
    }

    public String getSeqGoods() {
        return seqGoods;
    }

    public void setSeqGoods(String seqGoods) {
        this.seqGoods = seqGoods == null ? null : seqGoods.trim();
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo == null ? null : identifyNo.trim();
    }

    public String getSeqPerson() {
        return seqPerson;
    }

    public void setSeqPerson(String seqPerson) {
        this.seqPerson = seqPerson == null ? null : seqPerson.trim();
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson == null ? null : namePerson.trim();
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType == null ? null : identifyType.trim();
    }
}