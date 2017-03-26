package com.jxnu.zha.tingbei.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:
 */
public abstract class LazyFragment extends BaseFragment {

    protected boolean mIsVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            mIsVisible = true;
            onVisible();
        }else{
            mIsVisible = false;
            onInVisible();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    protected abstract void lazyLoad();
    protected void onVisible(){
        lazyLoad();
    }
    protected void onInVisible(){}

}
