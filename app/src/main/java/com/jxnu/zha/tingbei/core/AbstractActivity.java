package com.jxnu.zha.tingbei.core;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.android.volley.Cache;
import com.jxnu.zha.qinglibrary.manager.CacheManager;
import com.jxnu.zha.qinglibrary.util.FileLocalCache;
import com.jxnu.zha.qinglibrary.util.L;
import com.jxnu.zha.qinglibrary.util.NetWorkUtil;
import com.jxnu.zha.qinglibrary.util.QingHttpClient;
import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.utils.EAlertStyle;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;

import cz.msebera.android.httpclient.Header;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:继承于BaseActivity，该类中增加了网络请求的封装
 */
public abstract class AbstractActivity extends BaseActivity {
    public abstract class AsyncTack{
        //是否需要进行网络判断,true判断(默认),false不需要判断
        private boolean isNeedNetWork = true;
        //是否覆盖mainBody显示showWaitDialog, true覆盖显示showWaitDialog,false覆盖，不显示
        private boolean isNeedCover;
        private String url;
        protected TipInfoLayout mTipInfoLayout = AbstractActivity.super.mTipInfoLayout;

        public AsyncTack(){
            this.isNeedCover =true;
        }

        /**
         * @param isNeedCover 是否覆盖mainBody显示showWaitDialog,
         *  true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog，
         */
        public AsyncTack(boolean isNeedCover){
            this.isNeedCover =isNeedCover;
        }

