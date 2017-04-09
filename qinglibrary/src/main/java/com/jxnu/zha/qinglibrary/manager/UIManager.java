package com.jxnu.zha.qinglibrary.manager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import com.jxnu.zha.qinglibrary.R;
import com.jxnu.zha.qinglibrary.util.DensityUtil;
import com.jxnu.zha.qinglibrary.util.DeviceUtil;

/**
 * Created by DaiQing.Zha on 2016/6/8 0008.
 * Email:1642674371@qq.com
 * Description:
 */
public class UIManager {

    /**
     * action sheet dialog
     *
     * @param context
     * @param view
     * @return
     */

    public static Dialog getActionSheet(Context context, View view) {

        final Dialog dialog = new Dialog(context, R.style.action_sheet);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        view.findViewById(R.id.action_sheet_cancle).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int screenW = getScreenWidth(context);
        lp.width = screenW;
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.action_sheet_animation); // 添加动画
        return dialog;
    }

    /**
     * 显示在底部的dialog
     * @param context
     * @param content
     * @return
     */
    public static Dialog getBottomShowDialog(Context context, View content){
        Dialog dg = new Dialog(context,R.style.BottomShowDialog);
        dg.getWindow().getAttributes();
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        dg.setContentView(content,lp);
        dg.setCanceledOnTouchOutside(true);
        Window win = 	dg.getWindow();
        win.setGravity(Gravity.BOTTOM);
        win.getAttributes().width = DeviceUtil.getScreenWidth(context);
        return dg;
    }

    /**
     * 显示在中间的dialog
     * @param context
     * @param content
     * @return
     */
    public static Dialog getCenterShowDialog(Context context, View content){
        Dialog dg = new Dialog(context,R.style.dialog_float_base);
        dg.getWindow().getAttributes();
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        dg.setContentView(content,lp);
        dg.setCanceledOnTouchOutside(true);
        Window win = dg.getWindow();
        win.setGravity(Gravity.CENTER);
        win.getAttributes().width = DeviceUtil.getScreenWidth(context) - 60;
        return dg;
    }
    /**
     * 工具方法
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * sp或者 dp 装换为 px
     */
    public static int dpToPx(Context context, int dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dpValue * scale);
    }
}
