package com.rxjy.niujingji.entity;

/**
 * Created by Administrator on 2018/5/28.
 */

public class LoginInfo {


    /**
     * StatusCode : 0
     * StatusMsg : 登陆成功！
     * Body : {"cardNo":"s00004141","account":"13988888888","Token":"347DFEA4FBE09A6EF2F9E0FC92223303","appId":"93ef0f14-0ecc-4599-9ede-ddf394b4b1ff"}
     */

    private int StatusCode;
    private String StatusMsg;
    private BodyBean Body;

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

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * cardNo : s00004141
         * account : 13988888888
         * Token : 347DFEA4FBE09A6EF2F9E0FC92223303
         * appId : 93ef0f14-0ecc-4599-9ede-ddf394b4b1ff
         */

        private String cardNo;
        private String account;
        private String Token;
        private String appId;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
