package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/6.
 */

public class BuildingListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<BuildingList> Body;

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

    public List<BuildingList> getBody() {
        return Body;
    }

    public void setBody(List<BuildingList> Body) {
        this.Body = Body;
    }

    public static class BuildingList {
        /**
         * RowId : 1
         * ID : 16153
         * City : 1
         * County : 2
         * Name : 瑞安大厦
         * CountyName : 朝阳
         * Bussiness : CBD
         * Address : 东三环南路102号
         * MainImage : http://image.niujingji.cn//LouPanKu/2016/06/24/20160624180201_5295.jpg
         * StandardArea : 2000.0
         * BuildType : 写字楼
         * AvgDailyRent : 5.00
         */

        private int RowId;
        private int ID;
        private int City;
        private int County;
        private String Name;
        private String CountyName;
        private String Bussiness;
        private String Address;
        private String MainImage;
        private double StandardArea;
        private String BuildType;
        private String AvgDailyRent;

        public int getRowId() {
            return RowId;
        }

        public void setRowId(int RowId) {
            this.RowId = RowId;
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

        public String getCountyName() {
            return CountyName;
        }

        public void setCountyName(String CountyName) {
            this.CountyName = CountyName;
        }

        public String getBussiness() {
            return Bussiness;
        }

        public void setBussiness(String Bussiness) {
            this.Bussiness = Bussiness;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getMainImage() {
            return MainImage;
        }

        public void setMainImage(String MainImage) {
            this.MainImage = MainImage;
        }

        public double getStandardArea() {
            return StandardArea;
        }

        public void setStandardArea(double StandardArea) {
            this.StandardArea = StandardArea;
        }

        public String getBuildType() {
            return BuildType;
        }

        public void setBuildType(String BuildType) {
            this.BuildType = BuildType;
        }

        public String getAvgDailyRent() {
            return AvgDailyRent;
        }

        public void setAvgDailyRent(String AvgDailyRent) {
            this.AvgDailyRent = AvgDailyRent;
        }
    }
}
