package com.cv.kdata.model;

import java.io.Serializable;

public class EntLaw implements Serializable , Comparable<EntLaw>{

    /**
	 *
	 */
	@Override
	public int compareTo(EntLaw ent) {
		int i = ent.getDate().compareTo(this.getDate());
		return i;
	}
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String caseNo;

    private String date;

    private String type;

    private String title;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}