package com.jxnu.zha.tingbei.fragment;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.util.DeviceUtil;
import com.jxnu.zha.qinglibrary.widget.RefreshLayout;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.AutoLoopViewPager;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.CirclePageIndicator;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.activity.MusicDetailActivity;
import com.jxnu.zha.tingbei.adapter.SingerTypesAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.RadioList;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.model.RecommendGroup;
import com.jxnu.zha.tingbei.model.SingerTypes;
import com.jxnu.zha.tingbei.model.SongLabel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:精选
 */
public class HandPickFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener{

    private RefreshLayout mRfContent;
    private AutoLoopViewPager mLoopView;
    private AutoLoopViewAdapter mAdpGallery;
    private CirclePageIndicator mCirclePageIndicator;
    @BindView(R.id.tv_singer)
    TextView mTvSinger;
    @BindView(R.id.gridView_singer)
    GridView mGridViewSinger;
    private List<SingerTypes.ObjBean> singerLst;
    private SingerTypesAdapter singerTypesAdapter;
    private String mIndexTopId;
    private static String TAG = "HandPickFragment";
    /**
     * 获取推荐页分组
     */
    StringRequest requestRecommendGroup = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getRecommendGroupOnInter)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e(TAG,"response = " + response);
            RecommendGroup recommendGroup = new Gson().fromJson(response,RecommendGroup.class);
            saveCacheFile(recommendGroup,getRecommendGroupCacheKey());
            mIndexTopId = recommendGroup.getObj().get(1).getId();
            mRQueue.add(requestRecommend);
            mRfContent.setRefreshing(false);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mRfContent.setRefreshing(false);
            readCacheFile(getRecommendGroupCacheKey());
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            return map;
        }
    };
    /**
     * 根据分组id获取推荐页
     */
    StringRequest requestRecommend = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getRecommendByGroupId)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e(TAG,"response = " + response);
            Recommend recommend = new Gson().fromJson(response,Recommend.class);
            showAutoLoopViewPage(recommend.getObj());
            saveCacheFile(recommend,getRecommendCacheKey());
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            readCacheFile(getRecommendCacheKey());
            Log.e(TAG,"getRecommend error ");
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            map.put("groupid",mIndexTopId);
            return map;
        }
    };
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hand_pick,container,false);
    }

    @Override
    public void builderView(View rootView) {

        mRQueue.add(requestRecommendGroup);//http://blog.csdn.net/baidu_31093133/article/details/51525113?locationNum=7&fps=1 q1
        mRfContent = findWidget(rootView,R.id.rf_content);
        mLoopView = findWidget(rootView,R.id.autoLoop);
        mCirclePageIndicator = findWidget(rootView,R.id.indy);
        singerLst = new ArrayList<>();
        singerTypesAdapter = new SingerTypesAdapter(father,singerLst);
        mGridViewSinger.setAdapter(singerTypesAdapter);
        mRfContent.setOnRefreshListener(this);
        getSingerTypes();
    }
    @Override
    public void onRefresh() {
        mRQueue.add(requestRecommendGroup);
    }

    /**
     * 显示首页轮播图片
     * @param data
     */
    private void showAutoLoopViewPage(final List<Recommend.ObjEntity> data){

        // 固定ViewPager高度为屏幕宽度的一半
        mLoopView.getLayoutParams().height = (int) (DeviceUtil.getDeviceWidth(father) / 2.5);
        mAdpGallery = new AutoLoopViewAdapter(father, data);
        mLoopView.setAdapter(mAdpGallery);
        mLoopView.setBoundaryCaching(true);
        mLoopView.setAutoScrollDurationFactor(10d);
        mLoopView.setInterval(3000);
        mLoopView.startAutoScroll();
        mCirclePageIndicator.setCentered(true);
        mCirclePageIndicator.setViewPager(mLoopView);
        mLoopView.setOnItemClickListener(new AutoLoopViewPager.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Recommend.ObjEntity objEntity = data.get(position);
                Intent intent = new Intent(father, MusicDetailActivity.class);
                intent.putExtra("obj",objEntity);
                father.startActivity(intent);
            }
        });
    }

    @Override
    protected Entity readData(Serializable serializable, String cacheKey) {
        if (serializable instanceof RecommendGroup){
            RecommendGroup recommendGroup = (RecommendGroup) serializable;
            recommendGroup.setCacheKey(cacheKey);
            return recommendGroup;
        }
        if (serializable instanceof Recommend){
            Recommend recommend = (Recommend) serializable;
            recommend.setCacheKey(cacheKey);
            return recommend;
        }
        if (serializable instanceof SingerTypes){
            SingerTypes singerTypes = (SingerTypes) serializable;
            singerTypes.setCacheKey(cacheKey);
            return singerTypes;
        }
        Entity entity = new Entity();
        entity.setCacheKey(cacheKey);
        entity.setHaveCache(false);
        return entity;
    }

    @Override
    protected void executeOnLoadFileSuccess(Entity entity) {
        if (entity instanceof RecommendGroup){
            RecommendGroup recommendGroup = (RecommendGroup) entity;
            mIndexTopId = recommendGroup.getObj().get(1).getId();
            mRQueue.add(requestRecommend);
            Log.e(TAG,"recommendGroup---------------------------------");
        }
        if (entity instanceof Recommend){
            Recommend recommend = (Recommend) entity;
            showAutoLoopViewPage(recommend.getObj());
            Log.e(TAG,"Recommend---------------------------------");
        }
        if (entity instanceof SingerTypes){
            SingerTypes singerTypes = (SingerTypes) entity;
            singerLst.addAll(singerTypes.getObj());
            singerTypesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void executeOnLoadFileFailure(String cacheKey) {
        if (cacheKey.equals(getRecommendGroupCacheKey())){
        }
        if (cacheKey.equals(getRecommendCacheKey())){
        }
    }
    private void getSingerTypes(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                String source = HttpTools.httpPost(RoutConstant.getSingerTypesOnInter,map);
                try{
                    SingerTypes singerTypes = new Gson().fromJson(source,SingerTypes.class);
                    saveCacheFile(singerTypes,getSingerTypesCacheKey());
                    singerLst.addAll(singerTypes.getObj());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            singerTypesAdapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception e){
                    readCacheFile(getSingerTypesCacheKey());
                }
                Log.e(TAG,"source = " + source);
            }
        });
    }
    /**
     * 轮播图片适配器
     */
    private static class AutoLoopViewAdapter extends PagerAdapter {
        private int  count = 100;
        private Queue<ImageView> views;
        private List<Recommend.ObjEntity> listRecommend;
        private Context context;
        public AutoLoopViewAdapter(Context ct, List<Recommend.ObjEntity> listData){
            views = new LinkedList<>();
            listRecommend = listData;
            context = ct;
        }
        @Override
        public int getCount() {
            return listRecommend.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView  image = views.poll();

            if(image == null){
                image  = new ImageView(context);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT));
                image.setId(count ++);
            }
            Log.e(TAG,"url = " + listRecommend.get(position).getPicPathSmall());
            ImageManager.getInstance().displayImage(listRecommend.get(position).getPicPathSmall(), image,
                    ImageManager.getNewsHeadOptions());
            container.addView(image);
            return image;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView image = (ImageView) object;
            views.add(image);
            container.removeView(image);
        }
    }

    /**
     * 获取recommendGroup的缓存key
     * @return
     */
    private String getRecommendGroupCacheKey(){
        return RoutConstant.getRecommendGroupOnInter.replace("/","_") + HttpTools.APP_ID;
    }
    /**
     * 获取recommend的缓存key
     * @return
     */
    private String getRecommendCacheKey(){
        return RoutConstant.getRecommendByGroupId.replace("/","_") + HttpTools.APP_ID;
    }

    /**
     * 获取歌手类型
     * @return
     */
    private String getSingerTypesCacheKey(){
        return RoutConstant.getSingerTypesOnInter.replace("/","_") + HttpTools.APP_ID;
    }
}
