package com.jxnu.zha.tingbei.model;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Music {
    /**
     * code : 0
     * msg : 查询成功
     * obj : [{"id":"f39c8ce34eb3d0a2014ebe2120c314ab","initialId":"560fa0bcdc76d8dc91f4598c43c22ff4","initialSingerId":"336595","lrcPath":"http://open.migu.cn:8100/material/lyric/2013/12/10/1312100151474334.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=56254794-f233-4bc6-bad9-3fa3a389836a","musicPathBak":"http://115.28.12.99/resource/musicbak/20151110/f39c8ce34eb3d0a2014ebe2120c314ab.mp3","musicSingerPicPath":"","name":"等着回头的爱情","setRingCall":[{"cooperation":"移动","id":"f39c8ce34eb3d0a2014ebe2120c314ae","initialid":"560fa0bcdc76d8dc91f4598c43c22ff4","ringCallPath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e"}],"setRingTone":[{"cooperation":"移动","id":"f39c8ce34eb3d0a2014ebe2120c314ad","initialid":"560fa0bcdc76d8dc91f4598c43c22ff4","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e"}],"singerName":"梦飞"}]
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
         * id : f39c8ce34eb3d0a2014ebe2120c314ab
         * initialId : 560fa0bcdc76d8dc91f4598c43c22ff4
         * initialSingerId : 336595
         * lrcPath : http://open.migu.cn:8100/material/lyric/2013/12/10/1312100151474334.lrc?m
         * musicPath : http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=56254794-f233-4bc6-bad9-3fa3a389836a
         * musicPathBak : http://115.28.12.99/resource/musicbak/20151110/f39c8ce34eb3d0a2014ebe2120c314ab.mp3
         * musicSingerPicPath :
         * name : 等着回头的爱情
         * setRingCall : [{"cooperation":"移动","id":"f39c8ce34eb3d0a2014ebe2120c314ae","initialid":"560fa0bcdc76d8dc91f4598c43c22ff4","ringCallPath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e"}]
         * setRingTone : [{"cooperation":"移动","id":"f39c8ce34eb3d0a2014ebe2120c314ad","initialid":"560fa0bcdc76d8dc91f4598c43c22ff4","ringTonePath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e"}]
         * singerName : 梦飞
         */

        private String id;
        private String initialId;
        private String initialSingerId;
        private String lrcPath;
        private String musicPath;
        private String musicPathBak;
        private String musicSingerPicPath;
        private String name;
        private String singerName;
        private List<SetRingCallEntity> setRingCall;
        private List<SetRingToneEntity> setRingTone;

        public void setId(String id) {
            this.id = id;
        }

        public void setInitialId(String initialId) {
            this.initialId = initialId;
        }

        public void setInitialSingerId(String initialSingerId) {
            this.initialSingerId = initialSingerId;
        }

        public void setLrcPath(String lrcPath) {
            this.lrcPath = lrcPath;
        }

        public void setMusicPath(String musicPath) {
            this.musicPath = musicPath;
        }

        public void setMusicPathBak(String musicPathBak) {
            this.musicPathBak = musicPathBak;
        }

        public void setMusicSingerPicPath(String musicSingerPicPath) {
            this.musicSingerPicPath = musicSingerPicPath;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public void setSetRingCall(List<SetRingCallEntity> setRingCall) {
            this.setRingCall = setRingCall;
        }

        public void setSetRingTone(List<SetRingToneEntity> setRingTone) {
            this.setRingTone = setRingTone;
        }

        public String getId() {
            return id;
        }

        public String getInitialId() {
            return initialId;
        }

        public String getInitialSingerId() {
            return initialSingerId;
        }

        public String getLrcPath() {
            return lrcPath;
        }

        public String getMusicPath() {
            return musicPath;
        }

        public String getMusicPathBak() {
            return musicPathBak;
        }

        public String getMusicSingerPicPath() {
            return musicSingerPicPath;
        }

        public String getName() {
            return name;
        }

        public String getSingerName() {
            return singerName;
        }

        public List<SetRingCallEntity> getSetRingCall() {
            return setRingCall;
        }

        public List<SetRingToneEntity> getSetRingTone() {
            return setRingTone;
        }

        public static class SetRingCallEntity {
            /**
             * cooperation : 移动
             * id : f39c8ce34eb3d0a2014ebe2120c314ae
             * initialid : 560fa0bcdc76d8dc91f4598c43c22ff4
             * ringCallPath : http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e
             */

            private String cooperation;
            private String id;
            private String initialid;
            private String ringCallPath;

            public void setCooperation(String cooperation) {
                this.cooperation = cooperation;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setInitialid(String initialid) {
                this.initialid = initialid;
            }

            public void setRingCallPath(String ringCallPath) {
                this.ringCallPath = ringCallPath;
            }

            public String getCooperation() {
                return cooperation;
            }

            public String getId() {
                return id;
            }

            public String getInitialid() {
                return initialid;
            }

            public String getRingCallPath() {
                return ringCallPath;
            }
        }

        public static class SetRingToneEntity {
            /**
             * cooperation : 移动
             * id : f39c8ce34eb3d0a2014ebe2120c314ad
             * initialid : 560fa0bcdc76d8dc91f4598c43c22ff4
             * ringTonePath : http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/2013/12/2013%E5%B9%B412%E6%9C%8805%E6%97%A5%E7%8B%AC%E7%AB%8B%E9%9F%B3%E4%B9%90%E4%BA%BA%E5%BC%95%E5%85%A5%E9%83%AD%E6%9E%97%E9%A3%9E%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A51%E9%A6%96/%E5%BD%A9%E9%93%83/6_mp3-128kbps/%E7%AD%89%E7%9D%80%E5%9B%9E%E5%A4%B4%E7%9A%84%E7%88%B1%E6%83%85-%E6%A2%A6%E9%A3%9E.mp3?channelid=08&msisdn=b6fb82d0-6ee4-4dc1-8df8-02146064c21e
             */

            private String cooperation;
            private String id;
            private String initialid;
            private String ringTonePath;

            public void setCooperation(String cooperation) {
                this.cooperation = cooperation;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setInitialid(String initialid) {
                this.initialid = initialid;
            }

            public void setRingTonePath(String ringTonePath) {
                this.ringTonePath = ringTonePath;
            }

            public String getCooperation() {
                return cooperation;
            }

            public String getId() {
                return id;
            }

            public String getInitialid() {
                return initialid;
            }

            public String getRingTonePath() {
                return ringTonePath;
            }
        }
    }
}
