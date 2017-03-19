package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class RadioLabel {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"40287e814e486964014e486b377a0000","labelGroup":{"id":"40287e814e4cb712014e4ccaed480000","index_":"0","name":"歌单组"},"name":"很好"}]
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
         * labelGroup : {"id":"40287e814e4cb712014e4ccaed480000","index_":"0","name":"歌单组"}
         * name : 很好
         */

        private String id;
        private LabelGroupEntity labelGroup;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setLabelGroup(LabelGroupEntity labelGroup) {
            this.labelGroup = labelGroup;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public LabelGroupEntity getLabelGroup() {
            return labelGroup;
        }

        public String getName() {
            return name;
        }

        public static class LabelGroupEntity {
            /**
             * id : 40287e814e4cb712014e4ccaed480000
             * index_ : 0
             * name : 歌单组
             */

            private String id;
            private String index_;
            private String name;

            public void setId(String id) {
                this.id = id;
            }

            public void setIndex_(String index_) {
                this.index_ = index_;
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

            public String getName() {
                return name;
            }
        }
    }
}
