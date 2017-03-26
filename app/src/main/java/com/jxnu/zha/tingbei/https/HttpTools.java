package com.jxnu.zha.tingbei.https;

import com.jxnu.zha.tingbei.engine.ServerConfig;

/**
 * Created by DaiQing.Zha on 2017/3/17.
 * email:13767191284@163.com
 * description:
 */
public class HttpTools {

    /**
     * 获取绝对路径
     * @param url
     * @return
     */
    public static String getAbsoluteUrl(String url){
        return ServerConfig.getServerRoot() + url;
    }

    public static final String APP_ID = "e431976548adec559dd10664e0f117f9";
}
