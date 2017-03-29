package com.jxnu.zha.qinglibrary.manager;

import android.content.Context;

import com.jxnu.zha.qinglibrary.util.NetWorkUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by DaiQing.Zha on 2017/3/30.
 * email:13767191284@163.com
 * description: 文件缓存工具类
 */
public class CacheManager {

    //wifi缓存时间为 5 分钟
    private final static long wifi_cache_time = 5 * 60 * 1000;
    //其它环境下缓存时间为 1 小时
    private final static long other_cache_time = 60 * 60 * 1000;

    /**
     * 保存缓存对象
     * @param context
     * @param serializable
     * @param file
     * @return
     */
    public static boolean saveObject(Context context, Serializable serializable
            ,String file){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = context.openFileOutput(file,Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(serializable);
            oos.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static Serializable readObject(Context context,String file){
        if (!isExistCacheFile(context,file)){
            return null;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        }catch (FileNotFoundException e){
        } catch (Exception e){
            //反序列化失败，则删除文件
            if (e instanceof InvalidClassException){
                File cache = context.getFileStreamPath(file);
                cache.delete();
            }
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 判断缓存文件是否存在
     * @param context
     * @param cacheFile
     * @return
     */
    private static boolean isExistCacheFile(Context context,String cacheFile){
        if (context == null){
            return false;
        }
        boolean isExist = false;
        File cache = context.getFileStreamPath(cacheFile);
        if (cache.exists()){
            return true;
        }
        return isExist;
    }
    /**
     * 判断缓存是否失效
     * @param context
     * @param cacheFile
     * @return
     */
    public static boolean isCacheFailure(Context context,String cacheFile){
        File cache = context.getFileStreamPath(cacheFile);
        if (!cache.exists()){
            return false;
        }
        long existTime = System.currentTimeMillis() - cache.lastModified();
        boolean isFailure = false;
        if (NetWorkUtil.getNetworkType() == NetWorkUtil.NETTYPE_WIFI){
            isFailure = existTime > wifi_cache_time ? true : false;
        }else {
            isFailure = existTime > other_cache_time ? true : false;
        }
        return isFailure;
    }
}
