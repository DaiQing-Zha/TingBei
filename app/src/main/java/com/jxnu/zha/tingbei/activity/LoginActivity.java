package com.jxnu.zha.tingbei.activity;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.util.StringUtil;
import com.jxnu.zha.qinglibrary.widget.ClearEditText;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.ConstantValue;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.core.BaseApplication;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.UserModel;
import com.jxnu.zha.tingbei.utils.EAlertStyle;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by DaiQing.Zha on 2017/4/18.
 * email:13767191284@163.com
 * description:
 */
public class LoginActivity extends AbstractActivity {
    @BindView(R.id.clearET_loginUser)
    ClearEditText mClearEditTextUser;
    @BindView(R.id.clearET_loginPwd)
    ClearEditText mClearEditTextPwd;
    @BindView(R.id.chk_rememberPwd)  //密码是否可见的开关
            CheckBox mChkRememberPwd;
    @BindView(R.id.btn_loginLogin)
    Button mBtnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void init() {
        setTitle(getString(R.string.login_login));
        String serviceString = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(serviceString);
        String provider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @OnClick(R.id.tv_register)
    public void onClickRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent, ConstantValue.INTENT_FROM_LOGIN_TO_REGISTER);
    }
    @OnClick(R.id.btn_loginLogin)
    public void onClickLogin(){
        String account = mClearEditTextUser.getText().toString();
        String pwd = mClearEditTextPwd.getText().toString();
        if (StringUtil.isEmpty(account)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_accountProhibitNull));
            return;
        }
        if (StringUtil.isEmpty(pwd)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_pwdProhibitNull));
            return;
        }
        login(account,pwd);
    }
    @OnCheckedChanged(R.id.chk_rememberPwd)
    void onChecked(boolean isChecked) { //是否记住密码
    }

    private void login(final String account, final String pwd){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("loginid", account);
                map.put("loginpwd", pwd);
                String source = HttpTools.httpPost(RoutConstant.loginAction,map);
                Log.e("mainLogin","source = " + source);
                try{
                    UserModel userModel = new Gson().fromJson(source,UserModel.class);
                    BaseApplication.getInstance().saveUser(userModel);
                    sendBroadcastLoginSuccess(ConstantValue.ACTION_LOGIN_SUCCESS);
                    finish();
                }catch (Exception e){
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ConstantValue.INTENT_FROM_LOGIN_TO_REGISTER:   //从绑定车辆返回过来
                String loginId = data.getStringExtra("loginid");
                String loginPwd = data.getStringExtra("loginpwd");
                mClearEditTextUser.setText(loginId);
                mClearEditTextPwd.setText(loginPwd);
                break;
        }
    }

    /**
     * 发送蓝牙连接步骤的广播
     * @param action
     */
    private void sendBroadcastLoginSuccess(String action) {
        // 发送广播
        Intent intent = new Intent();
        intent.setAction(action);
        sendBroadcast(intent);
    }
}
