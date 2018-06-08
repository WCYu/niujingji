package com.rxjy.niujingji.entity;

/**
 * Created by Administrator on 2018/5/28.
 */

public class CheckLoginBean {


    /**
     * StatusCode : -2
     * StatusMsg : 此用户尚未绑定或者绑定的不是此平台，无法登陆！
     * Body : null
     */

    private int StatusCode;
    private String StatusMsg;
    private Object Body;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public Object getBody() {
        return Body;
    }

    public void setBody(Object Body) {
        this.Body = Body;
    }
}
