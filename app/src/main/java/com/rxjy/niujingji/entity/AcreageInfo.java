package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/9/6.
 */

public class AcreageInfo {

    private String value;
    private Integer min;
    private Integer max;

    public AcreageInfo() {
    }

    public AcreageInfo(String value, Integer min, Integer max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
