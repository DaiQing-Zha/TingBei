package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class BangList {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"context":"52345","id":"402881f54d74f0c8014d750215470001","index_":"1","musicListId":"402881f54d2d0a0d014d2d0d4eaa0000","name":"34343","openUrl":"2345325","picPath":"http://localhost:8080/resource/musiclist/20150521/432899adf7dc40cc97d4e6a82f2e8b3e.png","picPathSmall":"http://localhost:8080/resource/musiclist/20150521/432899adf7dc40cc97d4e6a82f2e8b3e-s.png","title":"234234"}]
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
         * context : 52345
         * id : 402881f54d74f0c8014d750215470001
         * index_ : 1
         * musicListId : 402881f54d2d0a0d014d2d0d4eaa0000
         * name : 34343
         * openUrl : 2345325
         * picPath : http://localhost:8080/resource/musiclist/20150521/432899adf7dc40cc97d4e6a82f2e8b3e.png
         * picPathSmall : http://localhost:8080/resource/musiclist/20150521/432899adf7dc40cc97d4e6a82f2e8b3e-s.png
         * title : 234234
         */

        private String context;
        private String id;
        private String index_;
        private String musicListId;
        private String name;
        private String openUrl;
        private String picPath;
        private String picPathSmall;
        private String title;

        public void setContext(String context) {
            this.context = context;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public void setMusicListId(String musicListId) {
            this.musicListId = musicListId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOpenUrl(String openUrl) {
            this.openUrl = openUrl;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public void setPicPathSmall(String picPathSmall) {
            this.picPathSmall = picPathSmall;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContext() {
            return context;
        }

        public String getId() {
            return id;
        }

        public String getIndex_() {
            return index_;
        }

        public String getMusicListId() {
            return musicListId;
        }

        public String getName() {
            return name;
        }

        public String getOpenUrl() {
            return openUrl;
        }

        public String getPicPath() {
            return picPath;
        }

        public String getPicPathSmall() {
            return picPathSmall;
        }

        public String getTitle() {
            return title;
        }
    }
}
