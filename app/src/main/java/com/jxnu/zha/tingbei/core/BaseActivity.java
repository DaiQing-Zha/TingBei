package com.jxnu.zha.tingbei.core;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jxnu.zha.qinglibrary.util.ENetWorkErrorStyle;
import com.jxnu.zha.qinglibrary.widget.TipInfoLayout;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private static final String mToolBarTitleColor = "#FFFFFF";  //toolbar标题颜色
    protected boolean mIsTemplate = true; //是否使用模板
    protected LinearLayout mMainBody;       //主体
    protected Toolbar mToolbar;        //toolbar
    protected TipInfoLayout mTipInfoLayout;
    protected Fragment mFragmentContent;
    protected RequestQueue mRQueue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRQueue = Volley.newRequestQueue(this);
        if(mIsTemplate){
            setContentView(R.layout.layout_core_template);
            initWidget();
        }
    }

    public void setContentView(int layoutResID){
        if(layoutResID == R.layout.layout_core_template){
            super.setContentView(layoutResID);
        }else{
            if(mMainBody != null){
                mMainBody.removeAllViews();
                mMainBody.addView(this.getLayoutInflater().inflate(layoutResID,null),
                        new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.MATCH_PARENT));
                Log.e("main","--------mainBody1-------");
                ButterKnife.bind(this);
            }else{
                super.setContentView(layoutResID);
                Log.e("main","--------mainBody2-------");
                ButterKnife.bind(this);
            }
            init();
        }
    }

    /**
     * 初始化控件
     */
    private void initWidget(){
        mMainBody = (LinearLayout) findViewById(R.id.ll_mainBody);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mToolbar.setTitleTextColor(Color.parseColor(mToolBarTitleColor));
        mToolbar.setSubtitleTextColor(Color.parseColor(mToolBarTitleColor));
        setSupportActionBar(mToolbar);
        mTipInfoLayout = (TipInfoLayout)findViewById(R.id.tipInfo_layout);
    }
    /**
     * 初始化
     */
    protected abstract void init();

    private static final int RED_ALERT = 0xfff44336;
    private static final int ORANGE_WARM = 0xffffc107;
    private static final int GREEN_INFO = 0xff4caf50;

    protected void showSnackBarMsg(EAlertStyle eAlertStyle,String message){
        int colorResId;
        switch (eAlertStyle){
            case ALERT:
                colorResId = RED_ALERT;
                break;
            case WARNING:
                colorResId = ORANGE_WARM;
                break;
            case INFO:
                colorResId = GREEN_INFO;
                break;
            default:
                colorResId = GREEN_INFO;
                break;
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.tipInfo_layout), message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(colorResId);
        snackbar.show();
    }
    public void showSnackBarMsg(EAlertStyle style,int resString){
        showSnackBarMsg(style, getString(resString));
    }
    /**
     * 切换Fragment
     * @param resId 资源Id fragment 的布局文件id
     * @param to
     */
    protected void switchFragmentContent(int resId,Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mFragmentContent!=null){
            if (mFragmentContent != to) {
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mFragmentContent).add(resId, to); // 隐藏当前的fragment，add下一个fragment到Activity中
                } else {
                    transaction.hide(mFragmentContent).show(to); // 隐藏当前的fragment，显示下一个fragment
                }
            }
        }else{
            transaction.add(R.id.tipInfo_layout, to);
        }
        transaction.commitAllowingStateLoss();  //推荐使用此方法，更安全，更方便
        /**
         * Can not perform this action after onSaveInstanceState
         * onSaveInstanceState方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后
         * 再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成 commitAllowingStateLoss()就行了，其效果是一样的。
         */
        mFragmentContent = to;
    }
    /**
     * 自定义查找控件的方法
     * @param resId
     * @param <T>
     * @return
     */
    protected  <T extends View> T findWidget(int resId) {
        return (T) this.findViewById(resId);
    }

    /**
     * 根据error获取volley请求错误时的提示信息
     * @param error
     * @return
     */
    protected String getVolleyErrorMessage(@NonNull String error){
        String errorMessage = "";
        if (error.contains("TimeoutError")){
            errorMessage = getString(R.string.http_timeOut);
        }else if (error.contains("NetworkError")){
            errorMessage = getString(R.string.http_noNetwork);
        }else if (error.contains("NoConnectionError")){     //把手机网络关掉了
            errorMessage = getString(R.string.http_noNetwork);
        }else if (error.contains("ServerError")){   //请求地址错误或者参数错误
            errorMessage = getString(R.string.http_severNoResponse);
        }else{
            errorMessage = getString(R.string.http_noNetwork);
        }
        return errorMessage;
    }

    /**
     * 获取错误类型
     * @param error
     * @return
     */
    protected ENetWorkErrorStyle getErrorStyle(@NonNull String error){
        if (error.contains("TimeoutError")){
            return ENetWorkErrorStyle.CONNECT_TIME_OUT;
        }else if (error.contains("NetworkError")){
            return ENetWorkErrorStyle.NETWORK_ERROR;
        } else if (error.contains("NoConnectionError")){
            return ENetWorkErrorStyle.NETWORK_ERROR;
        }else if (error.contains("ServerError")){
            return ENetWorkErrorStyle.NETWORK_ERROR400;
        }else{
            return ENetWorkErrorStyle.NETWORK_ERROR;
        }
    }
}
