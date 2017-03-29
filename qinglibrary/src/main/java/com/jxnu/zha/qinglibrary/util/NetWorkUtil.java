package com.jxnu.zha.qinglibrary.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jxnu.zha.qinglibrary.core.CoreApplication;

public class NetWorkUtil {
	//true有网络,false无网络
	public static boolean NETWORK;
	public static boolean WIFI;

	// 手机网络类型
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	/**
	 * 获取网络类型
	 * @return 0，没有网络；1：wifi网络；2：wap网络；3：net网络
     */
	public static int getNetworkType(){
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager)
				CoreApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null){
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE){
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringUtil.isEmpty(extraInfo)){
				if (extraInfo.toLowerCase().equals("cmnet")){
					return NETTYPE_CMNET;
				}else{
					return NETTYPE_CMWAP;
				}
			}
		}else if (nType == ConnectivityManager.TYPE_WIFI){
			return NETTYPE_WIFI;
		}
		return netType;
	}
}
