package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by asus on 2018/6/11.
 */

public class AttributesBean {


    /**
     * StatusCode : 0
     * StatusMsg : 请求成功
     * Body : {"list":[{"id":1,"name":"局改","detail":[{"Aid":"4","Aname":"材料","Bid":"30","Bname":"地坪漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"31","Bname":"地毯","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"32","Bname":"地胶","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"33","Bname":"木地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"34","Bname":"防静电地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"45","Bname":"铝方通吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"47","Bname":"地砖","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"48","Bname":"乳胶漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"49","Bname":"隔墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"50","Bname":"吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"51","Bname":"下水问题","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"52","Bname":"顶面喷漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"53","Bname":"砌墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"54","Bname":"拆除","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"60","Bname":"刷墙铺地","AT_isDel":"0","AT_Type":"1"}]},{"id":2,"name":"专项","detail":[{"Aid":"4","Aname":"材料","Bid":"30","Bname":"地坪漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"31","Bname":"地毯","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"32","Bname":"地胶","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"33","Bname":"木地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"34","Bname":"防静电地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"45","Bname":"铝方通吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"47","Bname":"地砖","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"48","Bname":"乳胶漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"49","Bname":"隔墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"50","Bname":"吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"51","Bname":"下水问题","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"52","Bname":"顶面喷漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"53","Bname":"砌墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"54","Bname":"拆除","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"60","Bname":"刷墙铺地","AT_isDel":"0","AT_Type":"1"}]},{"id":3,"name":"特殊","detail":[{"Aid":"4","Aname":"材料","Bid":"30","Bname":"地坪漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"31","Bname":"地毯","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"32","Bname":"地胶","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"33","Bname":"木地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"34","Bname":"防静电地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"45","Bname":"铝方通吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"47","Bname":"地砖","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"48","Bname":"乳胶漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"49","Bname":"隔墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"50","Bname":"吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"51","Bname":"下水问题","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"52","Bname":"顶面喷漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"53","Bname":"砌墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"54","Bname":"拆除","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"60","Bname":"刷墙铺地","AT_isDel":"0","AT_Type":"1"}]}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 局改
             * detail : [{"Aid":"4","Aname":"材料","Bid":"30","Bname":"地坪漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"31","Bname":"地毯","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"32","Bname":"地胶","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"33","Bname":"木地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"34","Bname":"防静电地板","AT_isDel":"0","AT_Type":"1"},{"Aid":"4","Aname":"材料","Bid":"45","Bname":"铝方通吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"47","Bname":"地砖","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"48","Bname":"乳胶漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"49","Bname":"隔墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"50","Bname":"吊顶","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"51","Bname":"下水问题","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"52","Bname":"顶面喷漆","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"53","Bname":"砌墙","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"54","Bname":"拆除","AT_isDel":"0","AT_Type":"1"},{"Aid":"5","Aname":"施工","Bid":"60","Bname":"刷墙铺地","AT_isDel":"0","AT_Type":"1"}]
             */

            private int id;
            private String name;
            private List<DetailBean> detail;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class DetailBean {
                /**
                 * Aid : 4
                 * Aname : 材料
                 * Bid : 30
                 * Bname : 地坪漆
                 * AT_isDel : 0
                 * AT_Type : 1
                 */

                private String Aid;
                private String Aname;
                private String Bid;
                private String Bname;
                private String AT_isDel;
                private String AT_Type;

                public String getAid() {
                    return Aid;
                }

                public void setAid(String Aid) {
                    this.Aid = Aid;
                }

                public String getAname() {
                    return Aname;
                }

                public void setAname(String Aname) {
                    this.Aname = Aname;
                }

                public String getBid() {
                    return Bid;
                }

                public void setBid(String Bid) {
                    this.Bid = Bid;
                }

                public String getBname() {
                    return Bname;
                }

                public void setBname(String Bname) {
                    this.Bname = Bname;
                }

                public String getAT_isDel() {
                    return AT_isDel;
                }

                public void setAT_isDel(String AT_isDel) {
                    this.AT_isDel = AT_isDel;
                }

                public String getAT_Type() {
                    return AT_Type;
                }

                public void setAT_Type(String AT_Type) {
                    this.AT_Type = AT_Type;
                }
            }
        }
    }
}
