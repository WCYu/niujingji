package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/8.
 */

public class BuildingDetailInfo {

    private int StatusCode;
    private String StatusMsg;
    private BuildingDetail Body;

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

    public BuildingDetail getBody() {
        return Body;
    }

    public void setBody(BuildingDetail Body) {
        this.Body = Body;
    }

    public static class BuildingDetail {

        private int ID;
        private String Name;
        private int City;
        private int County;
        private String CountyName;
        private String Bussiness;
        private String Address;
        private String AllFloor;
        private String PropertyFee;
        private String PropertyName;
        private String Description;
        private String BuildType;
        private double Longitude;
        private double Latitude;
        private String AvgDailyRent;
        private int GroundParking;
        private int UndergroundParking;
        private List<Integer> Num;
        private List<ImgList> ImgList;
        private double StandardArea;

        public double getStandardArea() {
            return StandardArea;
        }

        public void setStandardArea(double standardArea) {
            StandardArea = standardArea;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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

        public String getAllFloor() {
            return AllFloor;
        }

        public void setAllFloor(String AllFloor) {
            this.AllFloor = AllFloor;
        }

        public String getPropertyFee() {
            return PropertyFee;
        }

        public void setPropertyFee(String PropertyFee) {
            this.PropertyFee = PropertyFee;
        }

        public String getPropertyName() {
            return PropertyName;
        }

        public void setPropertyName(String PropertyName) {
            this.PropertyName = PropertyName;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getBuildType() {
            return BuildType;
        }

        public void setBuildType(String BuildType) {
            this.BuildType = BuildType;
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

        public String getAvgDailyRent() {
            return AvgDailyRent;
        }

        public void setAvgDailyRent(String AvgDailyRent) {
            this.AvgDailyRent = AvgDailyRent;
        }

        public int getGroundParking() {
            return GroundParking;
        }

        public void setGroundParking(int GroundParking) {
            this.GroundParking = GroundParking;
        }

        public int getUndergroundParking() {
            return UndergroundParking;
        }

        public void setUndergroundParking(int UndergroundParking) {
            this.UndergroundParking = UndergroundParking;
        }

        public List<Integer> getNum() {
            return Num;
        }

        public void setNum(List<Integer> Num) {
            this.Num = Num;
        }

        public List<ImgList> getImgList() {
            return ImgList;
        }

        public void setImgList(List<ImgList> ImgList) {
            this.ImgList = ImgList;
        }

        public static class ImgList {

            private int ID;
            private String Thumbnail;
            private boolean IsMain;
            private int Types;
            private int BuildID;

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

            public int getBuildID() {
                return BuildID;
            }

            public void setBuildID(int BuildID) {
                this.BuildID = BuildID;
            }
        }
    }
}
