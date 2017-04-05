package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class SingerTypes extends Entity{


    /**
     * dicValue : 欧美男歌手
     * id : 23
     * index_ : 9
     */

    private List<ObjBean> obj;

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        private String dicValue;
        private String id;
        private String index_;

        public String getDicValue() {
            return dicValue;
        }

        public void setDicValue(String dicValue) {
            this.dicValue = dicValue;
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
    }
}
