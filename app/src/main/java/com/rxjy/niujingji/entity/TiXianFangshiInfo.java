package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by 解亚鑫 on 2018/6/27.
 */

public class TiXianFangshiInfo {

    /**
     * StatusCode : 0
     * StatusMsg : 请求成功
     * Body : {"table":[{"XingMing":"欧束","ZhangHao":"6258856688472589","LeiXing":3,"LeiXingMingCheng":"银行卡","MingCheng":"招商银行","BankID":5,"BankImage":"https://api.niujingji.cn:8183/Photos/Bankimage/zhaoShang.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       "}],"count":1}
     */

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
        /**
         * table : [{"XingMing":"欧束","ZhangHao":"6258856688472589","LeiXing":3,"LeiXingMingCheng":"银行卡","MingCheng":"招商银行","BankID":5,"BankImage":"https://api.niujingji.cn:8183/Photos/Bankimage/zhaoShang.png                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       "}]
         * count : 1
         */

        private int count;
        private List<TableBean> table;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<TableBean> getTable() {
            return table;
        }

        public void setTable(List<TableBean> table) {
            this.table = table;
        }

        public static class TableBean {
            /**
             * XingMing : 欧束
             * ZhangHao : 6258856688472589
             * LeiXing : 3
             * LeiXingMingCheng : 银行卡
             * MingCheng : 招商银行
             * BankID : 5
             * BankImage : https://api.niujingji.cn:8183/Photos/Bankimage/zhaoShang.png
             */

            private String XingMing;
            private String ZhangHao;
            private int LeiXing;
            private String LeiXingMingCheng;
            private String MingCheng;
            private int BankID;
            private String BankImage;

            public String getXingMing() {
                return XingMing;
            }

            public void setXingMing(String XingMing) {
                this.XingMing = XingMing;
            }

            public String getZhangHao() {
                return ZhangHao;
            }

            public void setZhangHao(String ZhangHao) {
                this.ZhangHao = ZhangHao;
            }

            public int getLeiXing() {
                return LeiXing;
            }

            public void setLeiXing(int LeiXing) {
                this.LeiXing = LeiXing;
            }

            public String getLeiXingMingCheng() {
                return LeiXingMingCheng;
            }

            public void setLeiXingMingCheng(String LeiXingMingCheng) {
                this.LeiXingMingCheng = LeiXingMingCheng;
            }

            public String getMingCheng() {
                return MingCheng;
            }

            public void setMingCheng(String MingCheng) {
                this.MingCheng = MingCheng;
            }

            public int getBankID() {
                return BankID;
            }

            public void setBankID(int BankID) {
                this.BankID = BankID;
            }

            public String getBankImage() {
                return BankImage;
            }

            public void setBankImage(String BankImage) {
                this.BankImage = BankImage;
            }
        }
    }
}
