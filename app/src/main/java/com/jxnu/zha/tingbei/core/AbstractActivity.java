package com.jxnu.zha.tingbei.core;

import android.view.View;

import com.jxnu.zha.qinglibrary.util.FileLocalCache;
import com.jxnu.zha.qinglibrary.util.L;
import com.jxnu.zha.qinglibrary.util.NetWorkUtil;
import com.jxnu.zha.qinglibrary.util.QingHttpClient;
import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.utils.EAlertStyle;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cz.msebera.android.httpclient.Header;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:继承于BaseActivity，该类中增加了网络请求的封装
 */
public abstract class AbstractActivity extends BaseActivity {
    public abstract class AsyncTack{
        //是否需要进行网络判断,true判断(默认),false不需要判断
        private boolean isNetWork = true;
        //是否覆盖mainBody显示showWaitDialog, true覆盖显示showWaitDialog,false覆盖，不显示
        private boolean isCover;
        private String url;
        protected TipInfoLayout mTipInfoLayout = AbstractActivity.super.mTipInfoLayout;

        public AsyncTack(){
            this.isCover=true;
        }

        /**
         * @param isCover 是否覆盖mainBody显示showWaitDialog,
         *  true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog，
         */
        public AsyncTack(boolean isCover){
            this.isCover=isCover;
        }

        /**
         * @param isCover 是否覆盖mainBody显示showWaitDialog, true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog
         * @param isNetWork 是否需要进行网络判断,true判断(默认),false不需要判断
         */
        public AsyncTack(boolean isCover,boolean isNetWork){
            this.isCover=isCover;
            this.isNetWork=isNetWork;
        }
        /**
         * @author caibing.zhang
         * @createdate 2015年1月17日 下午3:10:26
         * @Description: 网络加载成功
         * @param responseInfo 服务器返回参数
         */
        public abstract void loadSuccess(String responseInfo);
        /**
         * @author caibing.zhang
         * @createdate 2015年1月17日 下午3:10:49
         * @Description: 网络加载失败：异常处理
         */
        public abstract void exception();

        /**
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         * @param url
         * @param params 参数
         */
        public void post(String url,RequestParams params){
            post(true, url, params);
        }

        /**
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         * @param isLoading 是否显示加载对话框
         * @param url
         * @param params 参数
         */
        public void post(boolean isLoading,String url,RequestParams params){
            request(true, isLoading, url, params);
        }

        /**
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         * @param isLoading 是否显示加载对话框
         * @param url
         * @param params 参数
         */
        public void get(boolean isLoading,String url, RequestParams params){
            request(false,isLoading,url,params);
        }

        /**
         *
         * @param isPost true：post请求，false:get
         * @param isLoading 是否显示加载对话框
         * @param url
         * @param params 参数
         */
        private void request(boolean isPost,boolean isLoading,String url, RequestParams params){

            //没有网络或不需要网络判断
            if(isNetWork && !NetWorkUtil.NETWORK){
                mTipInfoLayout.setNetWorkError();
                showSnackBarMsg(EAlertStyle.ALERT, R.string.http_noNetwork);
                exception();
                return;
            }
            this.url=url;
            if(isCover && mMainBody!=null){
                mMainBody.setVisibility(View.GONE);
            }
            if(isLoading){
                mTipInfoLayout.setLoading();
            }
            if(isPost){
                QingHttpClient.post(url, params, jsonHttpResponseHandler);
            }else{
                QingHttpClient.get(url, params, jsonHttpResponseHandler);
            }
        }

        /**
         * @author caibing.zhang
         * @createdate 2015年1月16日 下午10:06:21
         * @Description: 判断返回的判断码
         * @param statusCode
         * @return
         */
        private boolean isStatusCode(int statusCode){
            mTipInfoLayout.completeLoading();
            if(statusCode==200 || statusCode==201){  //201(已创建)请求成功并且服务器创建了新的资源。
                return true;
            }else{
                int messageResId = isStatusCodeMessage(statusCode);
                showSnackBarMsg(EAlertStyle.ALERT, messageResId);
                return false;
            }
        }

        private int isStatusCodeMessage(int statusCode){
            int messageResId;
            switch (statusCode) {
                case 404:
                    messageResId= R.string.http_statusCode404;
                    break;
                case 500:
                    messageResId= R.string.http_statusCode500;
                    break;
                default:
                    messageResId= R.string.http_loadFailure;
                    break;
            }
            return messageResId;
        }
        /**
         * @author caibing.zhang
         * @createdate 2015年1月16日 下午11:14:15
         * @Description: 处理异常
         * @param throwable
         */
        private void handleThrowable(String message,AsyncTack asyncTask,Throwable throwable){
            mTipInfoLayout.setFailureInfo(message);
            try {
                String exceptionInfo = analysisException(throwable);
                L.e(exceptionInfo);
                if(exceptionInfo.indexOf("UnknownHostException")!=-1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("NoDataException")!=-1){
                    showSnackBarMsg(EAlertStyle.WARNING, R.string.http_noData);
                }else if(exceptionInfo.indexOf("SocketException")!=-1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("SocketTimeoutException")!=-1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("ConnectTimeoutException")!=-1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("HttpResponseException")!=-1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_response);
                }else {
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_loadError);
                }
            } catch (IOException e) {
                e.printStackTrace();
                showSnackBarMsg(EAlertStyle.ALERT, R.string.http_loadError);
            }
            asyncTask.exception();
        }
        /**
         * 将异常信息转化成字符串
         * @param t
         * @return
         * @throws IOException
         */
        private String analysisException(Throwable t) throws IOException{
            if(t == null)
                return null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try{
                t.printStackTrace(new PrintStream(baos));
            }finally{
                baos.close();
            }
            return baos.toString();
        }
        JsonHttpResponseHandler jsonHttpResponseHandler=new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONArray response-->:" + response.toString());
                if(isStatusCode(statusCode)){
                    FileLocalCache.saveFile(url, response.toString());
                    loadSuccess(response.toString());
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONObject response-->:" + response);
                if(isStatusCode(statusCode)){
                    String responseInfo=response.toString();
                    FileLocalCache.saveFile(url, responseInfo);
                    loadSuccess(responseInfo);
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers,String responseString) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", String responseString-->:" + responseString);
                if(isStatusCode(statusCode)){
                    FileLocalCache.saveFile(url, responseString);
                    loadSuccess(responseString);
                }
            }
            @Override
            public void onFailure(int statusCode,Header[] headers,
                                  String responseString, Throwable throwable) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", responseString-->:" + responseString);
                int messageResId = isStatusCodeMessage(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONArray errorResponse) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONArray errorResponse-->");
                int messageResId = isStatusCodeMessage(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONObject errorResponse) {
                L.d("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONObject errorResponse-->");
                int messageResId = isStatusCodeMessage(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
        };
    }
}
