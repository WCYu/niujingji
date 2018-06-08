package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/8/9.
 */

public class ClientInfo {

    private int StatusCode;
    private String StatusMsg;
    private Client Body;

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

    public Client getBody() {
        return Body;
    }

    public void setBody(Client Body) {
        this.Body = Body;
    }

    public static class Client {

        private String XingMing;
        private String ShouJiHaoYi;
        private String WeiXin;
        private double MianJi;
        private int LeiXingYi;
        private int LeiXingEr;
        private String LiangFangDiZhi;
        private int KeHuBaseID;
        private String GongSiMingCheng;
        private int ZhuangTai;
        private String LeiXingYiName;
        private String LeiXingErName;

        public String getLeiXingYiName() {
            return LeiXingYiName;
        }

        public void setLeiXingYiName(String leiXingYiName) {
            LeiXingYiName = leiXingYiName;
        }

        public String getLeiXingErName() {
            return LeiXingErName;
        }

        public void setLeiXingErName(String leiXingErName) {
            LeiXingErName = leiXingErName;
        }

        public String getXingMing() {
            return XingMing;
        }

        public void setXingMing(String XingMing) {
            this.XingMing = XingMing;
        }

        public String getShouJiHaoYi() {
            return ShouJiHaoYi;
        }

        public void setShouJiHaoYi(String ShouJiHaoYi) {
            this.ShouJiHaoYi = ShouJiHaoYi;
        }

        public String getWeiXin() {
            return WeiXin;
        }

        public void setWeiXin(String WeiXin) {
            this.WeiXin = WeiXin;
        }

        public double getMianJi() {
            return MianJi;
        }

        public void setMianJi(double MianJi) {
            this.MianJi = MianJi;
        }

        public int getLeiXingYi() {
            return LeiXingYi;
        }

        public void setLeiXingYi(int LeiXingYi) {
            this.LeiXingYi = LeiXingYi;
        }

        public int getLeiXingEr() {
            return LeiXingEr;
        }

        public void setLeiXingEr(int LeiXingEr) {
            this.LeiXingEr = LeiXingEr;
        }

        public String getLiangFangDiZhi() {
            return LiangFangDiZhi;
        }

        public void setLiangFangDiZhi(String LiangFangDiZhi) {
            this.LiangFangDiZhi = LiangFangDiZhi;
        }

        public int getKeHuBaseID() {
            return KeHuBaseID;
        }

        public void setKeHuBaseID(int KeHuBaseID) {
            this.KeHuBaseID = KeHuBaseID;
        }

        public String getGongSiMingCheng() {
            return GongSiMingCheng;
        }

        public void setGongSiMingCheng(String GongSiMingCheng) {
            this.GongSiMingCheng = GongSiMingCheng;
        }

        public int getZhuangTai() {
            return ZhuangTai;
        }

        public void setZhuangTai(int ZhuangTai) {
            this.ZhuangTai = ZhuangTai;
        }
    }
}
