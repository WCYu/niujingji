package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/7/27.
 */

public class TokenInfo {

    private int StatusCode;
    private String StatusMsg;
    private Token Body;

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

    public Token getBody() {
        return Body;
    }

    public void setBody(Token Body) {
        this.Body = Body;
    }

    public static class Token {

        private String cardNo;
        private String account;
        private String Token;

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
    }
}
