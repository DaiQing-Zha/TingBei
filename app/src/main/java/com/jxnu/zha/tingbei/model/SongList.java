package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class SongList {

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"context":"中国之星第一季第五 期","id":"f39c8ce351f12de20151f1deffaf001d","index_":"2","name":"中国之星第一季第 五期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/c17c5f88ea8f4c31bc619bf2da135e41.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /c17c5f88ea8f4c31bc619bf2da135e41-s.png","title":"中国之星第一季第五期"},{"context":"中国之星第一季第三 期","id":"f39c8ce351f12de20151f1dcde260018","index_":"5","name":"中国之星第一季第 二期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/3b436fc741324729bd88f7ffe400e88d.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /3b436fc741324729bd88f7ffe400e88d-s.png","title":"中国之星第一季第二期"},{"context":"中国之星第一季第三 期","id":"f39c8ce351f12de20151f1ddc49e001a","index_":"4","name":"中国之星第一季第 三期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/268639b52aca467e8f9540e938f39328.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /268639b52aca467e8f9540e938f39328-s.png","title":"中国之星第一季第三期"},{"context":"中国之星第一季第四 期","id":"f39c8ce351f12de20151f1de7b6b001c","index_":"3","name":"中国之星第一季第 四期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/68c2cf0f548d4355a0c81d182b080df2.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /68c2cf0f548d4355a0c81d182b080df2-s.png","title":"中国之星第一季第四期"},{"context":"中国之星第一季第六 期","id":"f39c8ce351f12de20151f1df6b1b001f","index_":"1","name":"中国之星第一季第 六期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/ea82408b56e94161969848c23dbe5c4b.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /ea82408b56e94161969848c23dbe5c4b-s.png","title":"中国之星第一季第六期"},{"context":"中国之星第一季第一 期","id":"f39c8ce351f12de20151f1dbf8f60016","index_":"6","name":"中国之星第一季第 一期","openUrl":"","picPath":"http://115.28.12.99/resource/musiclist /20151230/b7c44a462078488cbf0609df6d29a05d.png","picPathSmall":"http: //115.28.12.99/resource/musiclist/20151230 /b7c44a462078488cbf0609df6d29a05d-s.png","title":"中国之星第一季第一期"}]
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
         * context : 中国之星第一季第五 期
         * id : f39c8ce351f12de20151f1deffaf001d
         * index_ : 2
         * name : 中国之星第一季第 五期
         * openUrl :
         * picPath : http://115.28.12.99/resource/musiclist /20151230/c17c5f88ea8f4c31bc619bf2da135e41.png
         * picPathSmall : http: //115.28.12.99/resource/musiclist/20151230 /c17c5f88ea8f4c31bc619bf2da135e41-s.png
         * title : 中国之星第一季第五期
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
