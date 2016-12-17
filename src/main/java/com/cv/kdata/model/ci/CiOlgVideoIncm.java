package com.cv.kdata.model.ci;

public class CiOlgVideoIncm {
    private Integer id;

    private String statDate;

    private Double olvQuarterValue;

    private Double olvUpQuarter;

    private Double olvRgQuarter;

    private Double olaQuarterValue;

    private Double olaUpQuarter;

    private Double olaRgQuarter;

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

    public Double getOlvQuarterValue() {
        return olvQuarterValue;
    }

    public void setOlvQuarterValue(Double olvQuarterValue) {
        this.olvQuarterValue = olvQuarterValue;
    }

    public Double getOlvUpQuarter() {
        return olvUpQuarter;
    }

    public void setOlvUpQuarter(Double olvUpQuarter) {
        this.olvUpQuarter = olvUpQuarter;
    }

    public Double getOlvRgQuarter() {
        return olvRgQuarter;
    }

    public void setOlvRgQuarter(Double olvRgQuarter) {
        this.olvRgQuarter = olvRgQuarter;
    }

    public Double getOlaQuarterValue() {
        return olaQuarterValue;
    }

    public void setOlaQuarterValue(Double olaQuarterValue) {
        this.olaQuarterValue = olaQuarterValue;
    }

    public Double getOlaUpQuarter() {
        return olaUpQuarter;
    }

    public void setOlaUpQuarter(Double olaUpQuarter) {
        this.olaUpQuarter = olaUpQuarter;
    }

    public Double getOlaRgQuarter() {
        return olaRgQuarter;
    }

    public void setOlaRgQuarter(Double olaRgQuarter) {
        this.olaRgQuarter = olaRgQuarter;
    }
}