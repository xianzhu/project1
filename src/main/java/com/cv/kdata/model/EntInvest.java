package com.cv.kdata.model;

import java.io.Serializable;

public class EntInvest implements Serializable, Comparable<EntInvest>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String name;

    private String iUuid;

    private String sDate;

    private String legal;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getiUuid() {
        return iUuid;
    }

    public void setiUuid(String iUuid) {
        this.iUuid = iUuid == null ? null : iUuid.trim();
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate == null ? null : sDate.trim();
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal == null ? null : legal.trim();
    }

    @Override
	public int compareTo(EntInvest ent) {
		int i = ent.getsDate().compareTo(this.getsDate());
		return i;
	}
}