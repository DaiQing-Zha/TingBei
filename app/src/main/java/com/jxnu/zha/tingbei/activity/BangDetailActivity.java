package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.BangDetailAdapter;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.BangList;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.music.util.Player;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/8 0008.
 */
public class BangDetailActivity extends AbstractActivity{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.lst_bangList)
    ListView mLstBangList;
    final String TAG = "BangDetailActivity";
    BangDetailAdapter bangDetailAdapter;
    List<BangList.ObjEntity.ListMusicEntity> listMusicEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_detail);
    }

    @Override
    protected void init() {
        listMusicEntity = new ArrayList<>();
        bangDetailAdapter = new BangDetailAdapter(this,listMusicEntity);
        mLstBangList.setAdapter(bangDetailAdapter);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        Log.e(TAG,"-------------- bundle bundle");
        if (bundle != null){
            Log.e(TAG,"-------------- bundle is not null");
            BangList.ObjEntity objEntity = (BangList.ObjEntity) bundle.getSerializable("bang");
            setTitle(objEntity.getContext());
            if (objEntity != null){
                Log.e(TAG,"--------------" + objEntity.toString());
                ImageManager.getInstance().displayImage(objEntity.getPicPath(), mImgTopBg,
                        ImageManager.getUserImageOptions());
                listMusicEntity.addAll(objEntity.getListMusic());
                bangDetailAdapter.notifyDataSetChanged();
            }
        }
        mLstBangList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BangList.ObjEntity.ListMusicEntity musicEntity = listMusicEntity.get(position);
                Mp3Info mp3Info = new Mp3Info();
                mp3Info.setMusicId(musicEntity.getId());
                mp3Info.setMusicName(musicEntity.getName());
                mp3Info.setMusicUrl(musicEntity.getMusicPath());
                mp3Info.setSingerName(musicEntity.getSingerName());
                mp3Info.setMusicPicPath(musicEntity.getMusicPicPath());
                mp3Info.setSingerPicPath(musicEntity.getMusicSingerPicPath());
                musicIBind.addMusicPlayList(mp3Info);
            }
        });

    }
}
