package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/9.
 */

public class RoomDetailInfo {

    private int StatusCode;
    private String StatusMsg;
    private RoomDetail Body;

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

    public RoomDetail getBody() {
        return Body;
    }

    public void setBody(RoomDetail Body) {
        this.Body = Body;
    }

    public static class RoomDetail {

        private int ID;
        private String Types;
        private String AreaName;
        private String BusinessName;
        private String Floor;
        private String Fit;
        private String BuildType;
        private double DayRent;
        private int MonthRent;
        private double Longitude;
        private double Latitude;
        private int HousingArea;
        private String RentFreePeriod;
        private String DetailedAddress;
        private String Toward;
        private int HouseFee;
        private List<ImgInfo> ImgList;
        private double SalePrice;
        private double TotalSale;
        private int OnFloor;
        private int FloorNum;
        private String PropertyName;

        public String getPropertyName() {
            return PropertyName;
        }

        public void setPropertyName(String propertyName) {
            PropertyName = propertyName;
        }

        public double getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(double salePrice) {
            SalePrice = salePrice;
        }

        public double getTotalSale() {
            return TotalSale;
        }

        public void setTotalSale(double totalSale) {
            TotalSale = totalSale;
        }

        public int getOnFloor() {
            return OnFloor;
        }

        public void setOnFloor(int onFloor) {
            OnFloor = onFloor;
        }

        public int getFloorNum() {
            return FloorNum;
        }

        public void setFloorNum(int floorNum) {
            FloorNum = floorNum;
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

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String AreaName) {
            this.AreaName = AreaName;
        }

        public String getBusinessName() {
            return BusinessName;
        }

        public void setBusinessName(String BusinessName) {
            this.BusinessName = BusinessName;
        }

        public String getFloor() {
            return Floor;
        }

        public void setFloor(String Floor) {
            this.Floor = Floor;
        }

        public String getFit() {
            return Fit;
        }

        public void setFit(String Fit) {
            this.Fit = Fit;
        }

        public String getBuildType() {
            return BuildType;
        }

        public void setBuildType(String BuildType) {
            this.BuildType = BuildType;
        }

        public double getDayRent() {
            return DayRent;
        }

        public void setDayRent(double DayRent) {
            this.DayRent = DayRent;
        }

        public int getMonthRent() {
            return MonthRent;
        }

        public void setMonthRent(int MonthRent) {
            this.MonthRent = MonthRent;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public int getHousingArea() {
            return HousingArea;
        }

        public void setHousingArea(int HousingArea) {
            this.HousingArea = HousingArea;
        }

        public String getRentFreePeriod() {
            return RentFreePeriod;
        }

        public void setRentFreePeriod(String RentFreePeriod) {
            this.RentFreePeriod = RentFreePeriod;
        }

        public String getDetailedAddress() {
            return DetailedAddress;
        }

        public void setDetailedAddress(String DetailedAddress) {
            this.DetailedAddress = DetailedAddress;
        }

        public String getToward() {
            return Toward;
        }

        public void setToward(String Toward) {
            this.Toward = Toward;
        }

        public int getHouseFee() {
            return HouseFee;
        }

        public void setHouseFee(int HouseFee) {
            this.HouseFee = HouseFee;
        }

        public List<ImgInfo> getImgList() {
            return ImgList;
        }

        public void setImgList(List<ImgInfo> ImgList) {
            this.ImgList = ImgList;
        }

        public static class ImgInfo {
            /**
             * ID : 220
             * Thumbnail : http://image.niujingji.cn//FangYuan/2015/11/13/Thumbnail/20151113141224_9538.jpg
             * IsMain : true
             * Types : 1
             * ListingsID : 70
             */

            private int ID;
            private String Thumbnail;
            private boolean IsMain;
            private int Types;
            private int ListingsID;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getThumbnail() {
                return Thumbnail;
            }

            public void setThumbnail(String Thumbnail) {
                this.Thumbnail = Thumbnail;
            }

            public boolean isIsMain() {
                return IsMain;
            }

            public void setIsMain(boolean IsMain) {
                this.IsMain = IsMain;
            }

            public int getTypes() {
                return Types;
            }

            public void setTypes(int Types) {
                this.Types = Types;
            }

            public int getListingsID() {
                return ListingsID;
            }

            public void setListingsID(int ListingsID) {
                this.ListingsID = ListingsID;
            }
        }
    }
}
