package com.kd.model.general.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePmInvestEvent<M extends BasePmInvestEvent<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "pm_invest_event";

	/**
	 * 自增主键
	 */
	public void setEventId(java.lang.Integer eventId) {
		set("event_id", eventId);
	}

	/**
	 * 自增主键
	 */
	public java.lang.Integer getEventId() {
		return get("event_id");
	}

	/**
	 * 企业名称
	 */
	public void setEntCnName(java.lang.String entCnName) {
		set("ent_cn_name", entCnName);
	}

	/**
	 * 企业名称
	 */
	public java.lang.String getEntCnName() {
		return get("ent_cn_name");
	}

	/**
	 * 投资类型
	 */
	public void setInvestType(java.lang.String investType) {
		set("invest_type", investType);
	}

	/**
	 * 投资类型
	 */
	public java.lang.String getInvestType() {
		return get("invest_type");
	}

	/**
	 * 事件标题
	 */
	public void setEventTitle(java.lang.String eventTitle) {
		set("event_title", eventTitle);
	}

	/**
	 * 事件标题
	 */
	public java.lang.String getEventTitle() {
		return get("event_title");
	}

	/**
	 * 管理人
	 */
	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	/**
	 * 管理人
	 */
	public java.lang.String getUserName() {
		return get("user_name");
	}

	/**
	 * 发生日期
	 */
	public void setHappenDate(java.lang.String happenDate) {
		set("happen_date", happenDate);
	}

	/**
	 * 发生日期
	 */
	public java.lang.String getHappenDate() {
		return get("happen_date");
	}

}
