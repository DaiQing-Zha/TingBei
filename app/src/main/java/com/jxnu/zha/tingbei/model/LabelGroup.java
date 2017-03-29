package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class LabelGroup {

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"color":"f1d0ff","id":"f39c8ce34eae53ae014eae631aba0018","index_":"1","name":"氛围","picPath":"http://115.28.12.99/resource/label/20151215/2e149a2c87a348bcbbd578fc0b084457.png"},{"color":"f94f46","id":"40287e814e4ce5c1014e4cf39a680007","index_":"2","name":"情绪","picPath":"http://115.28.12.99/resource/label/20151215/4f1522a6f7544c2eb6916ded6f0d3bbd.png"},{"color":"98d05a","id":"f39c8ce34ed7940a014ed799a2b60003","index_":"3","name":"主题","picPath":"http://115.28.12.99/resource/label/20151215/2a79e5ca789d4ef09eb5fe32a604f2b2.png"},{"color":"9abbfd","id":"f39c8ce34ed7940a014ed79971260002","index_":"4","name":"流派","picPath":"http://115.28.12.99/resource/label/20151215/34c7d4b7475f47218f6a94227f918a37.png"}]
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
         * color : f1d0ff
         * id : f39c8ce34eae53ae014eae631aba0018
         * index_ : 1
         * name : 氛围
         * picPath : http://115.28.12.99/resource/label/20151215/2e149a2c87a348bcbbd578fc0b084457.png
         */

        private String color;
        private String id;
        private String index_;
        private String name;
        private String picPath;

        public void setColor(String color) {
            this.color = color;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getColor() {
            return color;
        }

        public String getId() {
            return id;
        }

        public String getIndex_() {
            return index_;
        }

        public String getName() {
            return name;
        }

        public String getPicPath() {
            return picPath;
        }
    }
}
