package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by asus on 2018/6/8.
 */

public class CustomerBean {


    /**
     * StatusCode : 0
     * StatusMsg : 请求成功
     * Body : [{"ZuQiKaiShi":"1900-01-01","ZuQiJieShu":"1900-01-01","YuJiDingFang":null,"LouPanQiZuo":"","LouPanLouCeng":"","HourNumber":"","QiYeRuZhu":0,"BuildName":"","BuildID":null,"GongSiChengLiDate":null,"QuYuID":0,"ShangQuanID":0,"City":"R6","TianJiaShiJian":"2018-03-07","KeHuBaseID":678209,"DanHao":"11-199915","tb_diqu":"11","JianYiSheJiShi":null,"LaiYuan":null,"XingMing2":null,"XingMing3":null,"ZhuangXiuXuQiu":null,"GongQi":null,"DanXing":null,"XingMing":"赵老板","ZhuangTai":0,"DanHao1":"11-199915","YouXiang":null,"WeiXin":"","GongSiMingCheng":"大龙猫","BanGongDiDian":"","LeiXingYi":0,"LeiXingEr":0,"LeiXingSan":0,"LeiXingErName":null,"LeiXingSanName":null,"ShouJiHaoYi":"15468699585","ShouJiHaoEr":"","ShouJiHaoSan":"","FangYuan":0,"MianJi":154,"YuJiLiangFang":"1900-01-01 00:00:00","LiangFangDiZhi":"","ZhuangXiuYuSuan":0,"JiaoFangShiJian":null,"ChengJiaoLeiXing":0,"ZhuZhongDianID":0,"MianZuQi":0,"FangYuanLeiXing":0,"XiangMuShuXing":0,"YuJiZhuangXiu":0,"YuJiZhuangXiuShiJian":"1900-01-01 00:00:00","BeiZhu":"","LouPanLouCeng1":null,"NianLingID":0,"QQ":null,"ZhuangXiuXuQiu1":null,"ZhaoBiao":null,"XingBie":0,"NianLingID1":0,"ShenFenID":0,"QiYeXingZhiID":0,"QiYeGuiMoID":0,"GuiShuXingZhiID":0,"HangYeLeiXingYiID":0,"HangYeLeiXingErID":0,"FangYuanZhuangTai":"","ShenFenName":null,"ChengJiao":"","ShuXing":"","Platform":0,"Channel":0,"KeyWord":"","FromURL":"","LandingURL":"","IsJoin":0,"Remarks":"在跟踪","LouPanName":null,"QiZuo":null,"LouCe":null,"MenPai":null,"IsMobilePC":null,"IsPending":null,"Ttype":null,"PendingTime":null,"KeHuXingMing":"陈伟峰","JiaoFangShiJian1":null,"ShangWuTiFangTime":null,"ShangWuZhongShenTime":null,"ZhuAnZhongShenTime":null,"ZhuAnSheJiShiXingMing":null,"JLName":"王海红","JLName1":"王海红","JianYiSheJiShiXingMing":null,"ZiTanZhouQi":null,"QianDanZhouQi":null,"JieGuoTime":null,"ShiGongHeTongJinE":null,"ZhuangXiuYuSuan1":0,"KFReason":"","XZJLReason":"","SJSReason":"","LeiXingMingCheng":"其他","LeiXingErMingCheng":"","YouXiang2":"","WeiXin2":"","ZhuCeZiJin":"","ZhuCeDiZhi":"","XingBie2":0,"ZhuZhongDianID2":0,"NianLingID2":0,"ShenFenID2":0,"ZhiMingQiYe":0,"KeHuShenFen":0,"ZhunKeHuShiJian":null,"BuMenShuXing":null,"YeWuYuanID":13663,"XuQiuLeiXing":0,"PartCompany":"","DecorateStyle":"","OpeningTime":null,"IsChain":0,"SingleRoomPrice":0,"BrandName":"","MatEnddowment":0,"LabelId":0,"ServiceGroups":0,"FenGongSiID":11,"FenGongSiMing":"R6"}]
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
         * ZuQiKaiShi : 1900-01-01
         * ZuQiJieShu : 1900-01-01
         * YuJiDingFang : null
         * LouPanQiZuo :
         * LouPanLouCeng :
         * HourNumber :
         * QiYeRuZhu : 0
         * BuildName :
         * BuildID : null
         * GongSiChengLiDate : null
         * QuYuID : 0
         * ShangQuanID : 0
         * City : R6
         * TianJiaShiJian : 2018-03-07
         * KeHuBaseID : 678209
         * DanHao : 11-199915
         * tb_diqu : 11
         * JianYiSheJiShi : null
         * LaiYuan : null
         * XingMing2 : null
         * XingMing3 : null
         * ZhuangXiuXuQiu : null
         * GongQi : null
         * DanXing : null
         * XingMing : 赵老板
         * ZhuangTai : 0
         * DanHao1 : 11-199915
         * YouXiang : null
         * WeiXin :
         * GongSiMingCheng : 大龙猫
         * BanGongDiDian :
         * LeiXingYi : 0
         * LeiXingEr : 0
         * LeiXingSan : 0
         * LeiXingErName : null
         * LeiXingSanName : null
         * ShouJiHaoYi : 15468699585
         * ShouJiHaoEr :
         * ShouJiHaoSan :
         * FangYuan : 0
         * MianJi : 154.0
         * YuJiLiangFang : 1900-01-01 00:00:00
         * LiangFangDiZhi :
         * ZhuangXiuYuSuan : 0.0
         * JiaoFangShiJian : null
         * ChengJiaoLeiXing : 0
         * ZhuZhongDianID : 0
         * MianZuQi : 0
         * FangYuanLeiXing : 0
         * XiangMuShuXing : 0
         * YuJiZhuangXiu : 0
         * YuJiZhuangXiuShiJian : 1900-01-01 00:00:00
         * BeiZhu :
         * LouPanLouCeng1 : null
         * NianLingID : 0
         * QQ : null
         * ZhuangXiuXuQiu1 : null
         * ZhaoBiao : null
         * XingBie : 0
         * NianLingID1 : 0
         * ShenFenID : 0
         * QiYeXingZhiID : 0
         * QiYeGuiMoID : 0
         * GuiShuXingZhiID : 0
         * HangYeLeiXingYiID : 0
         * HangYeLeiXingErID : 0
         * FangYuanZhuangTai :
         * ShenFenName : null
         * ChengJiao :
         * ShuXing :
         * Platform : 0
         * Channel : 0
         * KeyWord :
         * FromURL :
         * LandingURL :
         * IsJoin : 0
         * Remarks : 在跟踪
         * LouPanName : null
         * QiZuo : null
         * LouCe : null
         * MenPai : null
         * IsMobilePC : null
         * IsPending : null
         * Ttype : null
         * PendingTime : null
         * KeHuXingMing : 陈伟峰
         * JiaoFangShiJian1 : null
         * ShangWuTiFangTime : null
         * ShangWuZhongShenTime : null
         * ZhuAnZhongShenTime : null
         * ZhuAnSheJiShiXingMing : null
         * JLName : 王海红
         * JLName1 : 王海红
         * JianYiSheJiShiXingMing : null
         * ZiTanZhouQi : null
         * QianDanZhouQi : null
         * JieGuoTime : null
         * ShiGongHeTongJinE : null
         * ZhuangXiuYuSuan1 : 0.0
         * KFReason :
         * XZJLReason :
         * SJSReason :
         * LeiXingMingCheng : 其他
         * LeiXingErMingCheng :
         * YouXiang2 :
         * WeiXin2 :
         * ZhuCeZiJin :
         * ZhuCeDiZhi :
         * XingBie2 : 0
         * ZhuZhongDianID2 : 0
         * NianLingID2 : 0
         * ShenFenID2 : 0
         * ZhiMingQiYe : 0
         * KeHuShenFen : 0
         * ZhunKeHuShiJian : null
         * BuMenShuXing : null
         * YeWuYuanID : 13663
         * XuQiuLeiXing : 0
         * PartCompany :
         * DecorateStyle :
         * OpeningTime : null
         * IsChain : 0
         * SingleRoomPrice : 0.0
         * BrandName :
         * MatEnddowment : 0
         * LabelId : 0
         * ServiceGroups : 0
         * FenGongSiID : 11
         * FenGongSiMing : R6
         * ZuJinDanJia
         */

