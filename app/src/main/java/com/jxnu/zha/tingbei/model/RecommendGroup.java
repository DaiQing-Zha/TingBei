package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class RecommendGroup {

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"f39c8ce34dd792aa014dd7aeab7d0018","index_":"0","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 我在首页的最上面"},{"id":"f39c8ce34dd8afe4014ddc66814a0133","index_":"1","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 我在推荐页的广告栏里"},{"id":"f39c8ce34dd8afe4014ddc674a370134","index_":"2","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 推荐歌单"},{"id":"f39c8ce34dd8afe4014ddc67920e0135","index_":"5","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 民谣推荐"},{"id":"f39c8ce34de15419014de2096a1602e8","index_":"5","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 暖阳推荐"},{"id":"f39c8ce34dd8afe4014ddc682b600136","index_":"7","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 伤感推荐"},{"id":"f39c8ce34de15419014de1c867060169","index_":"9","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 摇滚推荐"},{"id":"f39db5d34fd41edf014fde8a38f0012b","index_":"9","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 有声读物"},{"id":"297ebbf2525d594901525da69c3d0000","index_":"100","labelId":"f39c8ce351f12de20151f1d058ec000a","name":" 我的最爱","picPath":"http://115.28.12.99/resource/recommendGroup/20160120 /dd6b737d542943098613c591c3707d65.png"}]
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
         * id : f39c8ce34dd792aa014dd7aeab7d0018
         * index_ : 0
         * labelId : f39c8ce351f12de20151f1d058ec000a
         * name :  我在首页的最上面
         */

        private String id;
        private String index_;
        private String labelId;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public void setLabelId(String labelId) {
            this.labelId = labelId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getIndex_() {
            return index_;
        }

        public String getLabelId() {
            return labelId;
        }

        public String getName() {
            return name;
        }
    }
}
