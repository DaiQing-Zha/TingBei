package com.jxnu.zha.tingbei.core;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jxnu.zha.qinglibrary.manager.CacheManager;
import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.model.Entity;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by DaiQing.Zha on 2017/3/17.
 * email:13767191284@163.com
 * description:
 */
public abstract class BaseFragment extends AbstractFragment{

    private View rootView;
    protected RequestQueue mRQueue;
    protected BaseActivity father;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = getRootView(inflater,container,savedInstanceState);
            mTipInfoLayout = (TipInfoLayout)rootView.findViewById(R.id.tipInfo_layout);
            ButterKnife.bind(this,rootView);
            builderView(rootView);
        }else{
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) viewGroup.removeAllViewsInLayout();
        }
        return rootView;
    }

    public abstract View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void builderView(View rootView);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRQueue = Volley.newRequestQueue(getBaseActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        father = (BaseActivity) context;
    }

    /**
     * 自定义查找控件的方法
     * @param view
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T findWidget(View view,int resId){
        return (T) view.findViewById(resId);
    }

    /**
     * 自定义控件查找方法
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T findWidget(int resId){
        return (T) father.findViewById(resId);
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
    protected String getCacheKey(Entity entity) {
        return null;
    }

    /**
     * 保存缓存文件
     * @param entity
     */
    protected void saveCache(Entity entity) {
        new SaveObjectTaskAsync(father, entity, getCacheKey(entity)).execute();
    }
    /**
     * 读取缓存文件
     * @param cacheKey
     * @param errorInfo
     */
    protected void readCacheData(String cacheKey,String errorInfo) {
        cancelReadCache();
        mCacheTask = new CacheTaskAsync(father,errorInfo).execute(cacheKey);
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
            return null;
        }
    }
}
