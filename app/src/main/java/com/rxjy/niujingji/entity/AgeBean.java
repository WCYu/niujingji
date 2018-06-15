package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by hjh on 2017/12/1.
 */

public class AgeBean {


    /**
     * StatusCode : 0
     * StatusMsg : Success
     * Body : [{"ID":0,"MingCheng":"请选择"},{"ID":134,"MingCheng":"行政"},{"ID":135,"MingCheng":"副总"},{"ID":136,"MingCheng":"合同负责人"},{"ID":138,"MingCheng":"财务"},{"ID":139,"MingCheng":"合伙人"},{"ID":141,"MingCheng":"老板"},{"ID":178,"MingCheng":"老板助理"}]
     */

    private int StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

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

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * ID : 0
         * MingCheng : 请选择
         */

        private int ID;
        private String MingCheng;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getMingCheng() {
            return MingCheng;
        }

        public void setMingCheng(String MingCheng) {
            this.MingCheng = MingCheng;
        }
    }
}
