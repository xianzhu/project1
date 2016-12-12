package com.cv.peseer.model;

import java.io.Serializable;

import org.msgpack.annotation.Message;

@Message
public class StaticsItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "StaticsItem [itemName=" + itemName + ", total=" + total + ", update=" + update + "]";
	}
	private String itemName;
	private int total;
	private int update;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}

	public StaticsItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StaticsItem(String itemName, int item1, int item2) {
		super();
		this.itemName = itemName;
		this.total = item1;
		this.update = item2;
	}

}