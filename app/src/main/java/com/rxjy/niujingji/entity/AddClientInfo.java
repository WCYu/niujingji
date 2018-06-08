package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/7/31.
 */

public class AddClientInfo {

    private int StatusCode;
    private String StatusMsg;
    private ClientListInfo.ClientInfo Body;

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

    public ClientListInfo.ClientInfo getBody() {
        return Body;
    }

    public void setBody(ClientListInfo.ClientInfo body) {
        Body = body;
    }
}