        /**
         * @param isNeedNetCover 是否覆盖mainBody显示showWaitDialog, true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog
         * @param isNeedNetWork 是否需要进行网络判断,true判断(默认),false不需要判断
         */
        public AsyncTack(boolean isNeedNetCover,boolean isNeedNetWork){
            this.isNeedCover = isNeedNetCover;
            this.isNeedNetWork = isNeedNetWork;
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
            if(isNeedNetWork && !NetWorkUtil.NETWORK){
                mTipInfoLayout.setNetWorkError();
                showSnackBarMsg(EAlertStyle.ALERT, R.string.http_noNetwork);
                exception();
                return;
            }
            this.url = url;
            if(isNeedCover && mMainBody != null){
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
         * @Description: 根据状态码判断网络请求是否正确返回
         * @param statusCode
         * @return
         */
        private boolean isCorrectResponse(int statusCode){
            mTipInfoLayout.completeLoading();
            if(statusCode==200 || statusCode==201){  //201(已创建)请求成功并且服务器创建了新的资源。
                return true;
            }else{
                int messageResId = getMessageIdByStatusCode(statusCode);
                showSnackBarMsg(EAlertStyle.ALERT, messageResId);
                return false;
            }
        }

        /**
         * 根据状态码获取要提示的错误信息的resId
         * @param statusCode
         * @return
         */
        private int getMessageIdByStatusCode(int statusCode){
            int messageResId;
            switch (statusCode) {
                case 404:
                    messageResId= R.string.http_statusCode404;
                    break;
                case 500:
                    messageResId= R.string.http_statusCode500;
                    break;
                default:
                    messageResId= R.string.http_defaultLoadError;
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
                if(exceptionInfo.indexOf("UnknownHostException") != -1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_unKnowHost);
                }else if(exceptionInfo.indexOf("NoDataException") != -1){
                    showSnackBarMsg(EAlertStyle.WARNING, R.string.http_defaultLoadError);
                }else if(exceptionInfo.indexOf("SocketException") != -1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("SocketTimeoutException") != -1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_timeOut);
                }else if(exceptionInfo.indexOf("ConnectTimeoutException") != -1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_connectTimeOut);
                }else if(exceptionInfo.indexOf("HttpResponseException") != -1){
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_severNoResponse);
                }else {
                    showSnackBarMsg(EAlertStyle.ALERT, R.string.http_defaultLoadError);
                }
            } catch (IOException e) {
                e.printStackTrace();
                showSnackBarMsg(EAlertStyle.ALERT, R.string.http_defaultLoadError);
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
                if(isCorrectResponse(statusCode)){
                    FileLocalCache.saveFile(url, response.toString());
                    loadSuccess(response.toString());
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONObject response-->:" + response);
                if(isCorrectResponse(statusCode)){
                    String responseInfo=response.toString();
                    FileLocalCache.saveFile(url, responseInfo);
                    loadSuccess(responseInfo);
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers,String responseString) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", String responseString-->:" + responseString);
                if(isCorrectResponse(statusCode)){
                    FileLocalCache.saveFile(url, responseString);
                    loadSuccess(responseString);
                }
            }
            @Override
            public void onFailure(int statusCode,Header[] headers,
                                  String responseString, Throwable throwable) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", responseString-->:" + responseString);
                int messageResId = getMessageIdByStatusCode(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONArray errorResponse) {
                L.w("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONArray errorResponse-->");
                int messageResId = getMessageIdByStatusCode(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONObject errorResponse) {
                L.d("--statusCode-->:" + statusCode + ", url-->:" + HttpTools.getAbsoluteUrl(url) + ", JSONObject errorResponse-->");
                int messageResId = getMessageIdByStatusCode(statusCode);
                handleThrowable(getString(messageResId),AsyncTack.this,throwable);
            }
        };
    }

    private AsyncTask<String, Void, Entity> mCacheTask;

    /**
     * 读取数据
     * @param serializable
     * @return
     */
    protected Entity readData(Serializable serializable) {
        return null;
    }

    /**
     * 获取缓存key
     * @return
     */
    protected String getCacheKey() {
        return null;
    }

    /**
     * 保存缓存文件
     * @param entity
     */
    protected void saveCache(Entity entity) {
        new SaveObjectTaskAsync(this, entity, getCacheKey()).execute();
    }

    /**
     * 读取缓存文件
     * @param cacheKey
     * @param errorInfo
     */
    protected void readCacheData(String cacheKey,String errorInfo) {
        cancelReadCache();
        mCacheTask = new CacheTaskAsync(this,errorInfo).execute(cacheKey);
    }

    /**
     * 取消读取缓存文件
     */
    private void cancelReadCache() {
        if (mCacheTask != null) {
            mCacheTask.cancel(true);
            mCacheTask = null;
        }
    }
    /**
     * 数据加载成功
     * @param entity
     */
    protected void executeOnLoadDataSuccess(Entity entity) {}

    /**
     * 数据加载失败
     * @param errorInfo
     */
    protected void executeOnLoadDataFailure(String errorInfo) {}
    /**
     * 读取缓存文件
     */
    private class CacheTaskAsync extends AsyncTask<String,Void,Entity>{
        private final WeakReference<Context> mContext;
        private String errorInfo;
        public CacheTaskAsync(Context context) {
            this.mContext = new WeakReference<Context>(context);
        }
        public CacheTaskAsync(Context context,String errorInfo) {
            this.mContext = new WeakReference<Context>(context);
            this.errorInfo = errorInfo;
        }
        @Override
        protected Entity doInBackground(String... params) {
            if (mContext != null){
                Serializable serializable = CacheManager.readObject(mContext.get(),params[0]);
                if (serializable == null) {
                    return null;
                }else{
                    return readData(serializable);
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Entity entity) {
            super.onPostExecute(entity);
            if (entity != null){
                executeOnLoadDataSuccess(entity);
            }else{
                executeOnLoadDataFailure(errorInfo);
            }
        }
    }

    /**
     * 保存对象异步类
     */
    protected class SaveObjectTaskAsync extends AsyncTask<Void,Void,Void> {
        private final WeakReference<Context> mContext;
        private final Serializable serializable;
        private final String key;
        public SaveObjectTaskAsync(Context context, Serializable serializable, String key) {
            mContext = new WeakReference<Context>(context);
            this.serializable = serializable;
            this.key = key;
        }
        @Override
        protected Void doInBackground(Void... params) {
            CacheManager.saveObject(mContext.get(),serializable,key);
            getFilesDir();
            return null;
        }
    }
}
