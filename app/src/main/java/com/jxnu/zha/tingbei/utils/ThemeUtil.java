package com.jxnu.zha.tingbei.utils;

import android.app.Activity;

import com.jxnu.zha.qinglibrary.util.SharedPreferenceUtil;
import com.jxnu.zha.tingbei.R;

public class ThemeUtil {

    public static int style;
    /**
     * 选择主题
     * @param activity
     */
    public static void selectTheme(Activity activity){
        if (activity == null)
            return;

        int position = (int) SharedPreferenceUtil.get(activity, activity.getString(R.string.mainFragment_selectedTheme), 0);
        style = R.style.GreenTheme;
        switch (position){
            case 0:
                style = R.style.BlueTheme;
                break;
            case 1:
                style = R.style.BrownTheme;
                break;
            case 2:
                style = R.style.redTheme;
                break;
            case 3:
                style = R.style.BlueGreyTheme;
                break;
            case 4:
                style = R.style.YellowTheme;
                break;
            case 5:
                style = R.style.DeepPurpleTheme;
                break;
            case 6:
                style = R.style.PinkTheme;
                break;
            case 7:
                style = R.style.GreenTheme;
                break;
            default:
                break;
        }
        activity.setTheme(style);  //设置主题
    }
}