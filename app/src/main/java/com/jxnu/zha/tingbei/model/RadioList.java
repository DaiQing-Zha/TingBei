package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class RadioList extends Entity{

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"context":"","id":"f39c8ce34de15419014de1f5482a024c","index_":"8","name":"白尛宝","openUrl":"","picPath":"http://www.nnshow.cn/resource/musiclist/20150818/734e6980e8f54df985156e098d29c2be.png","picPathSmall":"http://www.nnshow.cn/resource/musiclist/20150818/734e6980e8f54df985156e098d29c2be-s.png","title":"主播翻唱曲目"},{"context":"","id":"f39c8ce34de15419014de2055e2f02b6","index_":"6","name":"8090电台","openUrl":"","picPath":"http://www.nnshow.cn/resource/musiclist/20150721/f081e728aafc4e3798843885cab6dd86.png","picPathSmall":"http://www.nnshow.cn/resource/musiclist/20150721/f081e728aafc4e3798843885cab6dd86-s.png","title":"8090青春话题"}]
     */

    private int radioListType;

    private List<ObjEntity> obj;

    public int getRadioListType() {
        return radioListType;
    }

    public void setRadioListType(int radioListType) {
        this.radioListType = radioListType;
    }

    public void setObj(List<ObjEntity> obj) {
        this.obj = obj;
    }

    public List<ObjEntity> getObj() {
        return obj;
    }

    public static class ObjEntity implements Serializable{
        /**
         * context :
         * id : f39c8ce34de15419014de1f5482a024c
         * index_ : 8
         * name : 白尛宝
         * openUrl :
         * picPath : http://www.nnshow.cn/resource/musiclist/20150818/734e6980e8f54df985156e098d29c2be.png
         * picPathSmall : http://www.nnshow.cn/resource/musiclist/20150818/734e6980e8f54df985156e098d29c2be-s.png
         * title : 主播翻唱曲目
         */

        private String context;
        private String id;
        private String index_;
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
