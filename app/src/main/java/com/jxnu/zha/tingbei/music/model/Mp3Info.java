package com.jxnu.zha.tingbei.music.model;

/**
 * Created by DaiQing.Zha on 2017/4/12 0012.
 */
public class Mp3Info {

    private String musicId;
    private String musicPicPath;
    private String musicPicPathSmall;
    private String singerPicPath;
    private String singerPicPathSmall;
    /**
     * 歌曲路径
     */
    private String musicUrl;
    /**
     * 歌曲本地存储路径
     */
    private String musicLocalUrl;
    /**
     * 音乐名称
     */
    private String musicName;
    /**
     * 歌手名称
     */
    private String singerName;

    public Mp3Info() {
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicPicPath() {
        return musicPicPath;
    }

    public void setMusicPicPath(String musicPicPath) {
        this.musicPicPath = musicPicPath;
    }

    public String getMusicPicPathSmall() {
        return musicPicPathSmall;
    }

    public void setMusicPicPathSmall(String musicPicPathSmall) {
        this.musicPicPathSmall = musicPicPathSmall;
    }

    public String getSingerPicPath() {
        return singerPicPath;
    }

    public void setSingerPicPath(String singerPicPath) {
        this.singerPicPath = singerPicPath;
    }

    public String getSingerPicPathSmall() {
        return singerPicPathSmall;
    }

    public void setSingerPicPathSmall(String singerPicPathSmall) {
        this.singerPicPathSmall = singerPicPathSmall;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicLocalUrl() {
        return musicLocalUrl;
    }

    public void setMusicLocalUrl(String musicLocalUrl) {
        this.musicLocalUrl = musicLocalUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @Override
    public String toString() {
        return "Mp3Info{" +
                "musicUrl='" + musicUrl + '\'' +
                ", musicLocalUrl='" + musicLocalUrl + '\'' +
                ", musicName='" + musicName + '\'' +
                ", singerName='" + singerName + '\'' +
                '}';
    }
}
