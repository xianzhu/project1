package com.cv.peseer.model;

import java.io.Serializable;

public class PMExitEvent implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer eventId;

    private String entCnName;

    private String exitType;

    private String eventTitle;

    private String userName;

    private String happenDate;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEntCnName() {
        return entCnName;
    }

    public void setEntCnName(String entCnName) {
        this.entCnName = entCnName == null ? null : entCnName.trim();
    }

    public String getExitType() {
        return exitType;
    }

    public void setExitType(String exitType) {
        this.exitType = exitType == null ? null : exitType.trim();
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle == null ? null : eventTitle.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate == null ? null : happenDate.trim();
    }
}