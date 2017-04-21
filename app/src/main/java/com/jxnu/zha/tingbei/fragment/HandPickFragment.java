package com.jxnu.zha.tingbei.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.widget.RefreshLayout;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.AutoLoopViewPager;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.CirclePageIndicator;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.activity.MusicDetailActivity;
import com.jxnu.zha.tingbei.activity.TypeSingerActivity;
import com.jxnu.zha.tingbei.adapter.SingerTypesAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.MainSubFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.model.RecommendGroup;
import com.jxnu.zha.tingbei.model.SingerTypes;

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
public class HandPickFragment extends MainSubFragment
        implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rf_content)
    RefreshLayout mRfContent;
    @BindView(R.id.autoLoop)
    AutoLoopViewPager mLoopView;
    @BindView(R.id.indy)
    CirclePageIndicator mCirclePageIndicator;
    @BindView(R.id.lst_singer)
    ListView mLstSingerTypes;
    @BindView(R.id.ll_kk)
    LinearLayout ll_kk;
    private List<SingerTypes.ObjBean> mSingerTypes;
    private AutoLoopViewAdapter mAutoLoopViewAdapter;
    private SingerTypesAdapter mSingerTypesAdapter;
    private String mIndexTopId;
    private static String TAG = "HandPickFragment";

    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hand_pick,container,false);
    }

    @Override
    public void builderView(View rootView) {
        mSingerTypes = new ArrayList<>();
        mSingerTypesAdapter = new SingerTypesAdapter(father, mSingerTypes);
        mLstSingerTypes.setAdapter(mSingerTypesAdapter);
        mRfContent.setOnRefreshListener(this);
        mLstSingerTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(father, TypeSingerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("typeId", mSingerTypes.get(position).getId());
                bundle.putString("typeName", mSingerTypes.get(position).getDicValue());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        mLstSingerTypes.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(mLstSingerTypes != null && mLstSingerTypes.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = mLstSingerTypes.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = mLstSingerTypes.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mRfContent.setEnabled(enable);
            }});
        initFlag = true;
        lazyLoad();
    }
    @Override
    public void onRefresh() {
        getRecommendGroup();
        getSingerTypes();
    }
    /**
     * 获取推荐分组
     */
    private void getRecommendGroup(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                final String response = HttpTools.httpPost(RoutConstant.getRecommendGroupOnInter,map);
                if (HttpTools.checkSource(response)){
                    try{
                        father.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecommendGroup recommendGroup = new Gson().fromJson(response,RecommendGroup.class);
                                saveCacheFile(recommendGroup,getRecommendGroupCacheKey());
                                mIndexTopId = recommendGroup.getObj().get(1).getId();
                                getRecommend();
                                mRfContent.setRefreshing(false);
                            }
                        });
                    }catch (Exception e){
                        readCacheFile(getRecommendGroupCacheKey());
                    }
                }else{
                    readCacheFile(getRecommendGroupCacheKey());
                }
            }
        });
    }

    /**
     * 获取推荐分组
     */
    private void getRecommend(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("groupid",mIndexTopId);
                final String response = HttpTools.httpPost(RoutConstant.getRecommendByGroupId,map);
                if (HttpTools.checkSource(response)){
                    try{
                        father.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Recommend recommend = new Gson().fromJson(response,Recommend.class);
                                showAutoLoopViewPage(recommend.getObj());
                                saveCacheFile(recommend,getRecommendCacheKey());
                            }
                        });
                    }catch (Exception e){
                        readCacheFile(getRecommendCacheKey());
                    }
                }else{
                    readCacheFile(getRecommendCacheKey());
                }
            }
        });
    }

    /**
     * 获取歌手类型
     */
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
                    mSingerTypes.clear();
                    mSingerTypes.addAll(singerTypes.getObj());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSingerTypesAdapter.notifyDataSetChanged();
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
     * 显示首页轮播图片
     * @param data
     */
    private void showAutoLoopViewPage(final List<Recommend.ObjEntity> data){
        if (!isAdded()) return;
        mLoopView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.autoLoopViewPager_height);
        mAutoLoopViewAdapter = new AutoLoopViewAdapter(father, data);
        mLoopView.setAdapter(mAutoLoopViewAdapter);
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
            getRecommend();
//            Log.e(TAG,"recommendGroup---------------------------------");
        }
        if (entity instanceof Recommend){
            Recommend recommend = (Recommend) entity;
            showAutoLoopViewPage(recommend.getObj());
//            Log.e(TAG,"Recommend---------------------------------");
        }
        if (entity instanceof SingerTypes){
            SingerTypes singerTypes = (SingerTypes) entity;
            mSingerTypes.clear();
            mSingerTypes.addAll(singerTypes.getObj());
            mSingerTypesAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void executeOnLoadFileFailure(String cacheKey) {

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

    @Override
    protected void lazyLoad() {
        if(initFlag && mIsVisible){
            getRecommendGroup();
            getSingerTypes();
        }else{
        }
    }

    @Override
    public void onStart() {
        super.onStart();

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
//            Log.e(TAG,"url = " + listRecommend.get(position).getPicPathSmall());
            ImageManager.getInstance().displayImage(listRecommend.get(position).getPicPathSmall(), image,
                    ImageManager.getBackPictureOptions());
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
}
