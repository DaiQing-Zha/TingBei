package com.jxnu.zha.tingbei.https;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.jxnu.zha.tingbei.engine.ServerConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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

    public static final String APP_ID = "e7cd6f1b8546234697b07a6d0231c507";

    /**
     * 通过post方式获取网络数据
     * @param route
     * @param params
     * @return
     */
    public static String httpPost(String route, Map<String, Object> params) {

        Map<String,Object> mapParams = new HashMap<>();
        if (params != null && params.size() > 0){
            for (String name:params.keySet()){
                String value = String.valueOf(params.get(name));
                mapParams.put(name, value);
            }
        }
        String urlPath = ServerConfig.getServerRoot() + route;
        String dataString = getRequestData(mapParams, "UTF-8").toString();
        byte[] data = dataString.getBytes();// 获得请求体
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(60 * 1000);    // 设置连接超时时间
            httpURLConnection.setReadTimeout(60 * 1000);      // 设置读取时间
            httpURLConnection.setDoInput(true);    // 打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST"); // 设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);     // 使用Post方式不能使用缓存

            // 设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);
            int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
            Log.e("mainNetWork","response = " + response);
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream is = httpURLConnection.getInputStream();
                byte[] bytes = getBytes(is);
                String str = new String(bytes);
                return str;
            }
            if (response == HttpURLConnection.HTTP_NOT_FOUND){
                return "ServerError";
            }
            return "NetworkError";
        } catch (MalformedURLException e){  //URL格式或者路径错误异常
            return "MalformedURLException";
        } catch (IOException e){ //网络异常
            return "NetworkError";
        }catch (Exception e) { //普通异常
            return "Exception";
        }
    }

    /**
     * 获取封装好的请求体信息
     * @param params
     * @param encode
     * @return
     */
    private static StringBuffer getRequestData(Map<String, Object> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer(); // 存储封装好的请求体信息
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), encode)).append("&");
            }
            if (params.size() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1); // 删除最后的一个"&"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
    /**
     * 将流数据转换成二进制数据
     * @param is
     * @return
     * @throws Exception
     */
    private static byte[] getBytes(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        bos.flush();
        byte[] result = bos.toByteArray();
        return result;
    }
}
