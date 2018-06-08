package com.rxjy.niujingji.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qindd on 2017/6/13.
 */
public class UserInfo implements Serializable
{

    private int StatusCode;
    private String StatusMsg;

    private User Body;

    public int getStatusCode()
    {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode)
    {
        this.StatusCode = StatusCode;
    }

    public String getStatusMsg()
    {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg)
    {
        this.StatusMsg = StatusMsg;
    }

    public User getBody()
    {
        return Body;
    }

    public void setBody(User Body)
    {
        this.Body = Body;
    }

    public static class User
    {
        /**
         * image :
         * name : 人事经理公共
         * sex : 男
         * age : 0
         * birthday : 2015/8/11 15:29:36
         * phone :
         * email :
         * userId : 0
         */

        private BaseInfo baseinfo;
        /**
         * area : R6
         * departId : 34
         * department : 投资部
         * statusId : 3
         * statusName : 正式
         * duty : 人事主管
         * workNum : 01010504
         * entryTime : 2015/8/11 15:29:36
         * workYears : 22
         * level : 人事经理
         * authority : [{"power_id":18,"power_name":"基本信息","p_father":0,"power_state":0,"p_depth":0,"power_model":[]},{"power_id":19,"power_name":"人事资料","p_father":0,"power_state":0,"p_depth":0,"power_model":[]},{"power_id":21,"power_name":"通知","p_father":0,"power_state":0,"p_depth":0,"power_model":[{"power_id":29,"power_name":"试岗培训通知","p_father":21,"power_state":0,"p_depth":1,"power_model":[]},{"power_id":30,"power_name":"消息通知","p_father":21,"power_state":0,"p_depth":1,"power_model":[]}]},{"power_id":23,"power_name":"培训","p_father":0,"power_state":0,"p_depth":0,"power_model":[{"power_id":25,"power_name":"岗前培训","p_father":23,"power_state":0,"p_depth":1,"power_model":[]},{"power_id":26,"power_name":"专业培训","p_father":23,"power_state":0,"p_depth":1,"power_model":[]},{"power_id":27,"power_name":"专业答题","p_father":23,"power_state":0,"p_depth":1,"power_model":[]},{"power_id":28,"power_name":"培训须知","p_father":23,"power_state":0,"p_depth":1,"power_model":[]}]},{"power_id":24,"power_name":"业务权限","p_father":0,"power_state":0,"p_depth":0,"power_model":[]},{"power_id":33,"power_name":"入岗须知","p_father":0,"power_state":0,"p_depth":0,"power_model":[]},{"power_id":34,"power_name":"入职须知","p_father":0,"power_state":0,"p_depth":0,"power_model":[]}]
         */

        private PersonnelInfo personnelInfo;

        public BaseInfo getBaseinfo()
        {
            return baseinfo;
        }

        public void setBaseinfo(BaseInfo baseinfo)
        {
            this.baseinfo = baseinfo;
        }

        public PersonnelInfo getPersonnelInfo()
        {
            return personnelInfo;
        }

        public void setPersonnelInfo(PersonnelInfo personnelInfo)
        {
            this.personnelInfo = personnelInfo;
        }

        public static class BaseInfo
        {
            private String image;
            private String name;
            private String sex;
            private int age;
            private String birthday;
            private String phone;
            private String email;
            private String userId;
            private String nickName;
            private String bankCard;
            private String bankName;

            public String getBankBgImage() {
                return bankBgImage;
            }

            public void setBankBgImage(String bankBgImage) {
                this.bankBgImage = bankBgImage;
            }

            private String bankBgImage;

            public String getBankCard() {
                return bankCard;
            }

            public void setBankCard(String bankCard) {
                this.bankCard = bankCard;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankUserName() {
                return bankUserName;
            }

            public void setBankUserName(String bankUserName) {
                this.bankUserName = bankUserName;
            }

            private String bankUserName;

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getImage()
            {
                return image;
            }

            public void setImage(String image)
            {
                this.image = image;
            }

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }

