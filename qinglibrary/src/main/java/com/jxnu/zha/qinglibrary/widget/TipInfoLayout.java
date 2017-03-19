package com.jxnu.zha.qinglibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by DaiQing.Zha on 2017/3/16.
 * email:13767191284@163.com
 * description:带提示信息的布局
 */
public class TipInfoLayout extends FrameLayout{

    private ProgressWheel mPbProgressBar; //滚轮进度条
    private ImageView mImgTipState;      //提示状态
    private TextView mTvTipMsg;         //提示信息
    private Context mContext;          //上下文环境

    public TipInfoLayout(Context context) {
        super(context);
        this.mContext = context;
        initView(mContext);
    }

    public TipInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(mContext);
    }

    public TipInfoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(mContext);
    }

    /**
     * 控件初始化方法
     * @param context
     */
    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_core_tip_info, null, false);
        mPbProgressBar = (ProgressWheel) view.findViewById(R.id.pw_tipLoading);
        this.mImgTipState = (ImageView) view.findViewById(R.id.img_tipState);
        mTvTipMsg = (TextView) view.findViewById(R.id.tv_tipMsg);
        completeLoading();
        addView(view);
    }

    /**
     * 完成加载
     */
    public void completeLoading(){
        mPbProgressBar.setVisibility(GONE);
        mImgTipState.setVisibility(GONE);
        mTvTipMsg.setVisibility(GONE);
    }

    /**
     * 正在加载中
     */
    public void setLoading() {
        mPbProgressBar.setVisibility(View.VISIBLE);
        mImgTipState.setVisibility(View.GONE);
        mTvTipMsg.setVisibility(View.VISIBLE);
        mTvTipMsg.setText(mContext.getString(R.string.tipInfo_loading));
    }

    /**
     * 设置网络错误
     */
    public void setNetWorkError(){
        mPbProgressBar.setVisibility(GONE);
        mImgTipState.setVisibility(VISIBLE);
        mTvTipMsg.setVisibility(VISIBLE);
        mImgTipState.setImageResource(R.drawable.ic_core_no_network);
        mTvTipMsg.setText(mContext.getString(R.string.tipInfo_networkError));
    }

    /**
     * 无参错误提示信息
     * 默认提示数据加载失败
     */
    public void setFailureInfo() {
        mPbProgressBar.setVisibility(View.GONE);
        mImgTipState.setVisibility(View.VISIBLE);
        mImgTipState.setImageResource(R.drawable.ic_core_load_error);
        mTvTipMsg.setVisibility(View.VISIBLE);
        mTvTipMsg.setText(mContext.getString(R.string.tipInfo_loadDataFail));
    }

    /**
     * 带参数错误提示信息
     * @param message
     */
    public void setFailureInfo(String message){
        setFailureInfo();
        mTvTipMsg.setText(message);
    }

    /**
     * 不带参空数据提示
     * 默认提示暂无数据
     */
    public void setEmptyData() {
        setVisibility(VISIBLE);
        mPbProgressBar.setVisibility(View.GONE);
        mImgTipState.setVisibility(View.VISIBLE);
        mImgTipState.setImageResource(R.drawable.ic_core_page_empty);
        mTvTipMsg.setVisibility(View.VISIBLE);
        mTvTipMsg.setText(mContext.getString(R.string.tipInfo_loadEmpty));
    }

    /**
     * 带参空数据提示
     * @param message
     */
    public void setEmptyData(String message){
        setEmptyData();
        mTvTipMsg.setText(message);
    }
    /**
     * 不显示任何提示
     */
    public void setCancelShow(){
        mPbProgressBar.setVisibility(View.GONE);
        mImgTipState.setVisibility(View.GONE);
        mTvTipMsg.setVisibility(View.GONE);
    }
}
