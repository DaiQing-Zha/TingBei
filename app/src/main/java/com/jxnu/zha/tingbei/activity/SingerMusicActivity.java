package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.SingerMusicAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.Music;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/5.
 * email:13767191284@163.com
 * description:
 */
public class SingerMusicActivity extends AbstractActivity implements View.OnClickListener{
    @BindView(R.id.lst_musicList)
    ListView mLstMusic;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private List<Music.ObjEntity> mMusicLst;
    private SingerMusicAdapter mSingerMusicAdapter;
    private String singerId;
    private String singerName;
    private String TAG = "SingerMusicActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_music);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            singerId = bundle.getString("singerId");
            singerName = bundle.getString("singerName");
        }
        setTitle(singerName);
        mLoadStatusBox.setOnClickListener(this);
        mMusicLst = new ArrayList<>();
        mSingerMusicAdapter = new SingerMusicAdapter(this,mMusicLst);
        mLstMusic.setAdapter(mSingerMusicAdapter);
        mLstMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SingerMusicActivity.this, RingInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("musicId",mMusicLst.get(position).getId());
                bundle.putString("musicName",mMusicLst.get(position).getName());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
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
        return RoutConstant.getMusicBySingerId.replace("/","_") + HttpTools.APP_ID + singerId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        Music music = (Music) entity;
        mMusicLst.addAll(music.getObj());
        mSingerMusicAdapter.notifyDataSetChanged();
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
                map.put("appid",HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                map.put("id",singerId);
                final String response = HttpTools.httpPost(RoutConstant.getMusicBySingerId,map);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Music music = new Gson().fromJson(response,Music.class);
                                mLoadStatusBox.loadSuccess();
                                mMusicLst.addAll(music.getObj());
                                mSingerMusicAdapter.notifyDataSetChanged();
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