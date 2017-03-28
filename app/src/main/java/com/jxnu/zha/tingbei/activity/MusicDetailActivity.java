package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.MusicListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

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
    /**
     * 获取推荐页分组
     */
    StringRequest musicListRelease = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getMusicListReleaseById)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mLoadStatusBox.loadSuccess();
            MusicListRelease musicListRelease = new Gson().fromJson(response,MusicListRelease.class);
            mObjBeanList.addAll(musicListRelease.getObj().getMusicList().getListMusic());
            mMusicListAdapter.notifyDataSetChanged();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("mainError","error = " + error.toString());
            mLoadStatusBox.loadFailed(getErrorStyle(error.toString()));
            showSnackBarMsg(EAlertStyle.ALERT,getVolleyErrorMessage(error.toString()));
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            map.put("id",mReleaseId);
            map.put("position","1");
            return map;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Recommend.ObjEntity objEntity = (Recommend.ObjEntity) intent.getSerializableExtra("obj");
        mReleaseId = objEntity.getMusicListReleaseId();
        mPicPath = objEntity.getPicPath();
        ImageManager.getInstance().displayImage(mPicPath, mImgTopBg,
                ImageManager.getNewsHeadOptions());
        mLoadStatusBox.setOnClickListener(this);
        mObjBeanList = new ArrayList<>();
        mMusicListAdapter = new MusicListAdapter(this,mObjBeanList);
        mLstMusicList.setAdapter(mMusicListAdapter);
        getMusicListRelease();
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
        mRQueue.add(musicListRelease);
    }
}
