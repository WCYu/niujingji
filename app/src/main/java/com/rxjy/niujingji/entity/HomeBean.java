package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by 阿禹 on 2018/6/8.
 */

public class HomeBean {

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
        private java.util.List<TopListBean> topList;
        private java.util.List<ListBean> List;

        public List<TopListBean> getTopList() {
            return topList;
        }

        public void setTopList(List<TopListBean> topList) {
            this.topList = topList;
        }

        public List<ListBean> getList() {
            return List;
        }

        public void setList(List<ListBean> List) {
            this.List = List;
        }

        public static class TopListBean {

            private String cover;
            private int id;
            private String name;
            private String content;
            private String releaseDate;
            private String contentPic;
            private int NewsID;
            private String playFlag;
            private String refreshFlag;
            private int type;
            private String summary;
            private int view;
            private String spare1;
            private String anthor;
            private int did;
            private int praise;
            private String articleType;
            private int tid;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public String getContentPic() {
                return contentPic;
            }

            public void setContentPic(String contentPic) {
                this.contentPic = contentPic;
            }

            public int getNewsID() {
                return NewsID;
            }

            public void setNewsID(int NewsID) {
                this.NewsID = NewsID;
            }

            public String getPlayFlag() {
                return playFlag;
            }

            public void setPlayFlag(String playFlag) {
                this.playFlag = playFlag;
            }

            public String getRefreshFlag() {
                return refreshFlag;
            }

            public void setRefreshFlag(String refreshFlag) {
                this.refreshFlag = refreshFlag;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public String getSpare1() {
                return spare1;
            }

            public void setSpare1(String spare1) {
                this.spare1 = spare1;
            }

            public String getAnthor() {
                return anthor;
            }

            public void setAnthor(String anthor) {
                this.anthor = anthor;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public String getArticleType() {
                return articleType;
            }

            public void setArticleType(String articleType) {
                this.articleType = articleType;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }
        }

        public static class ListBean {

            private String cover;
            private int id;
            private String name;
            private String content;
            private String releaseDate;
            private String contentPic;
            private int NewsID;
            private String playFlag;
            private String refreshFlag;
            private int type;
            private String summary;
            private int view;
            private String spare1;
            private String anthor;
            private int did;
            private int praise;
            private String articleType;
            private int tid;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public String getContentPic() {
                return contentPic;
            }

            public void setContentPic(String contentPic) {
                this.contentPic = contentPic;
            }

            public int getNewsID() {
                return NewsID;
            }

            public void setNewsID(int NewsID) {
                this.NewsID = NewsID;
            }

            public String getPlayFlag() {
                return playFlag;
            }

            public void setPlayFlag(String playFlag) {
                this.playFlag = playFlag;
            }

            public String getRefreshFlag() {
                return refreshFlag;
            }

            public void setRefreshFlag(String refreshFlag) {
                this.refreshFlag = refreshFlag;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public String getSpare1() {
                return spare1;
            }

            public void setSpare1(String spare1) {
                this.spare1 = spare1;
            }

            public String getAnthor() {
                return anthor;
            }

            public void setAnthor(String anthor) {
                this.anthor = anthor;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public String getArticleType() {
                return articleType;
            }

            public void setArticleType(String articleType) {
                this.articleType = articleType;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }
        }
    }
}
