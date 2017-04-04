package com.jxnu.zha.tingbei.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.RadioListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.RadioList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:
 */
public class RadioFragment extends BaseFragment{
    @BindView(R.id.gridView_hotRecommend)
    GridView mGvHotRecommend;
    @BindView(R.id.gridView_labelRadio)
    GridView mGvLabelRadio;
    RadioListAdapter mHotRecommendRadioAdapter, mLabelRadioAdapter;
    List<RadioList.ObjEntity> mHotRecommendRadioList,mLabelRadioList;
    private String TAG = "RADIO";
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_radio,container,false);
    }

    @Override
    public void builderView(View rootView) {
        mHotRecommendRadioList = new ArrayList<>();
        mLabelRadioList = new ArrayList<>();
        mHotRecommendRadioAdapter = new RadioListAdapter(father, mHotRecommendRadioList);
        mLabelRadioAdapter = new RadioListAdapter(father, mLabelRadioList);
        mGvHotRecommend.setAdapter(mHotRecommendRadioAdapter);
        mGvLabelRadio.setAdapter(mLabelRadioAdapter);
        getHotRecommendRadio();
        getLabelRadio();
    }

    /**
     * 获取热门推荐电台
     */
    private void getHotRecommendRadio(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getRadioListByRecommend,map);
                try{
                    RadioList radioList = new Gson().fromJson(source,RadioList.class);
                    radioList.setRadioListType(0);
                    mHotRecommendRadioList.addAll(radioList.getObj());
                    saveCacheFile(radioList,getHotRecommendRadioCacheKey());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mHotRecommendRadioAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getHotRecommendRadioCacheKey());
                }
                Log.e(TAG,"source = " + source);
            }
        });
    }

    /**
     * 获取标签电台
     */
    private void getLabelRadio(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getRadioListByLabelId,map);
                try{
                    RadioList radioList = new Gson().fromJson(source,RadioList.class);
                    radioList.setRadioListType(1);
                    mLabelRadioList.addAll(radioList.getObj());
                    saveCacheFile(radioList,getLabelRadioCacheKey());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLabelRadioAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getLabelRadioCacheKey());
                }
                Log.e(TAG,"source2 = " + source);
            }
        });
    }

    @Override
    protected Entity readData(Serializable serializable, String cacheKey) {
        if (serializable instanceof RadioList) {
            RadioList radioList = (RadioList) serializable;
            radioList.setCacheKey(cacheKey);
            return radioList;
        }
        Entity entity = new Entity();
        entity.setCacheKey(cacheKey);
        entity.setHaveCache(false);
        return entity;
    }

    @Override
    protected void executeOnLoadFileSuccess(Entity entity) {
        super.executeOnLoadFileSuccess(entity);
        if (entity instanceof RadioList){
            if (((RadioList)entity).getRadioListType() == 0){
                RadioList radioList = (RadioList) entity;
                mHotRecommendRadioList.addAll(radioList.getObj());
                mHotRecommendRadioAdapter.notifyDataSetChanged();
            }
            if (((RadioList)entity).getRadioListType() == 1){
                RadioList radioList = (RadioList) entity;
                mLabelRadioList.addAll(radioList.getObj());
                mLabelRadioAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void executeOnLoadFileFailure(String cacheKey) {
        super.executeOnLoadFileFailure(cacheKey);
    }

    private String getHotRecommendRadioCacheKey(){
        return RoutConstant.getRadioListByRecommend.replace("/","_") + HttpTools.APP_ID;
    }
    private String getLabelRadioCacheKey(){
        return RoutConstant.getRadioListByLabelId.replace("/","_") + HttpTools.APP_ID;
    }
}