            public String getSex()
            {
                return sex;
            }

            public void setSex(String sex)
            {
                this.sex = sex;
            }

            public int getAge()
            {
                return age;
            }

            public void setAge(int age)
            {
                this.age = age;
            }

            public String getBirthday()
            {
                return birthday;
            }

            public void setBirthday(String birthday)
            {
                this.birthday = birthday;
            }

            public String getPhone()
            {
                return phone;
            }

            public void setPhone(String phone)
            {
                this.phone = phone;
            }

            public String getEmail()
            {
                return email;
            }

            public void setEmail(String email)
            {
                this.email = email;
            }

            public String getUserId()
            {
                return userId;
            }

            public void setUserId(String userId)
            {
                this.userId = userId;
            }
        }

        public static class PersonnelInfo
        {
            private String area;
            private int departId;
            private String department;
            private int statusId;
            private String statusName;
            private String duty;
            private String workNum;
            private String entryTime;
            private String workYears;
            private String level;
            private int postId;
            private String postName;

            /**
             * power_id : 18

             * power_name : 基本信息
             * p_father : 0
             * power_state : 0
             * p_depth : 0
             * power_model : []
             */

            private List<Authority> authority;

            public String getArea()
            {
                return area;
            }

            public void setArea(String area)
            {
                this.area = area;
            }

            public int getDepartId()
            {
                return departId;
            }

            public void setDepartId(int departId)
            {
                this.departId = departId;
            }

            public String getDepartment()
            {
                return department;
            }

            public void setDepartment(String department)
            {
                this.department = department;
            }

            public int getStatusId()
            {
                return statusId;
            }

            public void setStatusId(int statusId)
            {
                this.statusId = statusId;
            }

            public String getStatusName()
            {
                return statusName;
            }

            public void setStatusName(String statusName)
            {
                this.statusName = statusName;
            }

            public String getDuty()
            {
                return duty;
            }

            public void setDuty(String duty)
            {
                this.duty = duty;
            }

            public String getWorkNum()
            {
                return workNum;
            }

            public void setWorkNum(String workNum)
            {
                this.workNum = workNum;
            }

            public String getEntryTime()
            {
                return entryTime;
            }

            public void setEntryTime(String entryTime)
            {
                this.entryTime = entryTime;
            }

            public String getWorkYears()
            {
                return workYears;
            }

            public void setWorkYears(String workYears)
            {
                this.workYears = workYears;
            }

            public String getLevel()
            {
                return level;
            }

            public void setLevel(String level)
            {
                this.level = level;
            }

            public List<Authority> getAuthority()
            {
                return authority;
            }

            public void setAuthority(List<Authority> authority)
            {
                this.authority = authority;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getPostName() {
                return postName;
            }

            public void setPostName(String postName) {
                this.postName = postName;
            }

            public static class Authority
            {
                private int power_id;
                private String power_name;
                private int p_father;
                private int power_state;
                private int p_depth;
                private List<?> power_model;

                public int getPower_id()
                {
                    return power_id;
                }

                public void setPower_id(int power_id)
                {
                    this.power_id = power_id;
                }

                public String getPower_name()
                {
                    return power_name;
                }

                public void setPower_name(String power_name)
                {
                    this.power_name = power_name;
                }

                public int getP_father()
                {
                    return p_father;
                }

                public void setP_father(int p_father)
                {
                    this.p_father = p_father;
                }

                public int getPower_state()
                {
                    return power_state;
                }

                public void setPower_state(int power_state)
                {
                    this.power_state = power_state;
                }

                public int getP_depth()
                {
                    return p_depth;
                }

                public void setP_depth(int p_depth)
                {
                    this.p_depth = p_depth;
                }

                public List<?> getPower_model()
                {
                    return power_model;
                }

                public void setPower_model(List<?> power_model)
                {
                    this.power_model = power_model;
                }
            }
        }
    }
}
