package com.twlrg.slbl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.utils.ConfigManager;
import com.twlrg.slbl.utils.StringUtils;
import com.twlrg.slbl.utils.ToastUtil;

import butterknife.BindView;

/**
 * 作者：王先云 on 2018/4/7 12:10
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class SettingActivity extends BaseActivity
{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView  tvTitle;
    @BindView(R.id.btn_submit)
    Button    btnSubmit;
    @BindView(R.id.et_url)
    EditText  etUrl;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initEvent()
    {
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("服务器地址设置");
        etUrl.setText(ConfigManager.instance().getBaseUrl());
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        if (v == ivBack)
        {
            finish();
        }
        else if (v == btnSubmit)
        {
            String base_url= etUrl.getText().toString();

            if(StringUtils.stringIsEmpty(base_url))
            {
                ToastUtil.show(this,"请输入正确的IP地址");
                return;
            }
            else
            {
                ConfigManager.instance().setBaseUrl(base_url);
                ToastUtil.show(this,"IP地址设置成功！");
                finish();

            }
        }
    }
}
