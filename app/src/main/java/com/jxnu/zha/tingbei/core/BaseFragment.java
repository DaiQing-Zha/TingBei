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
public abstract class BaseFragment extends Fragment{

    private View rootView;
    protected TipInfoLayout mTipInfoLayout;
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
        mRQueue = Volley.newRequestQueue(getBaseActivity());
        return rootView;
    }

    /**
     * 获取该Fragment所附属的Activity
     * @return
     */
    protected BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    /**
     * 提示语封装
     * @return
     */
    protected TipInfoLayout getTipInfoLayout(){
        if(mTipInfoLayout != null){
            return mTipInfoLayout;
        }else{
            return getBaseActivity().mTipInfoLayout;
        }
    }

    public abstract View getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void builderView(View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        father = (BaseActivity) context;
    }
}
