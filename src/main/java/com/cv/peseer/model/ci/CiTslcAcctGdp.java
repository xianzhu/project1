package com.cv.peseer.model.ci;

public class CiTslcAcctGdp {
    private Integer id;

    private String statDate;

    private Double prop;

    private Double alter;

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

    public Double getProp() {
        return prop;
    }

    public void setProp(Double prop) {
        this.prop = prop;
    }

    public Double getAlter() {
        return alter;
    }

    public void setAlter(Double alter) {
        this.alter = alter;
    }
}