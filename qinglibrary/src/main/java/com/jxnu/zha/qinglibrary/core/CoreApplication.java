package com.jxnu.zha.qinglibrary.core;

import android.app.Application;
import android.content.res.Configuration;


import com.jxnu.zha.qinglibrary.util.FileUtil;
import com.jxnu.zha.qinglibrary.util.L;

import java.io.Serializable;

public class CoreApplication extends Application implements Serializable {
	
	public static final long serialVersionUID = 4656071326644680147L;
	private static CoreApplication context;
	public static boolean IS_EXIST_SDCARD;
	public static String CACHE_DIR_SD;                      //SD卡缓存目录
	public static String CACHE_DIR_SYSTEM;                  //系统目录
	public static String IMAGE_DIR;                   		//图片目录
	public static String FILE_DIR;                     	    //文件目录
	public static String LOG_DIR;                     	    //日志目录
	public static String IMAGE_UPLOAD_TEMP;   				//上传图片临时目录
	public static String LOG;                           //日志保存的SD卡的目录
	public static String AllLOG;
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = CoreApplication.this;
		init();
//		ExceptionHandler exceptionHandler = ExceptionHandler.getInstance() ;
//		exceptionHandler.init(this) ;
	}

	public static Application getInstance() {
		return context;
	}
	
	/**
	 * @Description: 初始化
	 */
	private void init(){
		if(FileUtil.isExistSD()){
			//SD存在
			CACHE_DIR_SD = FileUtil.getSDCacheDir(context);
			IS_EXIST_SDCARD=true;
		}else{
			//不存在则使用系统目录
			CACHE_DIR_SD = context.getCacheDir().getPath();
		}
		CACHE_DIR_SD+="/";
		L.e("----SD卡目录---->>>:" + CACHE_DIR_SD);
		LOG=CACHE_DIR_SD+"cache.log";
    	AllLOG=CACHE_DIR_SD+"allcache.log";
		IMAGE_DIR=CACHE_DIR_SD+"image/";
		FILE_DIR=CACHE_DIR_SD+"file/";
		LOG_DIR=CACHE_DIR_SD+"log/";
    	IMAGE_UPLOAD_TEMP=CACHE_DIR_SD+"imageUploadTemp/";
    	CACHE_DIR_SYSTEM=context.getCacheDir().getPath()+"/file/";
		FileUtil.checkDir(CACHE_DIR_SD);
		FileUtil.checkDir(IMAGE_DIR);
		FileUtil.checkDir(FILE_DIR);
		FileUtil.checkDir(LOG_DIR);
//    	FileUtil.checkDir(IMAGE_UPLOAD_TEMP);
		FileUtil.checkDir(CACHE_DIR_SYSTEM);
//    	TerminalUtils.getInstance(getApplicationContext()).initTerminalID();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
}
