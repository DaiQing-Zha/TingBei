package com.jxnu.zha.tingbei.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.HotRecommendAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.SongLabel;
import com.jxnu.zha.tingbei.widgets.HorizontalListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:推荐
 */
public class RecommendFragment extends BaseFragment {
    @BindView(R.id.hzLst)
    HorizontalListView mHzLstHotRecommend;

    HotRecommendAdapter mHotRecommendAdapter;
    List<SongLabel.ObjEntity> lst;
    private String TAG = "RECOMMEND_";
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend,container,false);
    }

    @Override
    public void builderView(View rootView) {
        lst = new ArrayList<>();
        mHotRecommendAdapter = new HotRecommendAdapter(father,lst);
        mHzLstHotRecommend.setAdapter(mHotRecommendAdapter);
        getHotRecommend();
    }
//getSongLabel --> getSongListByLabelId
    private void getHotRecommend(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                String source = HttpTools.httpPost(RoutConstant.getSongLabel,map);
                final SongLabel songLabel = new Gson().fromJson(source,SongLabel.class);
                lst.addAll(songLabel.getObj());
                father.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"source = " + songLabel.getObj().get(0).getName());
                        Log.e(TAG,"source = " + songLabel.getObj().get(1).getName());
                        Log.e(TAG,"source = " + songLabel.getObj().get(2).getName());
                        mHotRecommendAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
