package com.rxjy.niujingji.entity;

/**
 * Created by hjh on 2017/12/18.
 */

public class StatusBean {

    private int StatusCode;
    private String StatusMsg;

    public StatusBean() {super();
    }

    @Override
    public String toString() {
        return "StatusBean{" +
                "StatusCode=" + StatusCode +
                ", StatusMsg='" + StatusMsg + '\'' +
                '}';
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        StatusMsg = statusMsg;
    }
}
