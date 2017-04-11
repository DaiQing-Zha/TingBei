package com.jxnu.zha.tingbei.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.utils.StaticValue;

/**
 * Created by DaiQing.Zha on 2017/4/11.
 * email:13767191284@163.com
 * description:
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ViewGroup mDecorView = (ViewGroup) getWindow().getDecorView();
        mContentContainer = (FrameLayout) ((ViewGroup) mDecorView.getChildAt(0)).getChildAt(1);
        mFloatView =  LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_music_bottom, null);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        mContentContainer.addView(mFloatView, layoutParams);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    /**
     * 防止退出activity时闪烁
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private FrameLayout mContentContainer;
    private View mFloatView;
}
