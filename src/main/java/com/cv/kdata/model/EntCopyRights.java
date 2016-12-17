package com.cv.kdata.model;

import java.io.Serializable;

public class EntCopyRights implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String name;

    private String number;

    private String appDate;

    private String type;

    private String finDate;

    private String pubDate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFinDate() {
        return finDate;
    }

    public void setFinDate(String finDate) {
        this.finDate = finDate == null ? null : finDate.trim();
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate == null ? null : pubDate.trim();
    }
}