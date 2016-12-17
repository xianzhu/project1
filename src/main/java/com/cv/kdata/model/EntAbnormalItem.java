package com.cv.kdata.model;

import java.io.Serializable;

public class EntAbnormalItem implements Serializable, Comparable<EntAbnormalItem>{

    /**
	 *
	 */
	@Override
	public int compareTo(EntAbnormalItem ent) {
		int i = ent.getInDate().compareTo(this.getInDate());
		return i;
	}
	private static final long serialVersionUID = 1L;

	private String uuid;

    private String inDate;

    private String outDate;

    private String dept;
    
    private String inReason;
    
    private String outReason;

    public String getInReason() {
		return inReason;
	}

	public void setInReason(String inReason) {
		this.inReason = inReason;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate == null ? null : inDate.trim();
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate == null ? null : outDate.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
}