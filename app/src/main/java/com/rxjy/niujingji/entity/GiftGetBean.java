package com.rxjy.niujingji.entity;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftGetBean {

    private int StatusCode;
    private String StatusMsg;
    private Body Body;

    public GiftGetBean() {super();
    }

    @Override
    public String toString() {
        return "GiftGetBean{" +
                "StatusCode=" + StatusCode +
                ", StatusMsg='" + StatusMsg + '\'' +
                ", Body=" + Body +
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

    public GiftGetBean.Body getBody() {
        return Body;
    }

    public void setBody(GiftGetBean.Body body) {
        Body = body;
    }

    public class Body{
        private int GiftStatus;

        public Body() {super();
        }

        @Override
        public String toString() {
            return "Body{" +
                    "GiftStatus=" + GiftStatus +
                    '}';
        }

        public int getGiftStatus() {
            return GiftStatus;
        }

        public void setGiftStatus(int giftStatus) {
            GiftStatus = giftStatus;
        }
    }

}
