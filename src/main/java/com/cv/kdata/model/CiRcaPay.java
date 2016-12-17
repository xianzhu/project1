package com.cv.kdata.model;

import java.io.Serializable;

public class CiRcaPay implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String statDate;

    private Double monthValueUs;

    private Double cumValueUs;

    private Double monthValueCn;

    private Double cumValueCn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate == null ? null : statDate.trim();
    }

    public Double getMonthValueUs() {
        return monthValueUs;
    }

    public void setMonthValueUs(Double monthValueUs) {
        this.monthValueUs = monthValueUs;
    }

    public Double getCumValueUs() {
        return cumValueUs;
    }

    public void setCumValueUs(Double cumValueUs) {
        this.cumValueUs = cumValueUs;
    }

    public Double getMonthValueCn() {
        return monthValueCn;
    }

    public void setMonthValueCn(Double monthValueCn) {
        this.monthValueCn = monthValueCn;
    }

    public Double getCumValueCn() {
        return cumValueCn;
    }

    public void setCumValueCn(Double cumValueCn) {
        this.cumValueCn = cumValueCn;
    }
}