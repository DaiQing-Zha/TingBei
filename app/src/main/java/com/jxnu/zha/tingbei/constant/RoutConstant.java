package com.jxnu.zha.tingbei.constant;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:url
 */
public class RoutConstant {
    /**
     * 获取推荐页分组信息
     */
    public final static String getRecommendGroupOnInter = "inter/getRecommendGroupOnInter";
    /**
     * 根据分组ID获取推荐页
     */
    public final static String getRecommendByGroupId = "inter/getRecommendByGroupId";
    /**
     * 根据标签ID获取发布歌单
     */
    public final static String getSongListByLabelId = "inter/getSongListByLabelId";
    /**
     * 根据ID获取发布的音乐集（暂时未用）
     */
    public final static String getMusicListReleaseById = "inter/getMusicListReleaseById";
    /**
     * 获取音乐电台标签
     */
    public final static String getRadioLabel = "inter/getRadioLabel";
    /**
     * 获取榜单标签
     */
    public final static String getBangLabel = "inter/getBangLabel";
    /**
     * 获取标签组信息
     */
    public final static String getLabelGroupOnInter = "inter/getLabelGroupOnInter";
    /**
     * 获取推荐音乐电台
     */
    public final static String getRadioListByRecommend = "inter/getRadioListByRecommend";
    /**
     * 获取标签音乐电台
     */
    public final static String getRadioListByLabelId = "inter/getRadioListByLabelId";
    /**
     * 获取歌单标签
     */
    public final static String getSongLabel = "inter/getSongLabel";

    /**
     * 获取推荐歌单（暂时未用）
     */
    public final static String getSongListByRecommend = "inter/getSongListByRecommend";
    /**
     * 获取推荐榜单
     */
    public final static String getBangListByRecommend = "inter/getBangListByRecommend";
    /**
     * 获取标签榜单
     */
    public final static String getBangListByLabelId = "inter/getBangListByLabelId";
    /**
     * 获取非推荐榜单
     */
    public final static String getBangListByNoRecommend = "inter/getBangListByNoRecommend";
    /**
     * 获取歌手类型
     */
    public final static String getSingerTypesOnInter = "inter/getSingerTypesOnInter";
    /**
     * 根据歌手ID获取歌手信息
     */
    public final static String getSingerById = "inter/getSingerById";
    /**
     * 根据歌手类型获取歌手信息
     */
    public final static String getSingerBySingerType = "inter/getSingerBySingerType";
    /**
     * 根据歌手ID获取歌曲信息
     */
    public final static String getMusicBySingerId = "inter/getMusicBySingerId";
    /**
     * 根据歌曲ID获取彩铃及振铃信息
     */
    public final static String getRingInfoByMusicId = "inter/getRingInfoByMusicId";
    /**
     * 登录
     */
    public final static String loginAction = "inter/login";
    /**
     * 注册
     */
    public final static String registerAction = "inter/register";
    /**
     * loginId是否重复
     */
    public final static String sameLoginId = "inter/sameLoginId";
    /**
     * 昵称是否重复
     */
    public final static String sameNickName = "inter/sameNickName";
    /**
     * 手机号是否重复
     */
    public final static String samePhone = "inter/samePhone";
    /**
     * 根据NoRing获取音乐集
     */
    public final static String getMusicListReleaseByIdNoRingInfo = "inter/getMusicListReleaseByIdNoRingInfo";
}
