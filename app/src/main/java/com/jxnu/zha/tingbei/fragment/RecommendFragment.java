package com.jxnu.zha.tingbei.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.AutoLoopViewPager;
import com.jxnu.zha.qinglibrary.widget.pagerindicator.CirclePageIndicator;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.activity.LabelSongListActivity;
import com.jxnu.zha.tingbei.activity.RecommendSongActivity;
import com.jxnu.zha.tingbei.adapter.HotRecommendAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.core.MainSubFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.SongLabel;
import com.jxnu.zha.tingbei.model.SongList;
import com.jxnu.zha.tingbei.widgets.HorizontalListView;

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
 * description:推荐
 */
public class RecommendFragment extends MainSubFragment {
    @BindView(R.id.lstHotRecommend)
    ListView mLstHotRecommend;
    HotRecommendAdapter mHotRecommendAdapter;
    @BindView(R.id.autoLoop)
    AutoLoopViewPager mLoopView;
    @BindView(R.id.indy)
    CirclePageIndicator mCirclePageIndicator;
    private SongList songList;
    private AutoLoopViewAdapter mAdpGallery;
    private int mSongListRow = 1;
    private int mSongListPage = 1;

    List<SongLabel.ObjEntity> lstHotRecommend;
    private String TAG = "RECOMMEND_";
    private final int mHotRecommendNum = 10;
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend,container,false);
    }

    @Override
    public void builderView(View rootView) {
        lstHotRecommend = new ArrayList<>();
        mHotRecommendAdapter = new HotRecommendAdapter(father, lstHotRecommend);
        mLstHotRecommend.setAdapter(mHotRecommendAdapter);
        mLstHotRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(father, LabelSongListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("labelid",lstHotRecommend.get(i).getId());
                bundle.putString("labelName",lstHotRecommend.get(i).getName());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        initFlag = true;
        lazyLoad();
    }

    private void getDefaultRecommend(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("row", mSongListRow);
                map.put("page", mSongListPage);
                String source = HttpTools.httpPost(RoutConstant.getSongListByRecommend,map);
                try{
                    songList = new Gson().fromJson(source,SongList.class);
                    Log.e(TAG,"------------songList = " + songList.getObj().size());
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showAutoLoopViewPage(songList.getObj().get(0).getListMusic());
                            saveCacheFile(songList,getSongListCacheKey());
                        }
                    });
                }catch (Exception e){
                    Log.e(TAG,"------------exception = " + e.toString());
                    readCacheFile(getSongListCacheKey());
                }
            }
        });
    }
    //getSongLabel --> getSongListByLabelId
    private void getHotRecommend(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                String source = HttpTools.httpPost(RoutConstant.getSongLabel,map);
                try{
                    final SongLabel songLabel = new Gson().fromJson(source,SongLabel.class);
                    lstHotRecommend.clear();
                    if (songLabel.getCode() == 0){
                        for (int i =0 ; i < songLabel.getObj().size() && i < mHotRecommendNum;i ++){  //最多添加十组
                            lstHotRecommend.add(songLabel.getObj().get(i));
                        }
                        saveCacheFile(songLabel,getSongLabelCacheKey());
                        refreshHotRecommend();
                    }else{
                        readCacheFile(getSongLabelCacheKey());
                    }
                }catch (Exception e){
                    readCacheFile(getSongLabelCacheKey());
                }
            }
        });
    }

    private void refreshHotRecommend(){
        father.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHotRecommendAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected Entity readData(Serializable serializable,String cacheKey) {
        if (serializable instanceof SongLabel){
            SongLabel songLabel = (SongLabel) serializable;
            songLabel.setCacheKey(cacheKey);
//            Log.e("mainQWE","readData = songLabel");
            return songLabel;
        }
        if (serializable instanceof SongList){
            SongList songList = (SongList) serializable;
            songList.setCacheKey(cacheKey);
            return songList;
        }
        Entity entity = new Entity();
        entity.setCacheKey(cacheKey);
        entity.setHaveCache(false);
        return entity;
    }
    @Override
    protected void executeOnLoadFileSuccess(Entity entity) {
        super.executeOnLoadFileSuccess(entity);
        if (entity instanceof SongLabel){
            SongLabel songLabel = (SongLabel) entity;
            lstHotRecommend.clear();
            for (int i =0 ; i < songLabel.getObj().size() && i < mHotRecommendNum;i ++){  //最多添加十组
                lstHotRecommend.add(songLabel.getObj().get(i));
            }
            refreshHotRecommend();
        }
        if (entity instanceof SongList){
            SongList songList = (SongList) entity;
            showAutoLoopViewPage(songList.getObj().get(0).getListMusic());
        }
    }

    @Override
    protected void executeOnLoadFileFailure(String cacheKey) {
        super.executeOnLoadFileFailure(cacheKey);
        if (cacheKey.equals(getSongLabelCacheKey())){
            lstHotRecommend.clear();
            for (int i = 0; i < mHotRecommendNum; i ++){
                SongLabel.ObjEntity objEntity = new SongLabel.ObjEntity();
                objEntity.setId("0");
                objEntity.setName("未知");
                lstHotRecommend.add(objEntity);
            }
            mHotRecommendAdapter.notifyDataSetChanged();
        }
        if (cacheKey.equals(getSongListCacheKey())){
//            readCacheFile(cacheKey);
        }
    }
    /**
     * 显示首页轮播图片
     * @param listMusicEntity
     */
    private void showAutoLoopViewPage(final List<SongList.ObjEntity.ListMusicEntity> listMusicEntity){
        if (!isAdded()) return;
        // 固定ViewPager高度为屏幕宽度的一半
//        mLoopView.getLayoutParams().height = (int) (DeviceUtil.getDeviceWidth(father) / 2.5);
        mLoopView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.autoLoopViewPager_height);
        mAdpGallery = new AutoLoopViewAdapter(father, listMusicEntity);
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
                Intent intent = new Intent(father, RecommendSongActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song",listMusicEntity.get(position));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        if(initFlag && mIsVisible){
            getDefaultRecommend();
            getHotRecommend();
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
        private List<SongList.ObjEntity.ListMusicEntity> listMusicEntity;
        private Context context;
        public AutoLoopViewAdapter(Context ct, List<SongList.ObjEntity.ListMusicEntity> listMusicEntity){
            views = new LinkedList<>();
            this.listMusicEntity = listMusicEntity;
            context = ct;
        }
        @Override
        public int getCount() {
            return listMusicEntity.size();
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
            ImageManager.getInstance().displayImage(listMusicEntity.get(position).getMusicPicPath(), image,
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

    /**
     * 获取SongList的缓存key
     * @return
     */
    private String getSongListCacheKey(){
        return RoutConstant.getSongListByRecommend.replace("/","_") + HttpTools.APP_ID;
    }

    /**
     * 获取SongLabel的缓存key
     * @return
     */
    private String getSongLabelCacheKey(){
        return RoutConstant.getSongLabel.replace("/","_") + HttpTools.APP_ID;
    }
}
