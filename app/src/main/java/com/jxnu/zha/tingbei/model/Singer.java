package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Singer extends Entity{


    /**
     * obj : [{"id":"402881f54ce3d565014ce3f032ac0355","initialid":"1000225665","name":"Turner John ","pic":"http://www.nnshow.cn/resource/singer/20150612/4662f04c23ec4bd8be5acfdbfa78cc0e.png"}]
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
         * id : 402881f54ce3d565014ce3f032ac0355
         * initialid : 1000225665
         * name : Turner John
         * pic : http://www.nnshow.cn/resource/singer/20150612/4662f04c23ec4bd8be5acfdbfa78cc0e.png
         */

        private String id;
        private String initialid;
        private String name;
        private String pic;

        public void setId(String id) {
            this.id = id;
        }

        public void setInitialid(String initialid) {
            this.initialid = initialid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getPic() {
            return pic;
        }
    }
}
