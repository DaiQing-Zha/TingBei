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
import com.jxnu.zha.tingbei.adapter.LabelSongListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.LabelSongList;
import com.jxnu.zha.tingbei.model.Music;
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
    StringRequest songListQueue = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getSongListByLabelId)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e(TAG,"response = " + response);
            mLoadStatusBox.loadSuccess();
            LabelSongList labelSongList = new Gson().fromJson(response,LabelSongList.class);
            mLabelSongLst.addAll(labelSongList.getObj());
            mSongListAdapter.notifyDataSetChanged();
            saveCache(labelSongList);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG,"error = " + error.toString());
            readCacheData(getCacheKey(),error.toString());
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            map.put("row","1");
            map.put("page","1");
            map.put("labelid",labelId);
            Log.e(TAG,"singerId = " + labelId);
            return map;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_song_list);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
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
        mRQueue.add(songListQueue);
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
        showSnackBarMsg(EAlertStyle.ALERT,getVolleyErrorMessage(errorInfo));
    }
}
