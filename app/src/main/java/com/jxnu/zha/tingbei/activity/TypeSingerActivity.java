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
import com.jxnu.zha.tingbei.adapter.TypeSingerAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
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
public class TypeSingerActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.lst_singerList)
    ListView mListViewSinger;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private List<Singer.ObjEntity> mLstSinger;
    private TypeSingerAdapter mSingerAdapter;
    private String typeId = "";
    private String typeName = "";

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
            typeName = bundle.getString("typeName");
        }
        setTitle(typeName);
        mLstSinger = new ArrayList<>();
        mSingerAdapter = new TypeSingerAdapter(this,mLstSinger);
        mListViewSinger.setAdapter(mSingerAdapter);
        mListViewSinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TypeSingerActivity.this, SingerMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("singerId",mLstSinger.get(position).getId());
                bundle.putString("singerName",mLstSinger.get(position).getName());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mLoadStatusBox.setOnClickListener(this);
        getSingerListByTypes();
    }

    /**
     * 根据类型获取歌手列表
     */
    private void getSingerListByTypes(){
        mLoadStatusBox.loading();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("row","1");
                map.put("page","1");
                map.put("typeid",typeId);
                final String response = HttpTools.httpPost(RoutConstant.getSingerBySingerType,map);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Singer singer = new Gson().fromJson(response,Singer.class);
                                mLoadStatusBox.loadSuccess();
                                mLstSinger.addAll(singer.getObj());
                                mSingerAdapter.notifyDataSetChanged();
                                saveCache(singer);
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
//        mRQueue.add(singerListRequest);
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
        mLstSinger.addAll(singer.getObj());
        mSingerAdapter.notifyDataSetChanged();
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
                mLoadStatusBox.loading();
                getSingerListByTypes();
                break;
        }
    }
}
