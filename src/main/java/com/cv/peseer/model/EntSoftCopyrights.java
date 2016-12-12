package com.cv.peseer.model;

import java.io.Serializable;

public class EntSoftCopyrights implements Serializable,  Comparable<EntSoftCopyrights>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String name;

    private String number;

    private String appDate;

    private String shortName;

    private String version;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate == null ? null : appDate.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    @Override
	public int compareTo(EntSoftCopyrights ent) {
		int i = ent.getAppDate().compareTo(this.getAppDate());
		return i;
	}
}