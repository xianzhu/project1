package com.cv.peseer.model;

import java.io.Serializable;
import java.util.Date;

public class UdfRptTrader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String uid;

	private Integer type;

	private Integer frequency;

	private Date createTime;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "id=" + id + ", uid=" + uid + ", type=" + type + ", frequency=" + frequency + ", createTime="
				+ createTime + ", updateTime=" + updateTime;
	}

	// use to generate the filter condition
	public String getCondition() {
		if (type > 0) {
			return "type=" + type +" ";
		} else {
			return null;
		}
	}

}