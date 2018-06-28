package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by 解亚鑫 on 2018/6/23.
 */

public class NewBankListInfo {

    /**
     * StatusCode : 0
     * StatusMsg : 请求成功
     * Body : {"table":[{"ID":1,"BankName":"中国银行"},{"ID":2,"BankName":"中国工商银行"},{"ID":3,"BankName":"中国建设银行"},{"ID":4,"BankName":"农业银行"},{"ID":5,"BankName":"招商银行"},{"ID":6,"BankName":"浦发银行"}],"count":6}
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
         * table : [{"ID":1,"BankName":"中国银行"},{"ID":2,"BankName":"中国工商银行"},{"ID":3,"BankName":"中国建设银行"},{"ID":4,"BankName":"农业银行"},{"ID":5,"BankName":"招商银行"},{"ID":6,"BankName":"浦发银行"}]
         * count : 6
         */

        private int count;
        private List<TableBean> table;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<TableBean> getTable() {
            return table;
        }

        public void setTable(List<TableBean> table) {
            this.table = table;
        }

        public static class TableBean {
            /**
             * ID : 1
             * BankName : 中国银行
             */

            private int ID;
            private String BankName;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getBankName() {
                return BankName;
            }

            public void setBankName(String BankName) {
                this.BankName = BankName;
            }
        }
    }
}
