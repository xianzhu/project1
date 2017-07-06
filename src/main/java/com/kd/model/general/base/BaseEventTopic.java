package com.kd.model.general.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseEventTopic<M extends BaseEventTopic<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "event_topic";

	/**
	 * 自增ID
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 自增ID
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

	/**
	 * 事件ID
	 */
	public void setEventId(java.lang.Integer eventId) {
		set("event_id", eventId);
	}

	/**
	 * 事件ID
	 */
	public java.lang.Integer getEventId() {
		return get("event_id");
	}

	/**
	 * 1--投资，2--退出
	 */
	public void setEventType(java.lang.Integer eventType) {
		set("event_type", eventType);
	}

	/**
	 * 1--投资，2--退出
	 */
	public java.lang.Integer getEventType() {
		return get("event_type");
	}

	/**
	 * 对应的topicID
	 */
	public void setTopicId(java.lang.Integer topicId) {
		set("topic_id", topicId);
	}

	/**
	 * 对应的topicID
	 */
	public java.lang.Integer getTopicId() {
		return get("topic_id");
	}

	/**
	 * 
	 */
	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	/**
	 * 
	 */
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
