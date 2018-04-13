package com.twlrg.slbl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.LoginHandler;
import com.twlrg.slbl.utils.ConfigManager;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.ToastUtil;
import com.twlrg.slbl.utils.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/16 11:59
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class LoginActivity extends BaseActivity implements IRequestListener
{
    @BindView(R.id.et_usrName)
    EditText     etUsrName;
    @BindView(R.id.ll_userName)
    LinearLayout llUserName;
    @BindView(R.id.et_pwd)
    EditText     etPwd;
    @BindView(R.id.ll_userPwd)
    LinearLayout llUserPwd;
    @BindView(R.id.btn_login)
    Button       btnLogin;
    @BindView(R.id.tv_forgetPwd)
    TextView     tvForgetPwd;
    @BindView(R.id.iv_setting)
    ImageView    ivSetting;

    private static final int    REQUEST_LOGIN_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL          = 0x02;
    private static final String USER_LOGIN            = "user_login";


    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case REQUEST_LOGIN_SUCCESS:
                    ToastUtil.show(LoginActivity.this, "登录成功!");
                    ConfigManager.instance().setUserPwd(mPwd);
                    ConfigManager.instance().setMobile(mUserName);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;


                case REQUEST_FAIL:
                    ToastUtil.show(LoginActivity.this, msg.obj.toString());
                    break;


            }
        }
    };

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_login);
        setTranslucentStatus();

    }

    @Override
    protected void initEvent()
    {
        btnLogin.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
                etUsrName.setText(ConfigManager.instance().getMobile());
                etPwd.setText(ConfigManager.instance().getUserPwd());
    }

    String mUserName;
    String mPwd;

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.btn_login:
                mUserName = etUsrName.getText().toString();
                mPwd = etPwd.getText().toString();


                if (TextUtils.isEmpty(mUserName))
                {
                    ToastUtil.show(LoginActivity.this, "请输入用户名");
                    return;
                }

                //                if (mUserName.length() < 11)
                //                {
                //                    ToastUtil.show(LoginActivity.this, "请输入正确的手机格式");
                //                    return;
                //                }


                if (TextUtils.isEmpty(mPwd))
                {
                    ToastUtil.show(LoginActivity.this, "请输入密码");
                    return;
                }

                Map<String, String> valuePairs = new HashMap<>();
                valuePairs.put("USERNAME", mUserName);
                valuePairs.put("PASSWORD", mPwd);
                DataRequest.instance().request(LoginActivity.this, Urls.getLoginUrl(), this, HttpRequest.POST, USER_LOGIN, valuePairs,
                        new LoginHandler());


                //                ConfigManager.instance().setUserName(mUserName);

                break;

            case R.id.iv_setting:
                startActivity(new Intent(LoginActivity.this, SettingActivity.class));
                break;
        }
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (USER_LOGIN.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_LOGIN_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
