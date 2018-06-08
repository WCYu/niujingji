package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/7.
 */

public class HotWordListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<HotWordList> Body;

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

    public List<HotWordList> getBody() {
        return Body;
    }

    public void setBody(List<HotWordList> Body) {
        this.Body = Body;
    }

    public static class HotWordList {

        private int Total;
        private String BusinessName;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public String getBusinessName() {
            return BusinessName;
        }

        public void setBusinessName(String BusinessName) {
            this.BusinessName = BusinessName;
        }
    }
}
