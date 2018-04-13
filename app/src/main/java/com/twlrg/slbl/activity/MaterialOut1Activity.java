package com.twlrg.slbl.activity;

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

import com.alibaba.fastjson.JSON;
import com.google.zxing.activity.CaptureActivity;
import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.MaterialAdapter;
import com.twlrg.slbl.adapter.SNAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.MaterialInfo1;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.KWListHandler;
import com.twlrg.slbl.json.ResultHandler;
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
 * 描述：材料出库操作
 */
public class MaterialOut1Activity extends BaseActivity implements IRequestListener
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
    @BindView(R.id.et_count)
    EditText     etCount;

    private List<MaterialInfo1> mMaerInfoList = new ArrayList<>();
    private MaterialAdapter mMaterialAdapter;
    private List<KWInfo> kwInfoList = new ArrayList<>();
    private String kw_code, kw_name, t_id, ql_id, ql_itm, t_itm,prd_no;
    private TaskInfo mTaskInfo;
    private static final int         REQUEST_SUCCESS        = 0x01;
    public static final  int         REQUEST_FAIL           = 0x02;
    private static final int         GET_WV_SUCCESS         = 0x04;
    private static final int         SCAN_OUT_CHECK_SUCCESS = 0x05;
    private static final int         SCAN_OUT_CHECK_FAILD   = 0x06;
    private static final String      GET_WV                 = "GET_WV";
    private static final String      CHECK_OUT              = "CHECK_OUT";
    private static final String      SCANOUTCHECK           = "SCANOUTCHECK";
    private              BaseHandler mHandler               = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case REQUEST_SUCCESS:
                    ToastUtil.show(MaterialOut1Activity.this, "操作成功！");
                    finish();
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(MaterialOut1Activity.this, msg.obj.toString());
                    break;

                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
                    break;


                case SCAN_OUT_CHECK_SUCCESS:
                    mMaterialAdapter.notifyDataSetChanged();
                    break;
                case SCAN_OUT_CHECK_FAILD:
                    ToastUtil.show(MaterialOut1Activity.this, msg.obj.toString());
                    mMaerInfoList.remove(mMaerInfoList.size()-1);

                    break;
            }
        }
    };


    @Override
    protected void initData()
    {

        mTaskInfo = (TaskInfo) getIntent().getSerializableExtra("TASKINFO");
        if (null != mTaskInfo)
        {
            t_id = mTaskInfo.getT_id();
            ql_id = mTaskInfo.getQl_id();
            ql_itm = mTaskInfo.getQl_itm();
            t_itm = mTaskInfo.getT_itm();
            kw_code = mTaskInfo.getWh();
            prd_no = mTaskInfo.getPrd_no();
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_material_out1);
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
        tvTitle.setText("物料出库操作");
        tvSubmit.setVisibility(View.VISIBLE);
        tvSubmit.setText("出库");

        rvSn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSn.addItemDecoration(new DividerDecoration(this));

        mMaterialAdapter = new MaterialAdapter(mMaerInfoList, this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                mMaerInfoList.remove(position);
                mMaterialAdapter.notifyDataSetChanged();
            }
        });
        rvSn.setAdapter(mMaterialAdapter);

        tvLibrary.setText(kw_code);
    }

    private void getKv()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CODE", "kw");
        DataRequest.instance().request(MaterialOut1Activity.this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
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
            String count = etCount.getText().toString();
            if (StringUtils.stringIsEmpty(kw_code))
            {
                ToastUtil.show(MaterialOut1Activity.this, "请选择库位");
                return;
            }


            if (StringUtils.stringIsEmpty(count) || "0".startsWith(count))
            {
                ToastUtil.show(MaterialOut1Activity.this, "请输入正确的数量");
                return;
            }

            if(Integer.parseInt(count)>mTaskInfo.getQty_import())
            {
                ToastUtil.show(MaterialOut1Activity.this, "数量大于实际检测合格量");
                return;
            }



            if (StringUtils.stringIsEmpty(sn))
            {
                ToastUtil.show(MaterialOut1Activity.this, "请输入单号码!");
                return;
            }
            else
            {
                if (isSame(sn))
                {
                    ToastUtil.show(MaterialOut1Activity.this, "已有相同材料编号!");
                }
                else
                {
                    MaterialInfo1 mProInfo = new MaterialInfo1();
                    mProInfo.setT_id(t_id);
                    mProInfo.setKw(kw_code);
                    mProInfo.setKwn(kw_name);
                    mProInfo.setMete_id(sn);
                    mProInfo.setQl_id(ql_id);
                    mProInfo.setQl_itm(ql_itm);
                    mProInfo.setT_itm(t_itm);
                    mProInfo.setOut_count(count);

                    mProInfo.setQl_no(mTaskInfo.getQl_no());
                    mProInfo.setPre_itm(mTaskInfo.getPre_itm());
                    mProInfo.setPrd_no(mTaskInfo.getPrd_no());
                    mProInfo.setBat_no(mTaskInfo.getBat_no());
                    mProInfo.setMo_no(mTaskInfo.getMo_no());
                    mProInfo.setEst_itm(mTaskInfo.getEst_itm());
                    mMaerInfoList.add(mProInfo);

                    Map<String, String> valuePairs = new HashMap<>();
                    valuePairs.put("CONTENT", sn);
                    valuePairs.put("QL_ID", ql_id);
                    valuePairs.put("QL_ITM", ql_itm);
                    valuePairs.put("PRD_NO", prd_no);
                    DataRequest.instance().request(MaterialOut1Activity.this, Urls.getScanOutCheckUrl(), this, HttpRequest.POST, SCANOUTCHECK, valuePairs,
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
            if (mMaerInfoList.isEmpty())
            {
                return;
            }

            Map<String, List> valuePairs = new HashMap<>();
            valuePairs.put("list",mMaerInfoList);
            DataRequest.instance().request1(MaterialOut1Activity.this, Urls.getCheckoutUrl(), this, HttpRequest.POST, CHECK_OUT, valuePairs,
                    new ResultHandler());
        }
        else if (v == btnScan)
        {
            if (Build.VERSION.SDK_INT > 22)
            {
                if (ContextCompat.checkSelfPermission(MaterialOut1Activity.this,
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    //先判断有没有权限 ，没有就在这里进行权限的申请
                    ActivityCompat.requestPermissions(MaterialOut1Activity.this,
                            new String[]{android.Manifest.permission.CAMERA}, 9001);

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

        for (int i = 0; i < mMaerInfoList.size(); i++)
        {
            if (sn.equals(mMaerInfoList.get(i).getMete_id()))
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
        else if (CHECK_OUT.equals(action))
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
        else if (SCANOUTCHECK.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(SCAN_OUT_CHECK_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(SCAN_OUT_CHECK_FAILD, resultMsg));
            }
        }

    }

    public void toScan()
    {
        Intent intent = new Intent(MaterialOut1Activity.this,
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
                    etSn.setText(content);
                }

            }
        }
    }


}
