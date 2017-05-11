package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.MusicListNoRingAdapter;
import com.jxnu.zha.tingbei.adapter.SingerMusicAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.Music;
import com.jxnu.zha.tingbei.model.MusicListNoRing;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/5/11.
 * email:13767191284@163.com
 * description:
 */
public class MusicListNoRingActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.lst_musicList)
    ListView mLstMusic;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private List<MusicListNoRing.ObjEntity.MusicListEntity.ListMusicEntity> mMusicLst;
    private MusicListNoRingAdapter mMusicListNoRingAdapter;
    private String TAG = "MusicListNoRingActivity";

    private String mId;
    private String mTitleName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_no_ring);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            mId = bundle.getString("id");
            mTitleName = bundle.getString("name");
        }
        Log.e(TAG,"id = " + mId + " mTitleName = " + mTitleName);
        setTitle(mTitleName);
        mLoadStatusBox.setOnClickListener(this);
        mMusicLst = new ArrayList<>();
        mMusicListNoRingAdapter = new MusicListNoRingAdapter(this,mMusicLst);
        mLstMusic.setAdapter(mMusicListNoRingAdapter);
        mLstMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicListNoRing.ObjEntity.MusicListEntity.ListMusicEntity musicBean = mMusicLst.get(position);
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
        getMusic();
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (Music)serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getMusicListReleaseByIdNoRingInfo.replace("/","_") + HttpTools.APP_ID + mId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        MusicListNoRing music = (MusicListNoRing) entity;
        mMusicLst.addAll(music.getObj().getMusicList().getListMusic());
        mMusicListNoRingAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT, getHttpErrorMessage(errorInfo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loadStatusBox:
                getMusic();
                break;
        }
    }

    private void getMusic(){
        mLoadStatusBox.loading();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("id",mId);
                final String response = HttpTools.httpPost(RoutConstant.getMusicListReleaseByIdNoRingInfo,map);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MusicListNoRing music = new Gson().fromJson(response,MusicListNoRing.class);
                                mLoadStatusBox.loadSuccess();
                                mMusicLst.addAll(music.getObj().getMusicList().getListMusic());
                                mMusicListNoRingAdapter.notifyDataSetChanged();
                                saveCache(music);
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
}
