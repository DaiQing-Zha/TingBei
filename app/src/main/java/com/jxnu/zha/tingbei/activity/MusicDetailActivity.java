package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.core.BaseActivity;
import com.jxnu.zha.tingbei.model.Recommend;

public class MusicDetailActivity extends AbstractActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Recommend.ObjEntity objEntity = (Recommend.ObjEntity) intent.getSerializableExtra("obj");
        Log.e("musicDetail","context = " + objEntity.getContext());
        Log.e("musicDetail","title = " + objEntity.getTitle());
        Log.e("musicDetail","openURL = " + objEntity.getOpenUrl());
        Log.e("musicDetail","picPath = " + objEntity.getPicPath());
        Log.e("musicDetail","picPathSmall = " + objEntity.getPicPathSmall());
        Log.e("musicDetail","id = " + objEntity.getId());
        Log.e("musicDetail","type = " + objEntity.getType());
        Log.e("musicDetail","releaseId = " + objEntity.getMusicListReleaseId());
    }
}
