package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/8/9.
 */

public class CounselorInfo {

    private int StatusCode;
    private String StatusMsg;
    private Counselor Body;

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

    public Counselor getBody() {
        return Body;
    }

    public void setBody(Counselor Body) {
        this.Body = Body;
    }

    public static class Counselor {

        private String CardNo;
        private String Name;
        private String Phone;
        private String WeChat;
        private String Email;
        private String HeadImg;

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getWeChat() {
            return WeChat;
        }

        public void setWeChat(String WeChat) {
            this.WeChat = WeChat;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getHeadImg() {
            return HeadImg;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }
    }
}