        private String ZuQiKaiShi;
        private String ZuQiJieShu;
        private String YuJiDingFang;
        private String LouPanQiZuo;
        private String LouPanLouCeng;
        private String HourNumber;
        private int QiYeRuZhu;
        private String BuildName;
        private String BuildID;
        private String GongSiChengLiDate;
        private int QuYuID;
        private int ShangQuanID;
        private String City;
        private String TianJiaShiJian;
        private int KeHuBaseID;
        private String DanHao;
        private String tb_diqu;
        private String JianYiSheJiShi;
        private String LaiYuan;
        private String XingMing2;
        private String XingMing3;
        private int ZhuangXiuXuQiu;
        private String GongQi;
        private String DanXing;
        private String XingMing;
        private int ZhuangTai;
        private String DanHao1;
        private String YouXiang;
        private String WeiXin;
        private String GongSiMingCheng;
        private String BanGongDiDian;
        private int LeiXingYi;
        private int LeiXingEr;
        private int LeiXingSan;
        private String LeiXingErName;
        private String LeiXingSanName;
        private String ShouJiHaoYi;
        private String ShouJiHaoEr;
        private String ShouJiHaoSan;
        private int FangYuan;
        private double MianJi;
        private String YuJiLiangFang;
        private String LiangFangDiZhi;
        private double ZhuangXiuYuSuan;
        private String JiaoFangShiJian;
        private int ChengJiaoLeiXing;
        private int ZhuZhongDianID;
        private int MianZuQi;
        private int FangYuanLeiXing;
        private int XiangMuShuXing;
        private int YuJiZhuangXiu;
        private String YuJiZhuangXiuShiJian;
        private String BeiZhu;
        private String LouPanLouCeng1;
        private int NianLingID;
        private String QQ;
        private String ZhuangXiuXuQiu1;
        private int ZhaoBiao;
        private int XingBie;
        private int NianLingID1;
        private int ShenFenID;
        private int QiYeXingZhiID;
        private int QiYeGuiMoID;
        private int GuiShuXingZhiID;
        private int HangYeLeiXingYiID;
        private int HangYeLeiXingErID;
        private String FangYuanZhuangTai;
        private String ShenFenName;
        private String ChengJiao;
        private String ShuXing;
        private int Platform;
        private int Channel;
        private String KeyWord;
        private String FromURL;
        private String LandingURL;
        private int IsJoin;
        private String Remarks;
        private String LouPanName;
        private String QiZuo;
        private String LouCe;
        private String MenPai;
        private String IsMobilePC;
        private String IsPending;
        private String Ttype;
        private String PendingTime;
        private String KeHuXingMing;
        private String JiaoFangShiJian1;
        private String ShangWuTiFangTime;
        private String ShangWuZhongShenTime;
        private String ZhuAnZhongShenTime;
        private String ZhuAnSheJiShiXingMing;
        private String JLName;
        private String JLName1;
        private String JianYiSheJiShiXingMing;
        private String ZiTanZhouQi;
        private String QianDanZhouQi;
        private String JieGuoTime;
        private String ShiGongHeTongJinE;
        private double ZhuangXiuYuSuan1;
        private String KFReason;
        private String XZJLReason;
        private String SJSReason;
        private String LeiXingMingCheng;
        private String LeiXingErMingCheng;
        private String YouXiang2;
        private String WeiXin2;
        private String ZhuCeZiJin;
        private String ZhuCeDiZhi;
        private int XingBie2;
        private int ZhuZhongDianID2;
        private int NianLingID2;
        private int ShenFenID2;
        private int ZhiMingQiYe;
        private int KeHuShenFen;
        private String ZhunKeHuShiJian;
        private String BuMenShuXing;
        private int YeWuYuanID;
        private int XuQiuLeiXing;
        private String PartCompany;
        private String DecorateStyle;
        private String OpeningTime;
        private int IsChain;
        private double SingleRoomPrice;
        private String BrandName;
        private int MatEnddowment;
        private int LabelId;
        private int ServiceGroups;
        private int FenGongSiID;
        private String FenGongSiMing;

