package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.RingInfo;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/6 0006.
 */
public class RingInfoActivity extends AbstractActivity {

    @BindView(R.id.tv_cooperation)
    TextView tv_cooperation;
    private String musicId;
    private String TAG = "RingInfoActivity";
    StringRequest ringInfoQueue = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getRingInfoByMusicId)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e(TAG,"response = " + response);
            RingInfo ringInfo = new Gson().fromJson(response,RingInfo.class);
            tv_cooperation.setText(ringInfo.getObj().getCooperation());
            saveCache(ringInfo);
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
            map.put("id",musicId);
            Log.e(TAG,"singerId = " + musicId);
            return map;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_info);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            musicId = bundle.getString("musicId");
        }
        getRingInfo();
    }

    private void getRingInfo(){
        mRQueue.add(ringInfoQueue);
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (RingInfo)serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getRingInfoByMusicId.replace("/","_") + HttpTools.APP_ID + musicId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        RingInfo ringInfo = (RingInfo) entity;
        tv_cooperation.setText(ringInfo.getObj().getCooperation());
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        showSnackBarMsg(EAlertStyle.ALERT,getVolleyErrorMessage(errorInfo));
    }
}
