package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class SingerTypes {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"dicValue":"盗版","id":"11","index_":"4"},{"dicValue":"欧美","id":"4","index_":"4"},{"dicValue":"好的","id":"12","index_":"1"},{"dicValue":"日韩","id":"3","index_":"1"},{"dicValue":"港台","id":"2","index_":"1"},{"dicValue":"大陆","id":"1","index_":"1"}]
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
         * dicValue : 盗版
         * id : 11
         * index_ : 4
         */

        private String dicValue;
        private String id;
        private String index_;

        public void setDicValue(String dicValue) {
            this.dicValue = dicValue;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIndex_(String index_) {
            this.index_ = index_;
        }

        public String getDicValue() {
            return dicValue;
        }

        public String getId() {
            return id;
        }

        public String getIndex_() {
            return index_;
        }
    }
}
