package com.jxnu.zha.tingbei.manager;

import android.content.Context;
import android.widget.Toast;


import com.jxnu.zha.qinglibrary.util.NetWorkUtil;
import com.jxnu.zha.tingbei.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by DaiQing.Zha on 2016/6/12 0012.
 * Email:1642674371@qq.com
 * Description:
 */
public class ThreadPool {
    private ExecutorService service;

    private ThreadPool() {
        int num = Runtime.getRuntime().availableProcessors();
        service = Executors.newFixedThreadPool(num * 2);
    }

    private static final ThreadPool manager = new ThreadPool();

    public static ThreadPool getInstance() {
        return manager;
    }

    /**
     * 向线程池中添加任务---添加之前不检查网络
     * @param runnable
     */
    public void addTask(Runnable runnable) {

        service.execute(runnable);
    }

    /**
     * 向线程池中添加任务---添加之前检查网络
     * @param runnable
     * @param context
     */
    public void addTask(Runnable runnable, Context context) {
        if (!NetWorkUtil.NETWORK){
            Toast.makeText(context,context.getString(R.string.tipInfo_networkError),Toast.LENGTH_SHORT).show();
            return;
        }
        service.execute(runnable);
    }
}
