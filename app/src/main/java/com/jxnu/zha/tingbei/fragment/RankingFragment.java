package com.jxnu.zha.tingbei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.activity.BangDetailActivity;
import com.jxnu.zha.tingbei.adapter.RecommendBandAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.BangList;
import com.jxnu.zha.tingbei.model.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:排行
 */
public class RankingFragment extends BaseFragment {

    @BindView(R.id.lst_bandList1)
    ListView mLstRecommendBang;
    @BindView(R.id.lst_bandList2)
    ListView mLstNoRecommendBang;
    @BindView(R.id.lst_bandList3)
    ListView mLstLabelBang;
    private RecommendBandAdapter mRecommendBandAdapter,mNoRecommendBandAdapter,mLabelBandAdapter;
    private List<BangList.ObjEntity> mRecommendBangList,mNoRecommendBangList,mLabelBangList;
    private String TAG = "RANKING";
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking,container,false);
    }

    @Override
    public void builderView(View rootView) {
        mRecommendBangList = new ArrayList<>();
        mNoRecommendBangList = new ArrayList<>();
        mLabelBangList = new ArrayList<>();
        mRecommendBandAdapter = new RecommendBandAdapter(father, mRecommendBangList);
        mNoRecommendBandAdapter = new RecommendBandAdapter(father,mNoRecommendBangList);
        mLabelBandAdapter = new RecommendBandAdapter(father,mLabelBangList);
        mLstRecommendBang.setAdapter(mRecommendBandAdapter);
        mLstNoRecommendBang.setAdapter(mNoRecommendBandAdapter);
        mLstLabelBang.setAdapter(mLabelBandAdapter);
        mLstRecommendBang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BangList.ObjEntity objEntity = mRecommendBangList.get(i);
                Log.e(TAG,"-----------------------tuijian = " + objEntity.toString());
                Intent intent = new Intent(father, BangDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bang",objEntity);;
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mLstLabelBang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BangList.ObjEntity objEntity = mRecommendBangList.get(i);
                Log.e(TAG,"-----------------------tuijian = " + objEntity.toString());
                Intent intent = new Intent(father, BangDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bang",objEntity);;
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mLstNoRecommendBang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BangList.ObjEntity objEntity = mRecommendBangList.get(i);
                Log.e(TAG,"-----------------------tuijian = " + objEntity.toString());
                Intent intent = new Intent(father, BangDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bang",objEntity);;
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        getRecommendBand();
        getNoRecommendBand();
        getLabelBand();
    }

    /**
     * 推荐榜单
     */
    private void getRecommendBand(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getBangListByRecommend,map);
                try{
                    BangList bangList = new Gson().fromJson(source, BangList.class);
                    bangList.setBangListType(0);
                    mRecommendBangList.addAll(bangList.getObj());
                    saveCacheFile(bangList,getRecommendBandCacheKey());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecommendBandAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getRecommendBandCacheKey());
                }

                Log.e(TAG,"source1 = " + source);
            }
        });
    }

    /**
     * 非推荐榜单
     */
    private void getNoRecommendBand(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getBangListByNoRecommend,map);
                try{
                    BangList bangList = new Gson().fromJson(source, BangList.class);
                    bangList.setBangListType(1);
                    mNoRecommendBangList.addAll(bangList.getObj());
                    saveCacheFile(bangList,getNoRecommendBandCacheKey());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mNoRecommendBandAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getNoRecommendBandCacheKey());
                }

                Log.e(TAG,"source1 = " + source);
            }
        });
    }

    /**
     * 标签榜单
     */
    private void getLabelBand(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getBangListByLabelId,map);
                try{
                    BangList bangList = new Gson().fromJson(source, BangList.class);
                    bangList.setBangListType(2);
                    mLabelBangList.addAll(bangList.getObj());
                    saveCacheFile(bangList,getLabelBandCacheKey());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLabelBandAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getLabelBandCacheKey());
                }

                Log.e(TAG,"source1 = " + source);
            }
        });
    }
    @Override
    protected Entity readData(Serializable serializable, String cacheKey) {
        if (serializable instanceof BangList) {
            BangList bangList = (BangList) serializable;
            bangList.setCacheKey(cacheKey);
            return bangList;
        }
        Entity entity = new Entity();
        entity.setCacheKey(cacheKey);
        entity.setHaveCache(false);
        return entity;
    }

    @Override
    protected void executeOnLoadFileSuccess(Entity entity) {
        super.executeOnLoadFileSuccess(entity);
        if (entity instanceof BangList){
            int bangListType = ((BangList)entity).getBangListType();
            if (bangListType == 0){
                BangList bangList = (BangList) entity;
                mRecommendBangList.addAll(bangList.getObj());
                mRecommendBandAdapter.notifyDataSetChanged();
            }
            if (bangListType == 1){
                BangList bangList = (BangList) entity;
                mNoRecommendBangList.addAll(bangList.getObj());
                mNoRecommendBandAdapter.notifyDataSetChanged();
            }
            if (bangListType == 2){
                BangList bangList = (BangList) entity;
                mLabelBangList.addAll(bangList.getObj());
                mLabelBandAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void executeOnLoadFileFailure(String cacheKey) {
        super.executeOnLoadFileFailure(cacheKey);
    }

    private String getRecommendBandCacheKey(){
        return RoutConstant.getBangListByRecommend.replace("/","_") + HttpTools.APP_ID;
    }
    private String getNoRecommendBandCacheKey(){
        return RoutConstant.getBangListByNoRecommend.replace("/","_") + HttpTools.APP_ID;
    }
    private String getLabelBandCacheKey(){
        return RoutConstant.getBangListByLabelId.replace("/","_") + HttpTools.APP_ID;
    }
}
