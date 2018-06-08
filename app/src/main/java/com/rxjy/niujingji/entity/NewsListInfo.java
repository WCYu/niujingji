package com.rxjy.niujingji.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class NewsListInfo implements Serializable {

    private int StatusCode;
    private String StatusMsg;
    private NewsList Body;

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

    public NewsList getBody() {
        return Body;
    }

    public void setBody(NewsList Body) {
        this.Body = Body;
    }

    public static class NewsList {
        private List<BannerInfo> topList;
        private List<NewsInfo> list;

        public List<BannerInfo> getTopList() {
            return topList;
        }

        public void setTopList(List<BannerInfo> topList) {
            this.topList = topList;
        }

        public List<NewsInfo> getList() {
            return list;
        }

        public void setList(List<NewsInfo> list) {
            this.list = list;
        }

        public static class BannerInfo {

            private String spare1;
            private int id;
            private String name;
            private int tid;
            private String author;
            private int did;
            private String cover;
            private int praise;
            private int view;
            private String releaseDate;

            public String getSpare1() {
                return spare1;
            }

            public void setSpare1(String spare1) {
                this.spare1 = spare1;
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

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }
        }

        public static class NewsInfo implements Serializable {

            private String spare1;
            private String name;
            private String author;
            private String cover;
            private String releaseDate;
            private int id;
            private int tid;
            private int did;
            private int praise;
            private int view;
            private int type;
            private int courseId;
            private List<String> contentPic;
            private String content;
            private String summary;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public List<String> getContentPic() {
                return contentPic;
            }

            public void setContentPic(List<String> contentPic) {
                this.contentPic = contentPic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getSpare1() {
                return spare1;
            }

            public void setSpare1(String spare1) {
                this.spare1 = spare1;
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

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }
        }
    }
}
