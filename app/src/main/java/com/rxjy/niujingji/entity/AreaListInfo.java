package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/6.
 */

public class AreaListInfo {

    private int StatusCode;
    private String StatusMsg;
    private List<AreaList> Body;

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

    public List<AreaList> getBody() {
        return Body;
    }

    public void setBody(List<AreaList> Body) {
        this.Body = Body;
    }

    public static class AreaList {

        private int Id;
        private String Name;
        private int ParentId;
        private int isSelector;

        public AreaList() {

        }

        public AreaList(String name, int isSelector) {
            this.Name = name;
            this.isSelector = isSelector;
        }

        public int getIsSelector() {
            return isSelector;
        }

        public void setIsSelector(int isSelector) {
            this.isSelector = isSelector;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }
    }
}