        private int ZuJinDanJia;

        public int getZuJinDanJia() {
            return ZuJinDanJia;
        }

        public void setZuJinDanJia(int zuJinDanJia) {
            ZuJinDanJia = zuJinDanJia;
        }

        public String getZuQiKaiShi() {
            return ZuQiKaiShi;
        }

        public void setZuQiKaiShi(String ZuQiKaiShi) {
            this.ZuQiKaiShi = ZuQiKaiShi;
        }

        public String getZuQiJieShu() {
            return ZuQiJieShu;
        }

        public void setZuQiJieShu(String ZuQiJieShu) {
            this.ZuQiJieShu = ZuQiJieShu;
        }

        public String getYuJiDingFang() {
            return YuJiDingFang;
        }

        public void setYuJiDingFang(String YuJiDingFang) {
            this.YuJiDingFang = YuJiDingFang;
        }

        public String getLouPanQiZuo() {
            return LouPanQiZuo;
        }

        public void setLouPanQiZuo(String LouPanQiZuo) {
            this.LouPanQiZuo = LouPanQiZuo;
        }

        public String getLouPanLouCeng() {
            return LouPanLouCeng;
        }

        public void setLouPanLouCeng(String LouPanLouCeng) {
            this.LouPanLouCeng = LouPanLouCeng;
        }

