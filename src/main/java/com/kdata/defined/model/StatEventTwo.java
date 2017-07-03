package com.kdata.defined.model;

public class StatEventTwo {
	private int id;
	private int typeId;
	private String typeName;
	private double count;
	private String countDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

}
