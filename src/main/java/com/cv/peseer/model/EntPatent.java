package com.cv.peseer.model;

import java.io.Serializable;

public class EntPatent implements Serializable, Comparable<EntPatent> {

    /**
	 *
	 */
	@Override
	public int compareTo(EntPatent ent) {
		int i = ent.getOuthorDate().compareTo(this.getOuthorDate());
		return i;
	}
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String patentName;

    private String typeName;

    private String outhorNum;

    private String requestNum;

    private String outhorDate;

    private String requestDate;

    private String brief;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName == null ? null : patentName.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getOuthorNum() {
        return outhorNum;
    }

    public void setOuthorNum(String outhorNum) {
        this.outhorNum = outhorNum == null ? null : outhorNum.trim();
    }

    public String getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(String requestNum) {
        this.requestNum = requestNum == null ? null : requestNum.trim();
    }

    public String getOuthorDate() {
        return outhorDate;
    }

    public void setOuthorDate(String outhorDate) {
        this.outhorDate = outhorDate == null ? null : outhorDate.trim();
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate == null ? null : requestDate.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }
}