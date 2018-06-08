package com.rxjy.niujingji.entity;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftPickUpBean {

    private int StatusCode;
    private String StatusMsg;

    public GiftPickUpBean() {super();
    }

    @Override
    public String toString() {
        return "GiftPickUpBean{" +
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
