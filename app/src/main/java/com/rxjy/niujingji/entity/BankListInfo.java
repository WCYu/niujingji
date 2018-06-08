package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/8/23.
 */

public class BankListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<BankInfo> Body;

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

    public List<BankInfo> getBody() {
        return Body;
    }

    public void setBody(List<BankInfo> Body) {
        this.Body = Body;
    }

    public static class BankInfo {

        private int id;
        private String bank_name;
        private String bank_image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_image() {
            return bank_image;
        }

        public void setBank_image(String bank_image) {
            this.bank_image = bank_image;
        }
    }
}
