package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/6 0006.
 */
public class LabelSongList extends Entity {

    /**
     * context :
     * id : f39c8ce34de15419014de1dd022c01ac
     * index_ : 1
     * name : 安静的离开
     * openUrl :
     * picPath : http://115.28.12.99/resource/musiclist/20160119/2b080e4f988d4d78a75a112398a96577.png
     * picPathSmall : http://115.28.12.99/resource/musiclist/20160119/2b080e4f988d4d78a75a112398a96577-s.png
     * title : 安静地离开
     */

    private List<ObjBean> obj;

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        private String context;
        private String id;
        private String index_;
        private String name;
        private String openUrl;
        private String picPath;
        private String picPathSmall;
        private String title;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIndex_() {
            return index_;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenUrl() {
            return openUrl;
        }

        public void setOpenUrl(String openUrl) {
            this.openUrl = openUrl;
        }

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getPicPathSmall() {
            return picPathSmall;
        }

        public void setPicPathSmall(String picPathSmall) {
            this.picPathSmall = picPathSmall;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
