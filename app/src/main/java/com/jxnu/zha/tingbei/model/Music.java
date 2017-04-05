package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class Music extends Entity{


    /**
     * obj : [{"id":"402880e54cefbea5014cefc59606013e","initialId":"7515fc3c868b4001a8062126274e192e","initialSingerId":"1000225665","lrcPath":"http://open.migu.cn:8100/material/lyric/2013/12/16/9563951387292653874.lrc?m","musicPath":"http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/6%E6%9C%88%E6%89%B9%E9%87%8F/2013/06/2013%E5%B9%B403%E6%9C%8818%E6%97%A5%E5%9B%BD%E6%B3%B0%E8%AF%8D%E6%9B%B2%E9%A2%84%E7%95%99%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A515110%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/Recorder+Music+English+Lane+P+Arnold+M+Pitfield+T+Gregson+E+Lyon+D+Parrott+I+Turner+Royal+Ballet+Sinfonia+G+Sutherland+Prelude-Turner+John+%E7%BA%A6%E7%BF%B0+%E7%89%B9%E7%BA%B3.mp3?channelid=08&msisdn=106ba6e0-a2f8-4c11-90c4-ac487674d19c","musicPathBak":"http://www.nnshow.cn/resource/musicbak/20150611/402880e54cefbea5014cefc59606013e.mp3","musicSingerPicPath":"","name":"Recorder Music English Lane P Arnold M Pitfield T Gregson E Lyon D Parrott I Turner Royal Ballet Sinfonia G Sutherland Prelude","setRingCall":[{"cooperation":"移动","id":"402880e54cefbea5014cefc596070140","initialid":"7515fc3c868b4001a8062126274e192e","ringCallPath":""}],"setRingTone":[{"cooperation":"移动","id":"402880e54cefbea5014cefc59607013f","initialid":"7515fc3c868b4001a8062126274e192e","ringTonePath":""}],"singerName":"Turner John "}]
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
         * id : 402880e54cefbea5014cefc59606013e
         * initialId : 7515fc3c868b4001a8062126274e192e
         * initialSingerId : 1000225665
         * lrcPath : http://open.migu.cn:8100/material/lyric/2013/12/16/9563951387292653874.lrc?m
         * musicPath : http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/6%E6%9C%88%E6%89%B9%E9%87%8F/2013/06/2013%E5%B9%B403%E6%9C%8818%E6%97%A5%E5%9B%BD%E6%B3%B0%E8%AF%8D%E6%9B%B2%E9%A2%84%E7%95%99%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A515110%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/Recorder+Music+English+Lane+P+Arnold+M+Pitfield+T+Gregson+E+Lyon+D+Parrott+I+Turner+Royal+Ballet+Sinfonia+G+Sutherland+Prelude-Turner+John+%E7%BA%A6%E7%BF%B0+%E7%89%B9%E7%BA%B3.mp3?channelid=08&msisdn=106ba6e0-a2f8-4c11-90c4-ac487674d19c
         * musicPathBak : http://www.nnshow.cn/resource/musicbak/20150611/402880e54cefbea5014cefc59606013e.mp3
         * musicSingerPicPath :
         * name : Recorder Music English Lane P Arnold M Pitfield T Gregson E Lyon D Parrott I Turner Royal Ballet Sinfonia G Sutherland Prelude
         * setRingCall : [{"cooperation":"移动","id":"402880e54cefbea5014cefc596070140","initialid":"7515fc3c868b4001a8062126274e192e","ringCallPath":""}]
         * setRingTone : [{"cooperation":"移动","id":"402880e54cefbea5014cefc59607013f","initialid":"7515fc3c868b4001a8062126274e192e","ringTonePath":""}]
         * singerName : Turner John
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

        public static class SetRingCallEntity implements Serializable{
            /**
             * cooperation : 移动
             * id : 402880e54cefbea5014cefc596070140
             * initialid : 7515fc3c868b4001a8062126274e192e
             * ringCallPath :
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

        public static class SetRingToneEntity implements Serializable{
            /**
             * cooperation : 移动
             * id : 402880e54cefbea5014cefc59607013f
             * initialid : 7515fc3c868b4001a8062126274e192e
             * ringTonePath :
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
