package com.rxjy.niujingji.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AAA on 2017/7/31.
 */

public class ClientListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<ClientInfo> Body;

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

    public List<ClientInfo> getBody() {
        return Body;
    }

    public void setBody(List<ClientInfo> Body) {
        this.Body = Body;
    }

    public static class ClientInfo implements Serializable {

        private String XingMing;
        private String DanHao;
        private String TianJiaShiJian;
        private int State;
        private String StateName;
        private String ShouJiHaoYi;
        private int KeHuBaseID;

        public int getKeHuBaseID() {
            return KeHuBaseID;
        }

        public void setKeHuBaseID(int keHuBaseID) {
            KeHuBaseID = keHuBaseID;
        }

        public String getShouJiHaoYi() {
            return ShouJiHaoYi;
        }

        public void setShouJiHaoYi(String shouJiHaoYi) {
            ShouJiHaoYi = shouJiHaoYi;
        }

        public String getXingMing() {
            return XingMing;
        }

        public void setXingMing(String XingMing) {
            this.XingMing = XingMing;
        }

        public String getDanHao() {
            return DanHao;
        }

        public void setDanHao(String DanHao) {
            this.DanHao = DanHao;
        }

        public String getTianJiaShiJian() {
            return TianJiaShiJian;
        }

        public void setTianJiaShiJian(String TianJiaShiJian) {
            this.TianJiaShiJian = TianJiaShiJian;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String StateName) {
            this.StateName = StateName;
        }
    }
}
