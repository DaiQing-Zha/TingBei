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
     * obj : [{"context":"全能唱作人杨朗朗专访","id":"f39c8ce3549dabef015529fbe0dc0fd8","musicListReleaseId":"f39c8ce3549dabef01552895b27a0f6b","openUrl":"","picPath":"http://115.28.12.99/resource/recommend/20160607/639e44f1d46d4335ad2610d3784d6305.png","picPathSmall":"http://115.28.12.99/resource/recommend/20160607/639e44f1d46d4335ad2610d3784d6305-s.png","title":"杨朗朗：我走得很慢但我从不后退","type":3},{"context":"杨朗朗：奔跑吧兄弟","id":"f39c8ce3549dabef015523b57fe60eff","musicListReleaseId":"f39c8ce3549dabef015523b501020efe","openUrl":"","picPath":"http://115.28.12.99/resource/recommend/20160606/50f89c450d5f4042a5bb571bbbb5f5e0.png","picPathSmall":"http://115.28.12.99/resource/recommend/20160606/50f89c450d5f4042a5bb571bbbb5f5e0-s.png","title":"杨朗朗：奔跑吧兄弟","type":1},{"context":"魏楚沅：未来","id":"f39c8ce3549dabef015505dfc17e0d36","musicListReleaseId":"f39c8ce3549dabef015505de3f420d35","openUrl":"","picPath":"http://115.28.12.99/resource/recommend/20160531/04c0a43d64ad47a3bc89326f32dc03f1.png","picPathSmall":"http://115.28.12.99/resource/recommend/20160531/04c0a43d64ad47a3bc89326f32dc03f1-s.png","title":"魏楚沅：未来","type":1},{"context":"人声兄弟：正青春","id":"f39c8ce3549dabef0154ebd59d3c0a97","musicListReleaseId":"f39c8ce3549dabef0154ebd0ed080a93","openUrl":"","picPath":"http://115.28.12.99/resource/recommend/20160526/3310db04d52e4f5a875aa859613a7c03.png","picPathSmall":"http://115.28.12.99/resource/recommend/20160526/3310db04d52e4f5a875aa859613a7c03-s.png","title":"人声兄弟：正青春","type":1},{"context":"曲/词/编曲/监制:黄贯中","id":"f39c8ce3549dabef0154e176135f0974","musicListReleaseId":"f39c8ce3549dabef0154e17568490973","openUrl":"","picPath":"http://115.28.12.99/resource/recommend/20160524/4a6adcbff4584018a563acacee53b93f.png","picPathSmall":"http://115.28.12.99/resource/recommend/20160524/4a6adcbff4584018a563acacee53b93f-s.png","title":"黄贯中：大英雄","type":1}]
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
         * context : 全能唱作人杨朗朗专访
         * id : f39c8ce3549dabef015529fbe0dc0fd8
         * musicListReleaseId : f39c8ce3549dabef01552895b27a0f6b
         * openUrl :
         * picPath : http://115.28.12.99/resource/recommend/20160607/639e44f1d46d4335ad2610d3784d6305.png
         * picPathSmall : http://115.28.12.99/resource/recommend/20160607/639e44f1d46d4335ad2610d3784d6305-s.png
         * title : 杨朗朗：我走得很慢但我从不后退
         * type : 3
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
