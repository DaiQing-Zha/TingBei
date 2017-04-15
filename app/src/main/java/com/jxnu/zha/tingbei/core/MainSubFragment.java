package com.jxnu.zha.tingbei.core;

/**
 * Created by DaiQing.Zha on 2017/4/15.
 * email:13767191284@163.com
 * description:
 */
public abstract class MainSubFragment extends BaseFragment {

    protected boolean mIsVisible;
    protected boolean initFlag = false; //是否初始化完成
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
