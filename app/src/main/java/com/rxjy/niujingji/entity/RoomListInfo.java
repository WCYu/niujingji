package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/8.
 */

public class RoomListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<RoomList> Body;

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

    public List<RoomList> getBody() {
        return Body;
    }

    public void setBody(List<RoomList> Body) {
        this.Body = Body;
    }

    public static class RoomList {

        private int RowId;
        private double SalePrice;
        private int ID;
        private String Types;
        private double HousingArea;
        private String Toward;
        private String Fit;
        private String UpdateTime;
        private int Day;
        private double DayRent;
        private String Thumbnail;

        public double getDayRent() {
            return DayRent;
        }

        public void setDayRent(double dayRent) {
            DayRent = dayRent;
        }

        public String getThumbnail() {
            return Thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            Thumbnail = thumbnail;
        }

        public int getRowId() {
            return RowId;
        }

        public void setRowId(int RowId) {
            this.RowId = RowId;
        }

        public double getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(double SalePrice) {
            this.SalePrice = SalePrice;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getTypes() {
            return Types;
        }

        public void setTypes(String Types) {
            this.Types = Types;
        }

        public double getHousingArea() {
            return HousingArea;
        }

        public void setHousingArea(double HousingArea) {
            this.HousingArea = HousingArea;
        }

        public String getToward() {
            return Toward;
        }

        public void setToward(String Toward) {
            this.Toward = Toward;
        }

        public String getFit() {
            return Fit;
        }

        public void setFit(String Fit) {
            this.Fit = Fit;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public int getDay() {
            return Day;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }
    }
}
