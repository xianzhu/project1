package com.cv.kdata.model;

import java.io.Serializable;

public class EntRelated implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String mailSuffix;

    private String entId;

    private String entName;

    public String getMailSuffix() {
        return mailSuffix;
    }

    public void setMailSuffix(String mailSuffix) {
        this.mailSuffix = mailSuffix == null ? null : mailSuffix.trim();
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId == null ? null : entId.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }
}