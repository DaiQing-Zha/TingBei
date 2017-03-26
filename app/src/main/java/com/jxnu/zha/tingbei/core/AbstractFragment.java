package com.jxnu.zha.tingbei.core;

import android.support.v4.app.Fragment;

import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;

/**
 * Created by DaiQing.Zha on 2017/3/26.
 * email:13767191284@163.com
 * description:
 */
public class AbstractFragment extends Fragment {

    protected TipInfoLayout mTipInfoLayout;
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
}
