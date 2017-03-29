package com.jxnu.zha.qinglibrary.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.R;
import com.jxnu.zha.qinglibrary.util.ENetWorkErrorStyle;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:页面内进度框
 * 需要设置setOnClickListener为button设置监听
 */
public class LoadStatusBox extends RelativeLayout implements View.OnClickListener{

    private RelativeLayout mRlContent;
    private TextView mTvErrorCause,mTvErrorSuggest;
    private ImageView mImgLoadError;
    private View viewProgress,viewError;
    private OnClickListener btnClick;
    private Context mContext;
    public LoadStatusBox(Context context) {
        super(context);
        mContext = context;
    }

    public LoadStatusBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoadStatusBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        btnClick = l;
        mRlContent = (RelativeLayout) findViewById(R.id.rl_content);
        mTvErrorCause = (TextView) findViewById(R.id.tv_loadErrorCause);
        mTvErrorSuggest = (TextView) findViewById(R.id.tv_loadErrorSuggest);
        mImgLoadError = (ImageView) findViewById(R.id.img_loadError);
        mRlContent.setOnClickListener(this);
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
        mRlContent.setClickable(false);

    }

    /**
     * 数据加载失败
     */
    public void loadFailed(){
        viewProgress.setVisibility(GONE);
        viewError.setVisibility(VISIBLE);
        mRlContent.setClickable(true);
    }

    /**
     * 数据加载失败
     * @param style
     */
    public void loadFailed(ENetWorkErrorStyle style){
        viewProgress.setVisibility(GONE);
        viewError.setVisibility(VISIBLE);
        mRlContent.setClickable(true);
        setPrompt(style);
    }

    /**
     * 数据加载成功
     */
    public void loadSuccess(){
        this.setVisibility(GONE);
    }

    /**
     * 设置提示消息
     * @param cause
     * @param suggest
     */
    private void setPromptMessage(String cause,String suggest){
        mTvErrorCause.setText(cause);
        mTvErrorSuggest.setText(suggest);
    }

    /**
     * 设置提示图片
     * @param imgResource
     */
    private void setPromptImg(@DrawableRes int imgResource){
        mImgLoadError.setImageResource(imgResource);
    }

    public void setPrompt(ENetWorkErrorStyle style){
        String cause = "";
        String suggest = "";
        int imgResourceId = R.mipmap.ic_default_network_error;
        switch (style){
            case CONNECT_TIME_OUT:  //超时
                cause = mContext.getString(R.string.loadState_errorConnectTimeOutCause);
                suggest = mContext.getString(R.string.loadState_errorConnectTimeOutSuggest);
                imgResourceId = R.mipmap.ic_network_connect_timeout;
                break;
            case NETWORK_ERROR: //手机没有网络
                cause = mContext.getString(R.string.loadState_errorNetworkCause);
                suggest = mContext.getString(R.string.loadState_errorNetworkSuggest);
                imgResourceId = R.mipmap.ic_default_network_error;
                break;
            case NETWORK_ERROR404:  //页面不存在
                cause = mContext.getString(R.string.loadState_errorServerErrorCause);
                suggest = mContext.getString(R.string.loadState_errorServerErrorSuggest);
                imgResourceId = R.mipmap.ic_network_error400;
                break;
            case DEFAULT_LOAD_ERROR:    //默认的网络错误
                cause = mContext.getString(R.string.loadState_errorDefaultLoadCause);
                suggest = mContext.getString(R.string.loadState_errorDefaultLoadSuggest);
                imgResourceId = R.mipmap.ic_default_load_error;
        }
        setPromptMessage(cause,suggest);
        setPromptImg(imgResourceId);
    }
}
