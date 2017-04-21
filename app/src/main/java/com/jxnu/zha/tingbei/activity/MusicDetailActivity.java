package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.MusicListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MusicDetailActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    @BindView(R.id.lst_musicList)
    ListView mLstMusicList;
    final String TAG = "musicDetail";
    private String mReleaseId = "";
    private String mPicPath = "";
    MusicListAdapter mMusicListAdapter;
    List<MusicListRelease.ObjBean.MusicListBean.ListMusicBean> mObjBeanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
    }
    @Override
    protected void init() {
        Intent intent = getIntent();
        Recommend.ObjEntity objEntity = (Recommend.ObjEntity) intent.getSerializableExtra("obj");
        setTitle(objEntity.getTitle());
        mReleaseId = objEntity.getMusicListReleaseId();
        mPicPath = objEntity.getPicPath();
        ImageManager.getInstance().displayImage(mPicPath, mImgTopBg,
                ImageManager.getBackPictureOptions());
        mLoadStatusBox.setOnClickListener(this);
        mObjBeanList = new ArrayList<>();
        mMusicListAdapter = new MusicListAdapter(this,mObjBeanList);
        mLstMusicList.setAdapter(mMusicListAdapter);
        getMusicListRelease();
        mLstMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicListRelease.ObjBean.MusicListBean.ListMusicBean musicBean = mObjBeanList.get(position);
                Mp3Info mp3Info = new Mp3Info();
                mp3Info.setMusicId(musicBean.getId());
                mp3Info.setMusicName(musicBean.getName());
                mp3Info.setMusicUrl(musicBean.getMusicPath());
                mp3Info.setSingerName(musicBean.getSingerName());
                mp3Info.setMusicPicPath(musicBean.getMusicPicPath());
                mp3Info.setSingerPicPath(musicBean.getMusicSingerPicPath());
                musicIBind.addMusicPlayList(mp3Info);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loadStatusBox:
                Log.e(TAG,"---------loadErrorBn---------");
                getMusicListRelease();
                break;
        }
    }

    /**
     * 根据ID获取发布的音乐集
     */
    private void getMusicListRelease(){
        mLoadStatusBox.loading();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("id",mReleaseId);
                map.put("position","1");
                final String response = HttpTools.httpPost(RoutConstant.getMusicListReleaseById,map);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MusicListRelease musicListRelease = new Gson().fromJson(response,MusicListRelease.class);
                                mLoadStatusBox.loadSuccess();
                                mObjBeanList.addAll(musicListRelease.getObj().getMusicList().getListMusic());
                                mMusicListAdapter.notifyDataSetChanged();
                                saveCache(musicListRelease);
                            }
                        });
                    }catch (Exception e){
                        readCacheData(getCacheKey(),response);
                    }
                }else{
                    readCacheData(getCacheKey(),response);
                }

            }
        });
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (MusicListRelease) serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getMusicListReleaseById.replace("/","_") + HttpTools.APP_ID + mReleaseId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        MusicListRelease musicListRelease = (MusicListRelease) entity;
        mObjBeanList.addAll(musicListRelease.getObj().getMusicList().getListMusic());
        mMusicListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT, getHttpErrorMessage(errorInfo));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
