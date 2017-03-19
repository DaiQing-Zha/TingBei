package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Recommend {

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"context":"神之少女霸上校园少 男","id":"f39db5d34fd41edf014fee5785fc027f","musicListReleaseId":"f39db5d34fd41edf014fee56c135027e","openUrl":"","picPath":"http://www.nnshow.cn /resource/recommend/20150921 /4a616421d05740d3b10ffdabb1a18565.png","picPathSmall":"http: //www.nnshow.cn/resource/recommend/20150921 /4a616421d05740d3b10ffdabb1a18565-s.png","title":"神之少女霸上校园少男","type":1},{"context":"灯光下不为人知的交 易","id":"f39db5d34fd41edf014fee5120d6027d","musicListReleaseId":"f39db5d34fd41edf014fee4156f70279","openUrl":"","picPath":"http://www.nnshow.cn /resource/recommend/20150921 /f27e6a74bbe747b8a7c77ae6782fed16.png","picPathSmall":"http: //www.nnshow.cn/resource/recommend/20150921 /f27e6a74bbe747b8a7c77ae6782fed16-s.png","title":"都市迷魂债","type":1},{"context":"乱事中那美丽爱 情","id":"f39db5d34fd41edf014fee4c7bfe027b","musicListReleaseId":"f39db5d34fd41edf014fee441d2c027a","openUrl":"","picPath":"http://www.nnshow.cn /resource/recommend/20150921 /c4d434991c91437e9598c6ee15aa41d9.png","picPathSmall":"http: //www.nnshow.cn/resource/recommend/20150921 /c4d434991c91437e9598c6ee15aa41d9-s.png","title":"魔乱九天天君是我爱","type":1}]
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
         * context : 神之少女霸上校园少 男
         * id : f39db5d34fd41edf014fee5785fc027f
         * musicListReleaseId : f39db5d34fd41edf014fee56c135027e
         * openUrl :
         * picPath : http://www.nnshow.cn /resource/recommend/20150921 /4a616421d05740d3b10ffdabb1a18565.png
         * picPathSmall : http: //www.nnshow.cn/resource/recommend/20150921 /4a616421d05740d3b10ffdabb1a18565-s.png
         * title : 神之少女霸上校园少男
         * type : 1
         */

        private String context;
        private String id;
        private String musicListReleaseId;
        private String openUrl;
        private String picPath;
        private String picPathSmall;
        private String title;
        private int type;

        public void setContext(String context) {
            this.context = context;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setMusicListReleaseId(String musicListReleaseId) {
            this.musicListReleaseId = musicListReleaseId;
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

        public void setType(int type) {
            this.type = type;
        }

        public String getContext() {
            return context;
        }

        public String getId() {
            return id;
        }

        public String getMusicListReleaseId() {
            return musicListReleaseId;
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

        public int getType() {
            return type;
        }
    }
}
