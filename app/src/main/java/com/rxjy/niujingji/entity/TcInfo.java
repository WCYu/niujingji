package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/7/31.
 */

public class TcInfo {

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
        private List<TC> PackageType;
        private List<ClientType> CusTomer;

        public List<TC> getPackageType() {
            return PackageType;
        }

        public void setPackageType(List<TC> PackageType) {
            this.PackageType = PackageType;
        }

        public List<ClientType> getCusTomer() {
            return CusTomer;
        }

        public void setCusTomer(List<ClientType> CusTomer) {
            this.CusTomer = CusTomer;
        }

        public static class TC {

            private int ID;
            private int PackageGrade;
            private String PackageName;
            private int MeasureMoney;
            private int Commission;
            private String CreateTime;
            private int Status;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getPackageGrade() {
                return PackageGrade;
            }

            public void setPackageGrade(int PackageGrade) {
                this.PackageGrade = PackageGrade;
            }

            public String getPackageName() {
                return PackageName;
            }

            public void setPackageName(String PackageName) {
                this.PackageName = PackageName;
            }

            public int getMeasureMoney() {
                return MeasureMoney;
            }

            public void setMeasureMoney(int MeasureMoney) {
                this.MeasureMoney = MeasureMoney;
            }

            public int getCommission() {
                return Commission;
            }

            public void setCommission(int Commission) {
                this.Commission = Commission;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }
        }

        public static class ClientType {

            private int ID;
            private String MingCheng;
            private String FuID;
            private List<ClientChildType> ziji;

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

            public String getFuID() {
                return FuID;
            }

            public void setFuID(String FuID) {
                this.FuID = FuID;
            }

            public List<ClientChildType> getZiji() {
                return ziji;
            }

            public void setZiji(List<ClientChildType> ziji) {
                this.ziji = ziji;
            }

            public static class ClientChildType {
                /**
                 * ID : 6
                 * MingCheng : 金融类
                 * FuID : 1
                 * ziji : null
                 */

                private int ID;
                private String MingCheng;
                private String FuID;

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

                public String getFuID() {
                    return FuID;
                }

                public void setFuID(String FuID) {
                    this.FuID = FuID;
                }
            }
        }
    }
}
