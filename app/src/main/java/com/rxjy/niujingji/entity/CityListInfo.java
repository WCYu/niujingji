package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/6.
 */

public class CityListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<CityList> Body;

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

    public List<CityList> getBody() {
        return Body;
    }

    public void setBody(List<CityList> Body) {
        this.Body = Body;
    }

    public static class CityList {

        private int Id;
        private String Name;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
