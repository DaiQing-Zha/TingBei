package com.jxnu.zha.tingbei.core;

import android.app.Application;

import com.jxnu.zha.qinglibrary.constant.CoreConstants;
import com.jxnu.zha.qinglibrary.core.CoreApplication;
import com.jxnu.zha.qinglibrary.util.FileLocalCache;
import com.jxnu.zha.tingbei.model.UserModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by DaiQing.Zha on 2017/3/19.
 * email:13767191284@163.com
 * description:
 */
public class BaseApplication extends CoreApplication {

    private static BaseApplication mInstance;
    UserModel userModel;

    /**
     * 判断用户是否登录
     * @return
     */
    public boolean isUserLogin(){
        userModel = (UserModel) FileLocalCache
                .getSerializableData(CoreConstants.CACHE_DIR_SYSTEM,"login_msg");
        if (userModel == null){
            return false;
        }
        return true;
    }

    /**
     * 保存用户对象
     * @param userModel
     */
    public void saveUser(UserModel userModel){
        FileLocalCache.setSerializableData(CoreConstants.CACHE_DIR_SYSTEM, userModel, "login_msg");
    }

    /**
     * 退出登录
     */
    public void logoutAccount(){
        FileLocalCache.deleteSerializableData(CoreConstants.CACHE_DIR_SYSTEM, "login_msg");
    }

    /**
     * 获取用户对象
     * @return
     */
    public UserModel getUser(){
        isUserLogin();
        return userModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        /**
         * 配置ImageLoader
         */
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
    public static BaseApplication getInstance() {
        return mInstance;
    }
}
