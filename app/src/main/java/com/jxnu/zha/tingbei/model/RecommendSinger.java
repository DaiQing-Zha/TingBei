package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class RecommendSinger {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"f39db5d34fd41edf014fda0c699e00f5","initialid":"46801990e9dd42d1a50968f85e5e0614","name":"乐吧读物","pic":"http://www.nnshow.cn/resource/singer/20150917/aff5fc2063a549cebe71ae9fac12a2aa.png","picSmall":"http://www.nnshow.cn/resource/singer/20150917/aff5fc2063a549cebe71ae9fac12a2aa-s.png"},{"id":"402881f54d1daeed014d1dafd1460001","initialid":"350","name":"韩红","pic":"http://www.nnshow.cn/resource/singer/20150612/677aad43cb8f4db7b5b2c2ded1752e71.png","picSmall":"http://www.nnshow.cn/resource/singer/20150612/677aad43cb8f4db7b5b2c2ded1752e71-s.png"}]
     */

    private int code;
    private String msg;
    private List<ObjEntity> obj;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObj(List<ObjEntity> obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<ObjEntity> getObj() {
        return obj;
    }

    public static class ObjEntity {
        /**
         * id : f39db5d34fd41edf014fda0c699e00f5
         * initialid : 46801990e9dd42d1a50968f85e5e0614
         * name : 乐吧读物
         * pic : http://www.nnshow.cn/resource/singer/20150917/aff5fc2063a549cebe71ae9fac12a2aa.png
         * picSmall : http://www.nnshow.cn/resource/singer/20150917/aff5fc2063a549cebe71ae9fac12a2aa-s.png
         */

        private String id;
        private String initialid;
        private String name;
        private String pic;
        private String picSmall;

        public void setId(String id) {
            this.id = id;
        }

        public void setInitialid(String initialid) {
            this.initialid = initialid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPicSmall(String picSmall) {
            this.picSmall = picSmall;
        }

        public String getId() {
            return id;
        }

        public String getInitialid() {
            return initialid;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }

        public String getPicSmall() {
            return picSmall;
        }
    }
}
