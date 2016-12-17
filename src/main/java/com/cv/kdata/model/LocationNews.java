package com.cv.kdata.model;

import com.alibaba.fastjson.annotation.JSONField;

public class LocationNews {
	@JSONField(ordinal = 1)
	private String id;
	@JSONField(ordinal = 2)
	private Double lng;
	@JSONField(ordinal = 3)
	private Double lat;
	@JSONField(ordinal = 4)
	private String msg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Double getLng() {
		return lng;
	}
	
	public void setLngLat(LngLat lngLat){
		this.lng = lngLat.Lng;
		this.lat = lngLat.Lat;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
