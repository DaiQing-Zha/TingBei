package com.jxnu.zha.tingbei.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.util.StringUtil;
import com.jxnu.zha.qinglibrary.widget.ClearEditText;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ThreadPool;
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
    private double mLat;
    private double mLng;
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
        Location location = locationManager.getLastKnownLocation(provider);
        mLat = location.getLatitude();
        mLng = location.getLongitude();
    }

    @OnClick(R.id.tv_register)
    public void onClickRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
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
                map.put("pwd", pwd);
                map.put("long", mLng);
                map.put("lat", mLat);
                String source = HttpTools.httpPost(RoutConstant.loginAction,map);
                try{

                }catch (Exception e){
                }
            }
        });
    }

}
