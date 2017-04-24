package com.jxnu.zha.tingbei.model;

import java.io.Serializable;

/**
 * Created by DaiQing.Zha on 2017/4/18.
 * email:13767191284@163.com
 * description:
 */
public class UserModel extends Entity {


    /**
     * obj : {"addTime":"2017-04-24 09:58:06","appId":"e7cd6f1b8546234697b07a6d0231c507","id":"0b0155c65b8e4beb015b9dae23a700b6","integral":2000,"loginId":"12345","loginPassword":"827ccb0eea8a706c4c34a16891f84e7b","money":0,"nickName":"12345","phone":"12345","rank":1,"sex":0}
     */

    private ObjBean obj;

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        /**
         * addTime : 2017-04-24 09:58:06
         * appId : e7cd6f1b8546234697b07a6d0231c507
         * id : 0b0155c65b8e4beb015b9dae23a700b6
         * integral : 2000
         * loginId : 12345
         * loginPassword : 827ccb0eea8a706c4c34a16891f84e7b
         * money : 0
         * nickName : 12345
         * phone : 12345
         * rank : 1
         * sex : 0
         */

        private String addTime;
        private String appId;
        private String id;
        private int integral;
        private String loginId;
        private String loginPassword;
        private int money;
        private String nickName;
        private String phone;
        private int rank;
        private int sex;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
