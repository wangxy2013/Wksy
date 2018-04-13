package com.twlrg.slbl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.twlrg.slbl.R;
import com.twlrg.slbl.utils.ConfigManager;
import com.twlrg.slbl.utils.LogUtil;
import com.twlrg.slbl.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2018/3/27 15:07
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class MainActivity extends BaseActivity implements View.OnClickListener
{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView  tvTitle;
    @BindView(R.id.btn_wlrk)
    Button    btnWlrk;
    @BindView(R.id.btn_wlck)
    Button    btnWlck;
    @BindView(R.id.btn_cprk)
    Button    btnCprk;
    @BindView(R.id.btn_cpck)
    Button    btnCpck;

    private static final int    REQUEST_CODE_SCAN   = 0x0001;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initEvent()
    {
        btnWlrk.setOnClickListener(this);
        btnWlck.setOnClickListener(this);
        btnCprk.setOnClickListener(this);
        btnCpck.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText(ConfigManager.instance().getUserName());
        ivBack.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btn_wlck:

               //startActivity(new Intent(MainActivity.this,MaterialOutListActivity.class));

                startActivity(new Intent(MainActivity.this,RequestNoListActivity.class));

                break;

            case R.id.btn_wlrk:
                startActivity(new Intent(MainActivity.this,MaterInActivity.class));

                break;
            case R.id.btn_cpck:
                startActivity(new Intent(MainActivity.this,ProductOutListActivity.class));
                break;

            case R.id.btn_cprk:
                startActivity(new Intent(MainActivity.this,ProductInListActivity.class));

                break;
        }
    }


    public void toScan()
    {
        Intent intent = new Intent(MainActivity.this,
                CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN)
        {
            if (data != null)
            {

                String content = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
                LogUtil.e("TAG", "content--->" + content);

                if (!StringUtils.stringIsEmpty(content))
                    startActivity(new Intent(MainActivity.this, MaterialInActivity.class).putExtra("PRODUCT_ID", content));

            }
        }
    }


}
