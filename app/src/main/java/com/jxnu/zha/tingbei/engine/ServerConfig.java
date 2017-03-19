package com.jxnu.zha.tingbei.engine;

import android.content.Context;

import com.jxnu.zha.tingbei.core.BaseApplication;
import com.jxnu.zha.tingbei.utils.CommonUtil;


/**
 * Created by DaiQing.Zha on 2016/6/8 0008.
 * Email:1642674371@qq.com
 * Description:服务器访问配置类
 */
public class ServerConfig {

    /**
     * DEBUG：本地调试环境
     * TEST：测试环境
     * RELEASE：生产环境
     */
    public enum Mode {
        DEBUG, TEST, RELEASE
    }

    private static final String ROOT_DEBUG = "139.129.213.70/awake/";
    private static final String ROOT_TEST = "";
    private static final String ROOT_RELEASE = "";

    public static Mode USE_SERVER_MODE;
    static {
        final Context context = BaseApplication.getInstance();
        USE_SERVER_MODE = Enum.valueOf(Mode.class, CommonUtil.getMetaValue(context, "server_mode"));
    }
    public static String getServerRoot() {
        if (USE_SERVER_MODE == Mode.DEBUG) {
            return ROOT_DEBUG;
        } else if (USE_SERVER_MODE == Mode.TEST) {
            return ROOT_TEST;
        } else {
            return ROOT_RELEASE;
        }
    }
}
