package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/6.
 */

public class TradingListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<TradingList> Body;

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

    public List<TradingList> getBody() {
        return Body;
    }

    public void setBody(List<TradingList> Body) {
        this.Body = Body;
    }

    public static class TradingList {

        private int ID;
        private int City;
        private int County;
        private String Name;

        public TradingList(){

        }

        public TradingList(String name){
            this.Name = name;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getCity() {
            return City;
        }

        public void setCity(int City) {
            this.City = City;
        }

        public int getCounty() {
            return County;
        }

        public void setCounty(int County) {
            this.County = County;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
