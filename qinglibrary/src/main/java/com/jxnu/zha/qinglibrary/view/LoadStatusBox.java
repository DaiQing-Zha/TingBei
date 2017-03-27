package com.jxnu.zha.qinglibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.jxnu.zha.qinglibrary.R;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:页面内进度框
 * 需要设置setOnClickListener为button设置监听
 */
public class LoadStatusBox extends RelativeLayout implements View.OnClickListener{

    private View viewProgress,viewError;
    private OnClickListener btnClick;
    public LoadStatusBox(Context context) {
        super(context);
    }

    public LoadStatusBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadStatusBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        btnClick = l;
        findViewById(R.id.loadErrorBn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loading();
        btnClick.onClick(this);
    }

    /**
     * 数据正在加载中
     */
    public void loading(){
        if (viewProgress == null){
            viewProgress = findViewById(R.id.loadProgress);
            viewError = findViewById(R.id.loadErrorBox);
        }
        viewProgress.setVisibility(VISIBLE);
        viewError.setVisibility(GONE);
    }

    /**
     * 数据加载失败
     */
    public void loadFailed(){
        viewProgress.setVisibility(GONE);
        viewError.setVisibility(VISIBLE);
    }

    /**
     * 数据加载成功
     */
    public void loadSuccess(){
        this.setVisibility(GONE);
    }

}
