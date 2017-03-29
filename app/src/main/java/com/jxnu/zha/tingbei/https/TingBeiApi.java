package com.jxnu.zha.tingbei.https;

import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by DaiQing.Zha on 2017/3/30.
 * email:13767191284@163.com
 * description:
 */
public class TingBeiApi {

    /**
     * 获取音乐详情
     * @param mapParam
     * @param handler
     */
    public static void getMusicDetail(Map<String,String> mapParam
            ,AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        if (mapParam != null && mapParam.size() > 0){
            for (String name:mapParam.keySet()){
                String value = String.valueOf(mapParam.get(name));
                params.put(name, value);
            }
        }
        ApiHttpClient.post(RoutConstant.getMusicListReleaseById,params,handler);
    }
}
