package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/7/20.
 */

public class IsHaveBankCardInfo {

    private int StatusCode;
    private String StatusMsg;
    private IsHaveBankCard Body;

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

    public IsHaveBankCard getBody() {
        return Body;
    }

    public void setBody(IsHaveBankCard Body) {
        this.Body = Body;
    }

    public static class IsHaveBankCard {

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
