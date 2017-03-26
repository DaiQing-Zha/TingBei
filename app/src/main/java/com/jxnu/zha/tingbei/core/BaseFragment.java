package com.jxnu.zha.tingbei.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;
import com.jxnu.zha.tingbei.R;

import butterknife.ButterKnife;

/**
 * Created by DaiQing.Zha on 2017/3/17.
 * email:13767191284@163.com
 * description:
 */
public abstract class BaseFragment extends AbstractFragment{

    private View rootView;
    protected RequestQueue mRQueue;
    protected BaseActivity father;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = getRootView(inflater,container,savedInstanceState);
            mTipInfoLayout = (TipInfoLayout)rootView.findViewById(R.id.tipInfo_layout);
            ButterKnife.bind(this,rootView);
            builderView(rootView);
        }else{
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) viewGroup.removeAllViewsInLayout();
        }
        return rootView;
    }

    public abstract View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void builderView(View rootView);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRQueue = Volley.newRequestQueue(getBaseActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        father = (BaseActivity) context;
    }

    /**
     * 自定义查找控件的方法
     * @param view
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T findWidget(View view,int resId){
        return (T) view.findViewById(resId);
    }

    /**
     * 自定义控件查找方法
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T findWidget(int resId){
        return (T) father.findViewById(resId);
    }
}
