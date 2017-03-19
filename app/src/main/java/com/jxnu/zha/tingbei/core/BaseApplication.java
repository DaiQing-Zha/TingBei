package com.jxnu.zha.tingbei.core;

import android.app.Application;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static BaseApplication getInstance() {
        return mInstance;
    }
}
