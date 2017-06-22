package com.kdata.defined.model;

public class EventTypeMap {
	private int childId;
	private String childType;
	private int fatherId;
	private String fatherType;

	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public String getChildType() {
		return childType;
	}
	public void setChildType(String childType) {
		this.childType = childType;
	}
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}
	public String getFatherType() {
		return fatherType;
	}
	public void setFatherType(String fatherType) {
		this.fatherType = fatherType;
	}

}
