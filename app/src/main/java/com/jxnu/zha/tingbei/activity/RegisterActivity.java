package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
        register(account,pwd,mSex);
    }

    private void register(final String account, final String pwd, final String sex){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid", HttpTools.APP_ID);
                map.put("loginid", account);
                map.put("pwd", pwd);
                map.put("sex", sex);
                String source = HttpTools.httpPost(RoutConstant.registerAction,map);
                try{

                }catch (Exception e){
                }
            }
        });
    }
}
