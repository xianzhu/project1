package com.kd.model.general;

import java.io.Serializable;

public class EntGeneralObject implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3859097360165206274L;

	private String uuid;
	private String record;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String jsonString) {
		this.record = jsonString;
	}

}
