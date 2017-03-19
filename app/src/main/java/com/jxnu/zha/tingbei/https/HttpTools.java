package com.jxnu.zha.tingbei.https;

import com.jxnu.zha.tingbei.engine.ServerConfig;

/**
 * Created by DaiQing.Zha on 2017/3/17.
 * email:13767191284@163.com
 * description:
 */
public class HttpTools {
    public static String getAbsoluteUrl(String url){
        return ServerConfig.getServerRoot() + url;
    }
}
