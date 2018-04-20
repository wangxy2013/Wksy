package com.twlrg.slbl.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.MaterialAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.MaterialInfo1;
import com.twlrg.slbl.entity.ScanInCheckInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.KWListHandler;
import com.twlrg.slbl.json.ResultHandler;
import com.twlrg.slbl.json.ScanInCheckInfoHandler;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.DialogUtils;
import com.twlrg.slbl.utils.LogUtil;
import com.twlrg.slbl.utils.StringUtils;
import com.twlrg.slbl.utils.ToastUtil;
import com.twlrg.slbl.utils.Urls;
import com.twlrg.slbl.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者：王先云 on 2018/4/3 11:46
 * 邮箱：wangxianyun1@163.com
 * 描述：物料入库操作
 */
public class MaterInActivity extends BaseActivity implements IRequestListener
{
    @BindView(R.id.iv_back)
    ImageView    ivBack;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.tv_submit)
    TextView     tvSubmit;
    @BindView(R.id.et_sn)
    EditText     etSn;
    @BindView(R.id.btn_add)
    Button       btnAdd;
    @BindView(R.id.tv_library)
    TextView     tvLibrary;
    @BindView(R.id.rv_sn)
    RecyclerView rvSn;
    @BindView(R.id.btn_scan)
    Button       btnScan;
    @BindView(R.id.et_account)
    EditText     etAccount;

    private List<MaterialInfo1> mMaterInfoList = new ArrayList<>();
    private MaterialAdapter mMaterialAdapter;
    private List<KWInfo> kwInfoList = new ArrayList<>();
    private String kw_code, kw_name, pro_no, pro_name;

    private String snCode;
    private int    rest_count;
    private static final int    REQUEST_SUCCESS          = 0x01;
    public static final  int    REQUEST_FAIL             = 0x02;
    private static final int    GET_WV_SUCCESS           = 0x04;
    private static final int    SCAN_IN_CHECK_SUCCESS    = 0x05;
    private static final int    SCAN_IN_CHECK_FAILD      = 0x06;
    private static final int    SCAN_IN_CHECK_SN_SUCCESS = 0x07;
    private static final String GET_WV                   = "GET_WV";
    private static final String CHECK_IN                 = "CHECK_IN";
    private static final String SCANINCHECK              = "SCANINCHECK";
    private static final String SCANINCHECK_SN           = "SCANINCHECK_SN";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_SUCCESS:
                    ToastUtil.show(MaterInActivity.this, "操作成功！");
                    tvSubmit.setEnabled(true);
                    finish();
                    break;

                case REQUEST_FAIL:
                    tvSubmit.setEnabled(true);
                    ToastUtil.show(MaterInActivity.this, msg.obj.toString());
                    break;

                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
                    break;

                case SCAN_IN_CHECK_SUCCESS:
                    mMaterialAdapter.notifyDataSetChanged();

                    break;
                case SCAN_IN_CHECK_FAILD:
                    ToastUtil.show(MaterInActivity.this, msg.obj.toString());
                    mMaterInfoList.remove(mMaterInfoList.size() - 1);
                    break;
                case SCAN_IN_CHECK_SN_SUCCESS:
                    etSn.setText(snCode);

                    ScanInCheckInfoHandler mScanInCheckInfoHandler = (ScanInCheckInfoHandler) msg.obj;
                    ScanInCheckInfo scanInCheckInfo = mScanInCheckInfoHandler.getScanInCheckInfo();

                    if (null != scanInCheckInfo)
                    {
                        kw_code = scanInCheckInfo.getKw();
                        kw_name = scanInCheckInfo.getKwn();
                        tvLibrary.setText(kw_name);
                        etAccount.setText(scanInCheckInfo.getCount());
                        rest_count = scanInCheckInfo.getRest();
                        pro_no = scanInCheckInfo.getPro_no();
                        pro_name = scanInCheckInfo.getPro_name();
                    }

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
        setContentView(R.layout.activity_mater_in);
    }

    @Override
    protected void initEvent()
    {
        ivBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        tvLibrary.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        btnScan.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        getKv();
        tvTitle.setText("物料入库");
        tvSubmit.setVisibility(View.VISIBLE);
        tvSubmit.setText("入库");

        rvSn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSn.addItemDecoration(new DividerDecoration(this));

        mMaterialAdapter = new MaterialAdapter(mMaterInfoList, this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                mMaterInfoList.remove(position);
                mMaterialAdapter.notifyDataSetChanged();
            }
        });
        rvSn.setAdapter(mMaterialAdapter);
    }

    private void getKv()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CODE", "KW");
        DataRequest.instance().request(MaterInActivity.this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
                new KWListHandler());

    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        if (v == ivBack)
        {
            finish();
        }
        else if (v == btnAdd)
        {
            String sn = etSn.getText().toString();
            String count = etAccount.getText().toString();


            if (StringUtils.stringIsEmpty(kw_code))
            {
                ToastUtil.show(MaterInActivity.this, "请选择库位");
                return;
            }
            if (StringUtils.stringIsEmpty(count))
            {
                ToastUtil.show(MaterInActivity.this, "请输入入库数量");
                return;
            }


            if (Integer.parseInt(count) > rest_count)
            {
                ToastUtil.show(MaterInActivity.this, "入库数量大于实际检测合格量");
                return;
            }


            if (StringUtils.stringIsEmpty(sn))
            {
                ToastUtil.show(MaterInActivity.this, "请输入物料编码!");
                return;
            }
            else
            {
                if (isSame(sn))
                {
                    ToastUtil.show(MaterInActivity.this, "已有相同物料编码!");
                }
                else
                {
                    MaterialInfo1 mProInfo = new MaterialInfo1();
                    mProInfo.setKw(kw_code);
                    mProInfo.setKwn(kw_name);
                    mProInfo.setPrd_no(pro_no);
                    mProInfo.setPro_name(pro_name);
                    mProInfo.setIs_in("1");
                    mProInfo.setMete_id(sn);
                    mProInfo.setIn_count(Integer.parseInt(count));
                    mMaterInfoList.add(mProInfo);

                    Map<String, String> valuePairs = new HashMap<>();
                    valuePairs.put("CONTENT", sn);
                    DataRequest.instance().request(MaterInActivity.this, Urls.getScanInCheckUrl(), this, HttpRequest.POST, SCANINCHECK, valuePairs,
                            new ResultHandler());
                }
            }


        }
        else if (v == tvLibrary)
        {
            if (!kwInfoList.isEmpty())
            {
                DialogUtils.showKWInfoDialog(this, kwInfoList, new MyItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        KWInfo mKWInfo = kwInfoList.get(position);

                        if (null != mKWInfo)
                        {
                            kw_code = mKWInfo.getKw_code();
                            kw_name = mKWInfo.getKw_name();
                            tvLibrary.setText(kw_name);
                        }
                    }
                });
            }
        }
        else if (v == tvSubmit)
        {
            if (mMaterInfoList.isEmpty())
            {
                ToastUtil.show(MaterInActivity.this, "请先进行添加操作");
                return;
            }
            tvSubmit.setEnabled(false);
            Map<String, List> valuePairs = new HashMap<>();
            valuePairs.put("list", mMaterInfoList);
            DataRequest.instance().request1(MaterInActivity.this, Urls.getProductCheckinUrl(), this, HttpRequest.POST, CHECK_IN, valuePairs,
                    new ResultHandler());
        }
        else if (v == btnScan)
        {
            if (Build.VERSION.SDK_INT > 22)
            {
                if (ContextCompat.checkSelfPermission(MaterInActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    //先判断有没有权限 ，没有就在这里进行权限的申请
                    ActivityCompat.requestPermissions(MaterInActivity.this,
                            new String[]{Manifest.permission.CAMERA}, 9001);

                }
                else
                {
                    //说明已经获取到摄像头权限了 想干嘛干嘛
                    toScan();
                }
            }
            else
            {
                //这个说明系统版本在6.0之下，不需要动态获取权限。
                toScan();
            }

        }
    }


    private boolean isSame(String sn)
    {

        for (int i = 0; i < mMaterInfoList.size(); i++)
        {
            if (sn.equals(mMaterInfoList.get(i).getMete_id()))
            {
                return true;
            }
        }

        return false;
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (GET_WV.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(GET_WV_SUCCESS, obj));
            }

        }
        else if (CHECK_IN.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
        else if (SCANINCHECK.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(SCAN_IN_CHECK_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(SCAN_IN_CHECK_FAILD, resultMsg));
            }
        }
        else if (SCANINCHECK_SN.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(SCAN_IN_CHECK_SN_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }

    public void toScan()
    {
        Intent intent = new Intent(MaterInActivity.this,
                CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }


    private static final int REQUEST_CODE_SCAN = 0x0001;

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
                {
                    snCode = content;
                    Map<String, String> valuePairs = new HashMap<>();
                    valuePairs.put("CONTENT", content);
                    DataRequest.instance().request(MaterInActivity.this, Urls.getScanInCheckUrl(), this, HttpRequest.POST, SCANINCHECK_SN, valuePairs,
                            new ScanInCheckInfoHandler());
                }

            }
        }
    }


}
