package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MusicDetailActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    final String TAG = "musicDetail";
    private String mReleaseId = "";
    private String mPicPath = "";
    /**
     * 获取推荐页分组
     */
    StringRequest musicListRelease = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getMusicListReleaseById)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mLoadStatusBox.loadSuccess();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mLoadStatusBox.loadFailed();
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
       getMusicListRelease();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loadStatusBox:
                Log.e(TAG,"---------loadErrorBn---------");
//                getMusicListRelease();
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
