package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/8/15.
 */

public class MsgInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<Msg> Body;

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

    public List<Msg> getBody() {
        return Body;
    }

    public void setBody(List<Msg> Body) {
        this.Body = Body;
    }

    public static class Msg {

        private int ID;
        private String Name;
        private int Type;
        private String Content;
        private int Status;
        private String ImgUrl;
        private String CreateTime;
        private String CreatedByName;
        private int IsRead;
        private String NewsUrl;
        private String Card;

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

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreatedByName() {
            return CreatedByName;
        }

        public void setCreatedByName(String CreatedByName) {
            this.CreatedByName = CreatedByName;
        }

        public int getIsRead() {
            return IsRead;
        }

        public void setIsRead(int IsRead) {
            this.IsRead = IsRead;
        }

        public String getNewsUrl() {
            return NewsUrl;
        }

        public void setNewsUrl(String NewsUrl) {
            this.NewsUrl = NewsUrl;
        }

        public String getCard() {
            return Card;
        }

        public void setCard(String Card) {
            this.Card = Card;
        }
    }
}
