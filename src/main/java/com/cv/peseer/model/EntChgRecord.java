package com.cv.peseer.model;

import java.io.Serializable;

public class EntChgRecord implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private String uuid;

    private String record;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }
}