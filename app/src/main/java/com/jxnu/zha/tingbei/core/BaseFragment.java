package com.jxnu.zha.tingbei.core;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
     * @param cacheKey
     * @return
     */
    protected Entity readData(Serializable serializable,String cacheKey) {
        return null;
    }

    /**
     * 保存缓存文件
     * @param entity
     * @param cacheKey
     */
    protected void saveCacheFile(Entity entity,String cacheKey) {
        new SaveCacheTaskAsync(father, entity, cacheKey).execute();
    }
    /**
     * 读取缓存文件
     * @param cacheKey
     */
    protected void readCacheFile(String cacheKey) {
//        cancelReadCache();
        mCacheTask = new ReadCacheTaskAsync(father).execute(cacheKey);
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
    protected void executeOnLoadFileSuccess(Entity entity) {}

    /**
     * 数据加载失败
     * @param cacheKey
     */
    protected void executeOnLoadFileFailure(String cacheKey) {}
    /**
     * 读取缓存文件
     */
    private class ReadCacheTaskAsync extends AsyncTask<String,Void,Entity>{
        private final WeakReference<Context> mContext;
        public ReadCacheTaskAsync(Context context) {
            Log.e("mainmainQWE","创建读");
            this.mContext = new WeakReference<>(context);
        }
        @Override
        protected Entity doInBackground(String... params) {
            if (mContext != null){
                Serializable serializable = CacheManager.readObject(mContext.get(),params[0]);
                if (serializable == null) {
                    Entity entity = new Entity();
                    entity.setCacheKey(params[0]);
                    entity.setHaveCache(false);
                    Log.e("mainmainQWE","返回空");
                    return entity;
                }else{
                    Log.e("mainmainQWE","返回读");
                    return readData(serializable,params[0]);
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Entity entity) {
            super.onPostExecute(entity);
            if (entity.isHaveCache()){
                executeOnLoadFileSuccess(entity);
            }else{
                executeOnLoadFileFailure(entity.getCacheKey());
            }
        }
    }

    /**
     * 保存对象异步类
     */
    protected class SaveCacheTaskAsync extends AsyncTask<Void,Void,Void> {
        private final WeakReference<Context> mContext;
        private final Serializable serializable;
        private final String key;
        public SaveCacheTaskAsync(Context context, Serializable serializable, String key) {
            mContext = new WeakReference<>(context);
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
