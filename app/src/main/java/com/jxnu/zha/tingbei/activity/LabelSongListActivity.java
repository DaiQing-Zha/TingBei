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
import com.jxnu.zha.tingbei.adapter.LabelSongListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.LabelSongList;
import com.jxnu.zha.tingbei.model.MusicListNoRing;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/6 0006.
 */
public class LabelSongListActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.lst_songList)
    ListView mLstSong;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private List<LabelSongList.ObjBean> mLabelSongLst;
    private LabelSongListAdapter mSongListAdapter;
    private String labelId;
    private String labelName;
    private String TAG = "LabelSongActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_song_list);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            labelId = bundle.getString("labelid");
            labelName = bundle.getString("labelName");
        }
        setTitle(labelName);
        mLoadStatusBox.setOnClickListener(this);
        mLabelSongLst = new ArrayList<>();
        mSongListAdapter = new LabelSongListAdapter(this,mLabelSongLst);
        mLstSong.setAdapter(mSongListAdapter);
        mLstSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("mainResponse","response1 = " + mLabelSongLst.get(position).getId());
                Log.e("mainResponse","response2 = " + mLabelSongLst.get(position).getTitle());
                Intent intent1 = new Intent(LabelSongListActivity.this, MusicListNoRingActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("id",mLabelSongLst.get(position).getId());
                bundle1.putString("name",mLabelSongLst.get(position).getTitle());
                intent1.putExtra("bundle",bundle1);
                startActivity(intent1);
//                LabelSongList.ObjBean objBean = mLabelSongLst.get(position);
//                addMusicToList(objBean);
            }
        });
        getSongList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loadStatusBox:
                getSongList();
                break;
        }
    }

    private void getSongList(){
        mLoadStatusBox.loading();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                map.put("labelid",labelId);
                final String response = HttpTools.httpPost(RoutConstant.getSongListByLabelId,map);
                Log.e("mainResponse","response = " + response);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LabelSongList labelSongList = new Gson().fromJson(response,LabelSongList.class);
                                mLoadStatusBox.loadSuccess();
                                mLabelSongLst.addAll(labelSongList.getObj());
                                mSongListAdapter.notifyDataSetChanged();
                                saveCache(labelSongList);
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
        return (LabelSongList)serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getSongListByLabelId.replace("/","_") + HttpTools.APP_ID + labelId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        LabelSongList labelSongList = (LabelSongList) entity;
        mLabelSongLst.addAll(labelSongList.getObj());
        mSongListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT, getHttpErrorMessage(errorInfo));
    }

    /**
     * 添加一首歌曲
     * @param objBean
     */
    private void addMusicToList(LabelSongList.ObjBean objBean){
        Mp3Info mp3Info = new Mp3Info();
        mp3Info.setMusicId(objBean.getId());
        mp3Info.setMusicName(objBean.getName());
        mp3Info.setMusicUrl(objBean.getOpenUrl());
        mp3Info.setSingerName("未知歌手");
        mp3Info.setMusicPicPath(objBean.getPicPath());
        mp3Info.setSingerPicPath(objBean.getPicPath());
        musicIBind.addMusicPlayList(mp3Info);
    }
}
