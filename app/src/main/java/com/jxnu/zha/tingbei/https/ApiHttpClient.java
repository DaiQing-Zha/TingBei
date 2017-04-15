package com.jxnu.zha.tingbei.https;

import com.jxnu.zha.qinglibrary.util.L;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by DaiQing.Zha on 2017/3/30.
 * email:13767191284@163.com
 * description:
 */
public class ApiHttpClient {

    public static AsyncHttpClient client;

    public ApiHttpClient() {}

    public static AsyncHttpClient getHttpClient() {
        return client;
    }
    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
    }

    public static void post(String partUrl, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        client.post(HttpTools.getAbsoluteUrl(partUrl), params, handler);
        L.e(new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());
    }
}
