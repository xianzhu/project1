package com.cv.kdata.model;

import java.io.Serializable;

import org.msgpack.annotation.Message;

import com.alibaba.fastjson.annotation.JSONField;

//统计信息包含4个 目前暂定 income、order、vistits、activity
@Message
public class StaticsInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONField(ordinal = 1)
	private StaticsItem media;
	@JSONField(ordinal = 2)
	private StaticsItem report;
	@JSONField(ordinal = 3)
	private StaticsItem ent;
	@JSONField(ordinal = 4)
	private StaticsItem event;
	public StaticsItem getMedia() {
		return media;
	}
	public void setMedia(StaticsItem media) {
		this.media = media;
	}
	public StaticsItem getReport() {
		return report;
	}
	public void setReport(StaticsItem report) {
		this.report = report;
	}
	public StaticsItem getEnt() {
		return ent;
	}
	public void setEnt(StaticsItem ent) {
		this.ent = ent;
	}
	public StaticsItem getEvent() {
		return event;
	}
	public void setEvent(StaticsItem event) {
		this.event = event;
	}
	
	
}
