package com.jxnu.zha.qinglibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * @author caibing.zhang
 * @createdate 2013年3月1日 上午11:21:46
 * @Description: 常用单位转换的辅助类
 */
public class DensityUtil {

    /**
     * 第一个参数是单位,第二个参数是对应值,第三个你懂的，。
     * @param unit 单位
     * @param value 对应值
     * @param metrics 封装了显示区域的各种属性值
     * @return
     */
    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }

    /**
     * 将数值转换成dip
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static float numerical2dip(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 将数值转换成sp
     * COMPLEX_UNIT_SP是单位，(spVal)是数值，也就是(spVal)sp。
     *
     * @param context
     * @param spVal
     * @return
     */
    public static float numerical2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
    /**
     * dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static float dp2px(Context context, float dpVal) {
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpVal*scale+0.5f);
    }
    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * @param activity
     * @return
     * @Description: 获取屏幕宽高
     */
    public static int[] getDisplay(Activity activity) {
        int[] display = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        display[0] = dm.widthPixels;
        display[1] = dm.heightPixels;
        return display;
    }

    /**
     * @param view
     * @return
     * @Description: 获取视图在屏幕的坐标
     */
    public static int[] getViewXYOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }
}
