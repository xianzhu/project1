package com.kdata.defined.model;

public class StatDashboard {
	private int id;
	private String name;
	private int statMin;
	private int statMedian;
	private int statMax;
	private int count;
	private String statDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getStatMin() {
		return statMin;
	}
	public void setStatMin(int statMin) {
		this.statMin = statMin;
	}
	public int getStatMedian() {
		return statMedian;
	}
	public void setStatMedian(int statMedian) {
		this.statMedian = statMedian;
	}
	public int getStatMax() {
		return statMax;
	}
	public void setStatMax(int statMax) {
		this.statMax = statMax;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
}
