package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Label {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"40287e814e486964014e486b377a0000","name":"很好"},{"id":"402880e54d368437014d368610b70002","name":"唯美"},{"id":"402880e54d3c5960014d3c5cf8ad0000","name":"清新"},{"id":"402880e54d3c5960014d3c5d12790001","name":"非常好"},{"id":"402880e54d3c6b08014d3c6c6e040000","name":"榜单1"},{"id":"402880e54d3c6b08014d3c6c88ec0001","name":"榜单2"},{"id":"402880e54d3c75ff014d3c7661ab0000","name":"电台1"},{"id":"402880e54d3c75ff014d3c76829c0001","name":"电台2"}]
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
         * id : 40287e814e486964014e486b377a0000
         * name : 很好
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
