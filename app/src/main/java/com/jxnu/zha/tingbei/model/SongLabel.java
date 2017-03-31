package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class SongLabel extends Entity{

    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"f39c8ce351f12de20151f1d058ec000a","name":"中国之星第一季"},{"id":"f39c8ce34ed7f942014ed83aad6a000e","name":"动漫专区"},{"id":"f39c8ce34ef74d45014efccf6abe02f4","name":"寂寞"}]
     */

    private List<ObjEntity> obj;

    public void setObj(List<ObjEntity> obj) {
        this.obj = obj;
    }


    public List<ObjEntity> getObj() {
        return obj;
    }

    public static class ObjEntity implements Serializable{
        /**
         * id : f39c8ce351f12de20151f1d058ec000a
         * name : 中国之星第一季
         */

        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
