package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Singer {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"402880e54cefbea5014cefc5fb99014c","initialid":"353","name":"汪峰"},{"id":"402881f54cf982af014cf98321250000","initialid":"5fcfe79c881a45dabf85dd04d74f6470","name":"3334","pic":"http://a.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510fc941eeb96459252dd52aa58f.jpg"},{"id":"402881f54d2863d1014d286eb9e60000","initialid":"e3c140cc9eb5404bb3c3b733a9ac1ead","name":"智小楠","pic":"http://localhost:8080/resource/singer/20150506/872b53d2d0664ec8a140226b5eff0e81.png","picSmall":"http://localhost:8080/resource/singer/20150506/872b53d2d0664ec8a140226b5eff0e81-s.png"}]
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
         * id : 402880e54cefbea5014cefc5fb99014c
         * initialid : 353
         * name : 汪峰
         */

        private String id;
        private String initialid;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setInitialid(String initialid) {
            this.initialid = initialid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getInitialid() {
            return initialid;
        }

        public String getName() {
            return name;
        }
    }
}
