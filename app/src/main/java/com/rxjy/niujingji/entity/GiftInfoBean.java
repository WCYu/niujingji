package com.rxjy.niujingji.entity;

import java.util.ArrayList;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftInfoBean {

    private int StatusCode;
    private String StatusMsg;
    private Body Body;

    public GiftInfoBean() {super();
    }

    @Override
    public String toString() {
        return "GiftInfoBean{" +
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

    public GiftInfoBean.Body getBody() {
        return Body;
    }

    public void setBody(GiftInfoBean.Body body) {
        Body = body;
    }

    public class Body{

        private int ID;
        private ArrayList<String> GiftName;
        private String Name;
        private String Phone;
        private String Address;

        public Body() {super();
        }

        @Override
        public String toString() {
            return "Body{" +
                    "ID=" + ID +
                    ", GiftName=" + GiftName +
                    ", Name='" + Name + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", Address='" + Address + '\'' +
                    '}';
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public ArrayList<String> getGiftName() {
            return GiftName;
        }

        public void setGiftName(ArrayList<String> giftName) {
            GiftName = giftName;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }
    }
}
