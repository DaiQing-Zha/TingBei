package com.jxnu.zha.tingbei.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.BaseFragment;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:
 */
public class RadioFragment extends BaseFragment{
    @BindView(R.id.btn_test)
    Button mBtnTest;
    private String TAG = "RADIO";
    @Override
    public View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_radio,container,false);
    }

    @Override
    public void builderView(View rootView) {

    }
    @OnClick(R.id.btn_test)
    public void onClickTest(){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
//                map.put("row","2");
//                map.put("page","1");
                String source = HttpTools.httpPost(RoutConstant.getLabelGroupOnInter,map);
                Log.e(TAG,"source = " + source);
            }
        });
    }
}