        public String getHourNumber() {
            return HourNumber;
        }

        public void setHourNumber(String HourNumber) {
            this.HourNumber = HourNumber;
        }

        public int getQiYeRuZhu() {
            return QiYeRuZhu;
        }

        public void setQiYeRuZhu(int QiYeRuZhu) {
            this.QiYeRuZhu = QiYeRuZhu;
        }

        public String getBuildName() {
            return BuildName;
        }

        public void setBuildName(String BuildName) {
            this.BuildName = BuildName;
        }

        public String getBuildID() {
            return BuildID;
        }

        public void setBuildID(String BuildID) {
            this.BuildID = BuildID;
        }

        public String getGongSiChengLiDate() {
            return GongSiChengLiDate;
        }

        public void setGongSiChengLiDate(String GongSiChengLiDate) {
            this.GongSiChengLiDate = GongSiChengLiDate;
        }

        public int getQuYuID() {
            return QuYuID;
        }

        public void setQuYuID(int QuYuID) {
            this.QuYuID = QuYuID;
        }

        public int getShangQuanID() {
            return ShangQuanID;
        }

        public void setShangQuanID(int ShangQuanID) {
            this.ShangQuanID = ShangQuanID;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getTianJiaShiJian() {
            return TianJiaShiJian;
        }

        public void setTianJiaShiJian(String TianJiaShiJian) {
            this.TianJiaShiJian = TianJiaShiJian;
        }

        public int getKeHuBaseID() {
            return KeHuBaseID;
        }

        public void setKeHuBaseID(int KeHuBaseID) {
            this.KeHuBaseID = KeHuBaseID;
        }

        public String getDanHao() {
            return DanHao;
        }

        public void setDanHao(String DanHao) {
            this.DanHao = DanHao;
        }

        public String getTb_diqu() {
            return tb_diqu;
        }

        public void setTb_diqu(String tb_diqu) {
            this.tb_diqu = tb_diqu;
        }

        public String getJianYiSheJiShi() {
            return JianYiSheJiShi;
        }

        public void setJianYiSheJiShi(String JianYiSheJiShi) {
            this.JianYiSheJiShi = JianYiSheJiShi;
        }

        public String getLaiYuan() {
            return LaiYuan;
        }

        public void setLaiYuan(String LaiYuan) {
            this.LaiYuan = LaiYuan;
        }

        public String getXingMing2() {
            return XingMing2;
        }

        public void setXingMing2(String XingMing2) {
            this.XingMing2 = XingMing2;
        }

        public String getXingMing3() {
            return XingMing3;
        }

        public void setXingMing3(String XingMing3) {
            this.XingMing3 = XingMing3;
        }

        public int getZhuangXiuXuQiu() {
            return ZhuangXiuXuQiu;
        }

        public void setZhuangXiuXuQiu(int ZhuangXiuXuQiu) {
            this.ZhuangXiuXuQiu = ZhuangXiuXuQiu;
        }

        public String getGongQi() {
            return GongQi;
        }

        public void setGongQi(String GongQi) {
            this.GongQi = GongQi;
        }

        public String getDanXing() {
            return DanXing;
        }

        public void setDanXing(String DanXing) {
            this.DanXing = DanXing;
        }

        public String getXingMing() {
            return XingMing;
        }

        public void setXingMing(String XingMing) {
            this.XingMing = XingMing;
        }

        public int getZhuangTai() {
            return ZhuangTai;
        }

        public void setZhuangTai(int ZhuangTai) {
            this.ZhuangTai = ZhuangTai;
        }

        public String getDanHao1() {
            return DanHao1;
        }

        public void setDanHao1(String DanHao1) {
            this.DanHao1 = DanHao1;
        }

        public String getYouXiang() {
            return YouXiang;
        }

        public void setYouXiang(String YouXiang) {
            this.YouXiang = YouXiang;
        }

        public String getWeiXin() {
            return WeiXin;
        }

        public void setWeiXin(String WeiXin) {
            this.WeiXin = WeiXin;
        }

        public String getGongSiMingCheng() {
            return GongSiMingCheng;
        }

        public void setGongSiMingCheng(String GongSiMingCheng) {
            this.GongSiMingCheng = GongSiMingCheng;
        }

        public String getBanGongDiDian() {
            return BanGongDiDian;
        }

        public void setBanGongDiDian(String BanGongDiDian) {
            this.BanGongDiDian = BanGongDiDian;
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

        public int getLeiXingSan() {
            return LeiXingSan;
        }

        public void setLeiXingSan(int LeiXingSan) {
            this.LeiXingSan = LeiXingSan;
        }

        public String getLeiXingErName() {
            return LeiXingErName;
        }

        public void setLeiXingErName(String LeiXingErName) {
            this.LeiXingErName = LeiXingErName;
        }

        public String getLeiXingSanName() {
            return LeiXingSanName;
        }

        public void setLeiXingSanName(String LeiXingSanName) {
            this.LeiXingSanName = LeiXingSanName;
        }

        public String getShouJiHaoYi() {
            return ShouJiHaoYi;
        }

        public void setShouJiHaoYi(String ShouJiHaoYi) {
            this.ShouJiHaoYi = ShouJiHaoYi;
        }

        public String getShouJiHaoEr() {
            return ShouJiHaoEr;
        }

        public void setShouJiHaoEr(String ShouJiHaoEr) {
            this.ShouJiHaoEr = ShouJiHaoEr;
        }

        public String getShouJiHaoSan() {
            return ShouJiHaoSan;
        }

        public void setShouJiHaoSan(String ShouJiHaoSan) {
            this.ShouJiHaoSan = ShouJiHaoSan;
        }

        public int getFangYuan() {
            return FangYuan;
        }

        public void setFangYuan(int FangYuan) {
            this.FangYuan = FangYuan;
        }

        public double getMianJi() {
            return MianJi;
        }

        public void setMianJi(double MianJi) {
            this.MianJi = MianJi;
        }

        public String getYuJiLiangFang() {
            return YuJiLiangFang;
        }

        public void setYuJiLiangFang(String YuJiLiangFang) {
            this.YuJiLiangFang = YuJiLiangFang;
        }

        public String getLiangFangDiZhi() {
            return LiangFangDiZhi;
        }

        public void setLiangFangDiZhi(String LiangFangDiZhi) {
            this.LiangFangDiZhi = LiangFangDiZhi;
        }

        public double getZhuangXiuYuSuan() {
            return ZhuangXiuYuSuan;
        }

        public void setZhuangXiuYuSuan(double ZhuangXiuYuSuan) {
            this.ZhuangXiuYuSuan = ZhuangXiuYuSuan;
        }

        public String getJiaoFangShiJian() {
            return JiaoFangShiJian;
        }

        public void setJiaoFangShiJian(String JiaoFangShiJian) {
            this.JiaoFangShiJian = JiaoFangShiJian;
        }

        public int getChengJiaoLeiXing() {
            return ChengJiaoLeiXing;
        }

        public void setChengJiaoLeiXing(int ChengJiaoLeiXing) {
            this.ChengJiaoLeiXing = ChengJiaoLeiXing;
        }

        public int getZhuZhongDianID() {
            return ZhuZhongDianID;
        }

        public void setZhuZhongDianID(int ZhuZhongDianID) {
            this.ZhuZhongDianID = ZhuZhongDianID;
        }

        public int getMianZuQi() {
            return MianZuQi;
        }

        public void setMianZuQi(int MianZuQi) {
            this.MianZuQi = MianZuQi;
        }

        public int getFangYuanLeiXing() {
            return FangYuanLeiXing;
        }

        public void setFangYuanLeiXing(int FangYuanLeiXing) {
            this.FangYuanLeiXing = FangYuanLeiXing;
        }

        public int getXiangMuShuXing() {
            return XiangMuShuXing;
        }

        public void setXiangMuShuXing(int XiangMuShuXing) {
            this.XiangMuShuXing = XiangMuShuXing;
        }

        public int getYuJiZhuangXiu() {
            return YuJiZhuangXiu;
        }

        public void setYuJiZhuangXiu(int YuJiZhuangXiu) {
            this.YuJiZhuangXiu = YuJiZhuangXiu;
        }

        public String getYuJiZhuangXiuShiJian() {
            return YuJiZhuangXiuShiJian;
        }

        public void setYuJiZhuangXiuShiJian(String YuJiZhuangXiuShiJian) {
            this.YuJiZhuangXiuShiJian = YuJiZhuangXiuShiJian;
        }

        public String getBeiZhu() {
            return BeiZhu;
        }

        public void setBeiZhu(String BeiZhu) {
            this.BeiZhu = BeiZhu;
        }

        public String getLouPanLouCeng1() {
            return LouPanLouCeng1;
        }

        public void setLouPanLouCeng1(String LouPanLouCeng1) {
            this.LouPanLouCeng1 = LouPanLouCeng1;
        }

        public int getNianLingID() {
            return NianLingID;
        }

        public void setNianLingID(int NianLingID) {
            this.NianLingID = NianLingID;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getZhuangXiuXuQiu1() {
            return ZhuangXiuXuQiu1;
        }

        public void setZhuangXiuXuQiu1(String ZhuangXiuXuQiu1) {
            this.ZhuangXiuXuQiu1 = ZhuangXiuXuQiu1;
        }

        public int getZhaoBiao() {
            return ZhaoBiao;
        }

        public void setZhaoBiao(int ZhaoBiao) {
            this.ZhaoBiao = ZhaoBiao;
        }

        public int getXingBie() {
            return XingBie;
        }

        public void setXingBie(int XingBie) {
            this.XingBie = XingBie;
        }

        public int getNianLingID1() {
            return NianLingID1;
        }

        public void setNianLingID1(int NianLingID1) {
            this.NianLingID1 = NianLingID1;
        }

        public int getShenFenID() {
            return ShenFenID;
        }

        public void setShenFenID(int ShenFenID) {
            this.ShenFenID = ShenFenID;
        }

        public int getQiYeXingZhiID() {
            return QiYeXingZhiID;
        }

        public void setQiYeXingZhiID(int QiYeXingZhiID) {
            this.QiYeXingZhiID = QiYeXingZhiID;
        }

        public int getQiYeGuiMoID() {
            return QiYeGuiMoID;
        }

        public void setQiYeGuiMoID(int QiYeGuiMoID) {
            this.QiYeGuiMoID = QiYeGuiMoID;
        }

        public int getGuiShuXingZhiID() {
            return GuiShuXingZhiID;
        }

        public void setGuiShuXingZhiID(int GuiShuXingZhiID) {
            this.GuiShuXingZhiID = GuiShuXingZhiID;
        }

        public int getHangYeLeiXingYiID() {
            return HangYeLeiXingYiID;
        }

        public void setHangYeLeiXingYiID(int HangYeLeiXingYiID) {
            this.HangYeLeiXingYiID = HangYeLeiXingYiID;
        }

        public int getHangYeLeiXingErID() {
            return HangYeLeiXingErID;
        }

        public void setHangYeLeiXingErID(int HangYeLeiXingErID) {
            this.HangYeLeiXingErID = HangYeLeiXingErID;
        }

        public String getFangYuanZhuangTai() {
            return FangYuanZhuangTai;
        }

        public void setFangYuanZhuangTai(String FangYuanZhuangTai) {
            this.FangYuanZhuangTai = FangYuanZhuangTai;
        }

        public String getShenFenName() {
            return ShenFenName;
        }

        public void setShenFenName(String ShenFenName) {
            this.ShenFenName = ShenFenName;
        }

        public String getChengJiao() {
            return ChengJiao;
        }

        public void setChengJiao(String ChengJiao) {
            this.ChengJiao = ChengJiao;
        }

        public String getShuXing() {
            return ShuXing;
        }

        public void setShuXing(String ShuXing) {
            this.ShuXing = ShuXing;
        }

        public int getPlatform() {
            return Platform;
        }

        public void setPlatform(int Platform) {
            this.Platform = Platform;
        }

        public int getChannel() {
            return Channel;
        }

        public void setChannel(int Channel) {
            this.Channel = Channel;
        }

        public String getKeyWord() {
            return KeyWord;
        }

        public void setKeyWord(String KeyWord) {
            this.KeyWord = KeyWord;
        }

        public String getFromURL() {
            return FromURL;
        }

        public void setFromURL(String FromURL) {
            this.FromURL = FromURL;
        }

        public String getLandingURL() {
            return LandingURL;
        }

        public void setLandingURL(String LandingURL) {
            this.LandingURL = LandingURL;
        }

        public int getIsJoin() {
            return IsJoin;
        }

        public void setIsJoin(int IsJoin) {
            this.IsJoin = IsJoin;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getLouPanName() {
            return LouPanName;
        }

        public void setLouPanName(String LouPanName) {
            this.LouPanName = LouPanName;
        }

        public String getQiZuo() {
            return QiZuo;
        }

        public void setQiZuo(String QiZuo) {
            this.QiZuo = QiZuo;
        }

        public String getLouCe() {
            return LouCe;
        }

        public void setLouCe(String LouCe) {
            this.LouCe = LouCe;
        }

        public String getMenPai() {
            return MenPai;
        }

        public void setMenPai(String MenPai) {
            this.MenPai = MenPai;
        }

        public String getIsMobilePC() {
            return IsMobilePC;
        }

        public void setIsMobilePC(String IsMobilePC) {
            this.IsMobilePC = IsMobilePC;
        }

        public String getIsPending() {
            return IsPending;
        }

        public void setIsPending(String IsPending) {
            this.IsPending = IsPending;
        }

        public String getTtype() {
            return Ttype;
        }

        public void setTtype(String Ttype) {
            this.Ttype = Ttype;
        }

        public String getPendingTime() {
            return PendingTime;
        }

        public void setPendingTime(String PendingTime) {
            this.PendingTime = PendingTime;
        }

        public String getKeHuXingMing() {
            return KeHuXingMing;
        }

        public void setKeHuXingMing(String KeHuXingMing) {
            this.KeHuXingMing = KeHuXingMing;
        }

        public String getJiaoFangShiJian1() {
            return JiaoFangShiJian1;
        }

        public void setJiaoFangShiJian1(String JiaoFangShiJian1) {
            this.JiaoFangShiJian1 = JiaoFangShiJian1;
        }

        public String getShangWuTiFangTime() {
            return ShangWuTiFangTime;
        }

        public void setShangWuTiFangTime(String ShangWuTiFangTime) {
            this.ShangWuTiFangTime = ShangWuTiFangTime;
        }

        public String getShangWuZhongShenTime() {
            return ShangWuZhongShenTime;
        }

        public void setShangWuZhongShenTime(String ShangWuZhongShenTime) {
            this.ShangWuZhongShenTime = ShangWuZhongShenTime;
        }

        public String getZhuAnZhongShenTime() {
            return ZhuAnZhongShenTime;
        }

        public void setZhuAnZhongShenTime(String ZhuAnZhongShenTime) {
            this.ZhuAnZhongShenTime = ZhuAnZhongShenTime;
        }

        public String getZhuAnSheJiShiXingMing() {
            return ZhuAnSheJiShiXingMing;
        }

        public void setZhuAnSheJiShiXingMing(String ZhuAnSheJiShiXingMing) {
            this.ZhuAnSheJiShiXingMing = ZhuAnSheJiShiXingMing;
        }

        public String getJLName() {
            return JLName;
        }

        public void setJLName(String JLName) {
            this.JLName = JLName;
        }

        public String getJLName1() {
            return JLName1;
        }

        public void setJLName1(String JLName1) {
            this.JLName1 = JLName1;
        }

        public String getJianYiSheJiShiXingMing() {
            return JianYiSheJiShiXingMing;
        }

        public void setJianYiSheJiShiXingMing(String JianYiSheJiShiXingMing) {
            this.JianYiSheJiShiXingMing = JianYiSheJiShiXingMing;
        }

        public String getZiTanZhouQi() {
            return ZiTanZhouQi;
        }

        public void setZiTanZhouQi(String ZiTanZhouQi) {
            this.ZiTanZhouQi = ZiTanZhouQi;
        }

        public String getQianDanZhouQi() {
            return QianDanZhouQi;
        }

        public void setQianDanZhouQi(String QianDanZhouQi) {
            this.QianDanZhouQi = QianDanZhouQi;
        }

        public String getJieGuoTime() {
            return JieGuoTime;
        }

        public void setJieGuoTime(String JieGuoTime) {
            this.JieGuoTime = JieGuoTime;
        }

        public String getShiGongHeTongJinE() {
            return ShiGongHeTongJinE;
        }

        public void setShiGongHeTongJinE(String ShiGongHeTongJinE) {
            this.ShiGongHeTongJinE = ShiGongHeTongJinE;
        }

        public double getZhuangXiuYuSuan1() {
            return ZhuangXiuYuSuan1;
        }

        public void setZhuangXiuYuSuan1(double ZhuangXiuYuSuan1) {
            this.ZhuangXiuYuSuan1 = ZhuangXiuYuSuan1;
        }

        public String getKFReason() {
            return KFReason;
        }

        public void setKFReason(String KFReason) {
            this.KFReason = KFReason;
        }

        public String getXZJLReason() {
            return XZJLReason;
        }

        public void setXZJLReason(String XZJLReason) {
            this.XZJLReason = XZJLReason;
        }

        public String getSJSReason() {
            return SJSReason;
        }

        public void setSJSReason(String SJSReason) {
            this.SJSReason = SJSReason;
        }

        public String getLeiXingMingCheng() {
            return LeiXingMingCheng;
        }

        public void setLeiXingMingCheng(String LeiXingMingCheng) {
            this.LeiXingMingCheng = LeiXingMingCheng;
        }

        public String getLeiXingErMingCheng() {
            return LeiXingErMingCheng;
        }

        public void setLeiXingErMingCheng(String LeiXingErMingCheng) {
            this.LeiXingErMingCheng = LeiXingErMingCheng;
        }

        public String getYouXiang2() {
            return YouXiang2;
        }

        public void setYouXiang2(String YouXiang2) {
            this.YouXiang2 = YouXiang2;
        }

        public String getWeiXin2() {
            return WeiXin2;
        }

        public void setWeiXin2(String WeiXin2) {
            this.WeiXin2 = WeiXin2;
        }

        public String getZhuCeZiJin() {
            return ZhuCeZiJin;
        }

        public void setZhuCeZiJin(String ZhuCeZiJin) {
            this.ZhuCeZiJin = ZhuCeZiJin;
        }

        public String getZhuCeDiZhi() {
            return ZhuCeDiZhi;
        }

        public void setZhuCeDiZhi(String ZhuCeDiZhi) {
            this.ZhuCeDiZhi = ZhuCeDiZhi;
        }

        public int getXingBie2() {
            return XingBie2;
        }

        public void setXingBie2(int XingBie2) {
            this.XingBie2 = XingBie2;
        }

        public int getZhuZhongDianID2() {
            return ZhuZhongDianID2;
        }

        public void setZhuZhongDianID2(int ZhuZhongDianID2) {
            this.ZhuZhongDianID2 = ZhuZhongDianID2;
        }

        public int getNianLingID2() {
            return NianLingID2;
        }

        public void setNianLingID2(int NianLingID2) {
            this.NianLingID2 = NianLingID2;
        }

        public int getShenFenID2() {
            return ShenFenID2;
        }

        public void setShenFenID2(int ShenFenID2) {
            this.ShenFenID2 = ShenFenID2;
        }

        public int getZhiMingQiYe() {
            return ZhiMingQiYe;
        }

        public void setZhiMingQiYe(int ZhiMingQiYe) {
            this.ZhiMingQiYe = ZhiMingQiYe;
        }

        public int getKeHuShenFen() {
            return KeHuShenFen;
        }

        public void setKeHuShenFen(int KeHuShenFen) {
            this.KeHuShenFen = KeHuShenFen;
        }

        public String getZhunKeHuShiJian() {
            return ZhunKeHuShiJian;
        }

        public void setZhunKeHuShiJian(String ZhunKeHuShiJian) {
            this.ZhunKeHuShiJian = ZhunKeHuShiJian;
        }

        public String getBuMenShuXing() {
            return BuMenShuXing;
        }

        public void setBuMenShuXing(String BuMenShuXing) {
            this.BuMenShuXing = BuMenShuXing;
        }

        public int getYeWuYuanID() {
            return YeWuYuanID;
        }

        public void setYeWuYuanID(int YeWuYuanID) {
            this.YeWuYuanID = YeWuYuanID;
        }

        public int getXuQiuLeiXing() {
            return XuQiuLeiXing;
        }

        public void setXuQiuLeiXing(int XuQiuLeiXing) {
            this.XuQiuLeiXing = XuQiuLeiXing;
        }

        public String getPartCompany() {
            return PartCompany;
        }

        public void setPartCompany(String PartCompany) {
            this.PartCompany = PartCompany;
        }

        public String getDecorateStyle() {
            return DecorateStyle;
        }

        public void setDecorateStyle(String DecorateStyle) {
            this.DecorateStyle = DecorateStyle;
        }

        public String getOpeningTime() {
            return OpeningTime;
        }

        public void setOpeningTime(String OpeningTime) {
            this.OpeningTime = OpeningTime;
        }

        public int getIsChain() {
            return IsChain;
        }

        public void setIsChain(int IsChain) {
            this.IsChain = IsChain;
        }

        public double getSingleRoomPrice() {
            return SingleRoomPrice;
        }

        public void setSingleRoomPrice(double SingleRoomPrice) {
            this.SingleRoomPrice = SingleRoomPrice;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public int getMatEnddowment() {
            return MatEnddowment;
        }

        public void setMatEnddowment(int MatEnddowment) {
            this.MatEnddowment = MatEnddowment;
        }

        public int getLabelId() {
            return LabelId;
        }

        public void setLabelId(int LabelId) {
            this.LabelId = LabelId;
        }

        public int getServiceGroups() {
            return ServiceGroups;
        }

        public void setServiceGroups(int ServiceGroups) {
            this.ServiceGroups = ServiceGroups;
        }

        public int getFenGongSiID() {
            return FenGongSiID;
        }

        public void setFenGongSiID(int FenGongSiID) {
            this.FenGongSiID = FenGongSiID;
        }

        public String getFenGongSiMing() {
            return FenGongSiMing;
        }

        public void setFenGongSiMing(String FenGongSiMing) {
            this.FenGongSiMing = FenGongSiMing;
        }
    }
}
