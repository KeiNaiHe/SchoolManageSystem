package huohuo.cn.hncc.loginpage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.MainActivity;



/**
 * Created by Windows on 2018/4/28.
 */

public class LoginActivity extends Activity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private String LOGIN_SPNAME = "loginFileName";
    private String USERNAME = "username";
    private String PASSWORD = "password";
    private String REMPASS = "remPass";
    private String AUTOLOGIN = "autoLogin";

    private CheckBox mCb_autoLogin;
    private CheckBox mCb_remPass;
    private Button mBtn_login;
    private SharedPreferences mSP;
    private EditText mEt_pass;
    private EditText mEt_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initNativeInfo();
        initData();
    }

    /**
     * 加载本地SpInfo
     */
    private void initNativeInfo() {
        String user = getSPString(USERNAME);
        mEt_user.setText(user);
        int remPass = getSPInteger(REMPASS);

        int autoLogin = getSPInteger(AUTOLOGIN);
        if (autoLogin == 1) {
            mCb_autoLogin.setChecked(true);
        }
        //如果账号不为null,切且记住密码为true，则在页面上设置密码与记住密码状态
        if (!user.equals("") && remPass == 1) {
            String pass = getSPString(PASSWORD);
            mEt_pass.setText(pass);
            mCb_remPass.setChecked(true);

            //当三者有一个成立的时候不会执行该方法
            if (!(user.equals("") || pass.equals("") || autoLogin == 0)) {
                if(isCheckInService(user,pass)){
                    skipMainPage();
                }
            }
        }


    }

    private void initData() {
        mCb_autoLogin.setOnCheckedChangeListener(this);
        mBtn_login.setOnClickListener(this);
        mCb_remPass.setOnCheckedChangeListener(this);
    }

    private void initView() {
        mCb_autoLogin = (CheckBox) findViewById(R.id.cb_login_autoLogin);
        mBtn_login = (Button) findViewById(R.id.btn_login_login);
        mCb_remPass = (CheckBox) findViewById(R.id.cb_login_remPass);
        mEt_pass = (EditText) findViewById(R.id.et_login_pass);
        mEt_user = (EditText) findViewById(R.id.et_login_user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                String user = mEt_user.getText().toString().trim();
                String pass = mEt_pass.getText().toString().trim();
                //检车账号、密码合法性
                if (!isCheck(user, pass)) {
                    return;
                }
                if (!isCheckInService(user, pass)) {
                    return;
                }
                //账号密码保存
                saveLoginInfo(user, pass);
                //页面跳转
                skipMainPage();

                break;
            default:
        }
    }

    private void skipMainPage() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginInfo(String user, String pass) {
        putSPString(USERNAME, user);
        putSPString(PASSWORD, pass);
    }

    /**
     * 服务器验证账号密码
     */
    private boolean isCheckInService(String user, String pass) {
        if (user.equals("2016013668") && pass.equals("102503")) {
            return true;
        }
        //此处写连接服务器代码
        return false;
    }


    /**
     * 账号密码合法性检测
     */
    private boolean isCheck(String user, String pass) {
        if (user.equals("") || pass.equals("")) {
            return false;
        }
        /**
         * 再加上账号位数判定
         * 等等各种自定义判定
         * */
        return true;
    }

    /**
     * 以下为Sharedprefences工具类
     * 待实现功能
     *      写入本地的数据实现加密（base64）
     */
    private String getSPString(String key) {
        if (mSP == null) {
            getSPObject();
        }
        return mSP.getString(key, "");
    }

    private int getSPInteger(String key) {
        if (mSP == null) {
            getSPObject();
        }
        return mSP.getInt(key, 0);
    }

    private void putSPString(String key, String value) {
        if (mSP == null) {
            getSPObject();
        }
        mSP.edit().putString(key, value).apply();
    }

    private void putSPInteger(String key, int value) {
        if (mSP == null) {
            getSPObject();
        }
        mSP.edit().putInt(key, value).apply();
    }

    private void getSPObject() {
        mSP = getSharedPreferences(LOGIN_SPNAME, MODE_PRIVATE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String key = "";
        int value = 0;
        switch (buttonView.getId()) {
            case R.id.cb_login_autoLogin:
                key = AUTOLOGIN;
                if (isChecked) {
                    value = 1;
                    //存状态码进SP
                } else {
                    value = 0;
                    //存状态码进SP
                }

                break;
            case R.id.cb_login_remPass:
                key = REMPASS;
                if (isChecked) {
                    value = 1;
                    //存状态码进SP
                } else {
                    value = 0;
                    //存状态码进SP
                }
                break;
            default:
        }
        putSPInteger(key, value);
    }
}
