package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.SingerAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.model.Singer;
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
public class SingerActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.lst_singerList)
    ListView mLstSinger;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private List<Singer.ObjEntity> mSingerLst;
    private SingerAdapter mSingerAdapter;
    private String typeId = "";
    /**
     * 获取推荐页分组
     */
    StringRequest singerList = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getSingerBySingerType)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mLoadStatusBox.loadSuccess();
            Singer singer = new Gson().fromJson(response,Singer.class);
            mSingerLst.addAll(singer.getObj());
            mSingerAdapter.notifyDataSetChanged();
            saveCache(singer);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("mainError","error = " + error.toString());
            readCacheData(getCacheKey(),error.toString());
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            map.put("row","1");
            map.put("page","1");
            map.put("typeid",typeId);
            return map;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            typeId = bundle.getString("typeId");
        }
        mSingerLst = new ArrayList<>();
        mSingerAdapter = new SingerAdapter(this,mSingerLst);
        mLstSinger.setAdapter(mSingerAdapter);
        mRQueue.add(singerList);
        mLstSinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SingerActivity.this, SingerMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("singerId",mSingerLst.get(position).getId());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mLoadStatusBox.setOnClickListener(this);
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (Singer)serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getSingerBySingerType.replace("/","_") + HttpTools.APP_ID + typeId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        Singer singer = (Singer) entity;
        mSingerLst.addAll(singer.getObj());
        mSingerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT,getVolleyErrorMessage(errorInfo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loadStatusBox:
                mLoadStatusBox.loading();
                mRQueue.add(singerList);
                break;
        }
    }
}
