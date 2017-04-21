package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
import butterknife.OnClick;

/**
 * Created by DaiQing.Zha on 2017/4/18.
 * email:13767191284@163.com
 * description:
 */
public class RegisterActivity extends AbstractActivity {
    @BindView(R.id.clearET_registerUser)
    ClearEditText mClearEditTextUser;
    @BindView(R.id.clearET_registerPwd)
    ClearEditText mClearEditTextPwd;
    @BindView(R.id.clearET_nickName)
    ClearEditText mClearETNickName;
    @BindView(R.id.clearET_phone)
    ClearEditText mClearETPhone;
    @BindView(R.id.rg_userSex)
    RadioGroup rgUserSex;
    @BindView(R.id.rbSexMan)
    RadioButton rbSexMan;
    @BindView(R.id.rbSexWoman)
    RadioButton rbSexWoman;
    @BindView(R.id.btn_registerRegister)
    Button mBtnRegister;
    private String mSex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void init() {
        setTitle(getString(R.string.register_register));
        rgUserSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSexMan){
                    mSex = "男";
                }
                if (checkedId == R.id.rbSexWoman){
                    mSex = "女";
                }
            }
        });
    }

    @OnClick(R.id.btn_registerRegister)
    public void onClickRegister(){
        String loginId = mClearEditTextUser.getText().toString();
        String loginPwd = mClearEditTextPwd.getText().toString();
        String nickName = mClearETNickName.getText().toString();
        String phone = mClearETPhone.getText().toString();
        if (StringUtil.isEmpty(loginId)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_accountProhibitNull));
            return;
        }
        if (StringUtil.isEmpty(loginPwd)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_pwdProhibitNull));
            return;
        }
        if (StringUtil.isEmpty(nickName)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_nickNameProhibitNull));
            return;
        }
        if (StringUtil.isEmpty(phone)){
            showSnackBarMsg(EAlertStyle.WARNING,getString(R.string.toast_phoneProhibitNull));
            return;
        }
        register(loginId,loginPwd,nickName,phone);
    }

    private void register(final String loginId, final String loginPwd, final String nickname,final String phone){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {

                Map mapSameId = new HashMap();
                Map mapSameNickName = new HashMap();
                Map mapSamePhone = new HashMap();
                mapSameId.put("appid", HttpTools.APP_ID);
                mapSameId.put("loginid", loginId);
                mapSameNickName.put("appid", HttpTools.APP_ID);
                mapSameNickName.put("nickname", nickname);
                mapSamePhone.put("appid", HttpTools.APP_ID);
                mapSamePhone.put("phone", phone);
                String sourceSameId = HttpTools.httpPost(RoutConstant.registerAction,mapSameId);
                String sourceSameNickName = HttpTools.httpPost(RoutConstant.registerAction,mapSameNickName);
                String sourcePhone = HttpTools.httpPost(RoutConstant.registerAction,mapSamePhone);
                Log.e("mainHHH","sourceSameId = " + sourceSameId + " sourceSameNickName = " + sourceSameNickName + " sourcePhone = " + sourcePhone);
//                Map map = new HashMap();
//                map.put("appid", HttpTools.APP_ID);
//                map.put("loginid", loginId);
//                map.put("loginpwd", loginPwd);
//                map.put("nickname", nickname);
//                map.put("phone", phone);
//                String source = HttpTools.httpPost(RoutConstant.registerAction,map);
                try{

                }catch (Exception e){
                }
            }
        });
    }
}
