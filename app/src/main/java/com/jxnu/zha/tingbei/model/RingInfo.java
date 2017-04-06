package com.jxnu.zha.tingbei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class RingInfo extends Entity{

    /**
     * addTime : 2015-04-25 00:00:00
     * cooperation : 移动
     * id : 402880e54cefbea5014cefc59606013e
     * initialId : 7515fc3c868b4001a8062126274e192e
     * initialSingerId : 1000225665
     * isRecommend : 1
     * isTime : 2015-09-30 00:00:00
     * lrcPath : http://open.migu.cn:8100/material/lyric/2013/12/16/9563951387292653874.lrc?m
     * musicPath : http://tyst.migu.cn/public/ringmaker01/n4/karakalupload/6%E6%9C%88%E6%89%B9%E9%87%8F/2013/06/2013%E5%B9%B403%E6%9C%8818%E6%97%A5%E5%9B%BD%E6%B3%B0%E8%AF%8D%E6%9B%B2%E9%A2%84%E7%95%99%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A515110%E9%A6%96/%E5%85%A8%E6%9B%B2%E8%AF%95%E5%90%AC/Mp3_64_22_16/Recorder+Music+English+Lane+P+Arnold+M+Pitfield+T+Gregson+E+Lyon+D+Parrott+I+Turner+Royal+Ballet+Sinfonia+G+Sutherland+Prelude-Turner+John+%E7%BA%A6%E7%BF%B0+%E7%89%B9%E7%BA%B3.mp3?channelid=08&msisdn=106ba6e0-a2f8-4c11-90c4-ac487674d19c
     * musicPathBak : http://www.nnshow.cn/resource/musicbak/20150611/402880e54cefbea5014cefc59606013e.mp3
     * musicPicPath :
     * musicSingerPicPath :
     * name : Recorder Music English Lane P Arnold M Pitfield T Gregson E Lyon D Parrott I Turner Royal Ballet Sinfonia G Sutherland Prelude
     * nameSpell : RecorderMusicEnglishLanePArnoldMPitfieldTGregsonELyonDParrottITurnerRoyalBalletSinfoniaGSutherlandPrelude
     * sell : 2
     * setRingCall : [{"cooperation":"移动","id":"402880e54cefbea5014cefc596070140","initialid":"7515fc3c868b4001a8062126274e192e","ringCallPath":""}]
     * setRingTone : [{"cooperation":"移动","id":"402880e54cefbea5014cefc59607013f","initialid":"7515fc3c868b4001a8062126274e192e","ringTonePath":""}]
     * singerName : Turner John
     * singerNameSpell : TurnerJohn
     * valueFrom : 接口
     */

    private ObjBean obj;

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        private String addTime;
        private String cooperation;
        private String id;
        private String initialId;
        private String initialSingerId;
        private int isRecommend;
        private String isTime;
        private String lrcPath;
        private String musicPath;
        private String musicPathBak;
        private String musicPicPath;
        private String musicSingerPicPath;
        private String name;
        private String nameSpell;
        private int sell;
        private String singerName;
        private String singerNameSpell;
        private String valueFrom;
        /**
         * cooperation : 移动
         * id : 402880e54cefbea5014cefc596070140
         * initialid : 7515fc3c868b4001a8062126274e192e
         * ringCallPath :
         */

        private List<SetRingCallBean> setRingCall;
        /**
         * cooperation : 移动
         * id : 402880e54cefbea5014cefc59607013f
         * initialid : 7515fc3c868b4001a8062126274e192e
         * ringTonePath :
         */

        private List<SetRingToneBean> setRingTone;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getCooperation() {
            return cooperation;
        }

        public void setCooperation(String cooperation) {
            this.cooperation = cooperation;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInitialId() {
            return initialId;
        }

        public void setInitialId(String initialId) {
            this.initialId = initialId;
        }

        public String getInitialSingerId() {
            return initialSingerId;
        }

        public void setInitialSingerId(String initialSingerId) {
            this.initialSingerId = initialSingerId;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getIsTime() {
            return isTime;
        }

        public void setIsTime(String isTime) {
            this.isTime = isTime;
        }

        public String getLrcPath() {
            return lrcPath;
        }

        public void setLrcPath(String lrcPath) {
            this.lrcPath = lrcPath;
        }

        public String getMusicPath() {
            return musicPath;
        }

        public void setMusicPath(String musicPath) {
            this.musicPath = musicPath;
        }

        public String getMusicPathBak() {
            return musicPathBak;
        }

        public void setMusicPathBak(String musicPathBak) {
            this.musicPathBak = musicPathBak;
        }

        public String getMusicPicPath() {
            return musicPicPath;
        }

        public void setMusicPicPath(String musicPicPath) {
            this.musicPicPath = musicPicPath;
        }

        public String getMusicSingerPicPath() {
            return musicSingerPicPath;
        }

        public void setMusicSingerPicPath(String musicSingerPicPath) {
            this.musicSingerPicPath = musicSingerPicPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameSpell() {
            return nameSpell;
        }

        public void setNameSpell(String nameSpell) {
            this.nameSpell = nameSpell;
        }

        public int getSell() {
            return sell;
        }

        public void setSell(int sell) {
            this.sell = sell;
        }

        public String getSingerName() {
            return singerName;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public String getSingerNameSpell() {
            return singerNameSpell;
        }

        public void setSingerNameSpell(String singerNameSpell) {
            this.singerNameSpell = singerNameSpell;
        }

        public String getValueFrom() {
            return valueFrom;
        }

        public void setValueFrom(String valueFrom) {
            this.valueFrom = valueFrom;
        }

        public List<SetRingCallBean> getSetRingCall() {
            return setRingCall;
        }

        public void setSetRingCall(List<SetRingCallBean> setRingCall) {
            this.setRingCall = setRingCall;
        }

        public List<SetRingToneBean> getSetRingTone() {
            return setRingTone;
        }

        public void setSetRingTone(List<SetRingToneBean> setRingTone) {
            this.setRingTone = setRingTone;
        }

        public static class SetRingCallBean implements Serializable{
            private String cooperation;
            private String id;
            private String initialid;
            private String ringCallPath;

            public String getCooperation() {
                return cooperation;
            }

            public void setCooperation(String cooperation) {
                this.cooperation = cooperation;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInitialid() {
                return initialid;
            }

            public void setInitialid(String initialid) {
                this.initialid = initialid;
            }

            public String getRingCallPath() {
                return ringCallPath;
            }

            public void setRingCallPath(String ringCallPath) {
                this.ringCallPath = ringCallPath;
            }
        }

        public static class SetRingToneBean implements Serializable{
            private String cooperation;
            private String id;
            private String initialid;
            private String ringTonePath;

            public String getCooperation() {
                return cooperation;
            }

            public void setCooperation(String cooperation) {
                this.cooperation = cooperation;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInitialid() {
                return initialid;
            }

            public void setInitialid(String initialid) {
                this.initialid = initialid;
            }

            public String getRingTonePath() {
                return ringTonePath;
            }

            public void setRingTonePath(String ringTonePath) {
                this.ringTonePath = ringTonePath;
            }
        }
    }
}
