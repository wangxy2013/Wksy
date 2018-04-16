package com.twlrg.slbl.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.SNAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.KWListHandler;
import com.twlrg.slbl.json.ResultHandler;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.DialogUtils;
import com.twlrg.slbl.utils.StringUtils;
import com.twlrg.slbl.utils.ToastUtil;
import com.twlrg.slbl.utils.Urls;
import com.twlrg.slbl.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * 作者：王先云 on 2018/4/3 11:46
 * 邮箱：wangxianyun1@163.com
 * 描述：成品入库操作
 */
public class ProductInActivity extends BaseActivity implements IRequestListener
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
    @BindView(R.id.et_batch)
    EditText     etBatch;

    private List<ProInfo> mSnList = new ArrayList<>();
    private SNAdapter mSNAdapter;
    private List<KWInfo> kwInfoList = new ArrayList<>();
    private String kw_code, kw_name, t_id,prd_no;

    private static final int REQUEST_SUCCESS = 0x01;
    public static final  int REQUEST_FAIL    = 0x02;
    private static final int GET_WV_SUCCESS  = 0x04;
    private static final int PRODUCT_CHECK_SUCCESS = 0x05;
    private static final int PRODUCT_CHECK_FAIL= 0x06;
    private static final String GET_WV   = "GET_WV";
    private static final String CHECK_IN = "CHECK_IN";
    private static final String  PRODUCT_CHECK = " PRODUCT_CHECK";
    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case REQUEST_SUCCESS:
                    ToastUtil.show(ProductInActivity.this, "操作成功！");
                    finish();
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProductInActivity.this, msg.obj.toString());
                    break;

                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
                    break;
                case PRODUCT_CHECK_SUCCESS:
                    mSNAdapter.notifyDataSetChanged();
                    break;
                case   PRODUCT_CHECK_FAIL:
                    ToastUtil.show(ProductInActivity.this, msg.obj.toString());
                    mSnList.remove(mSnList.size() - 1);
                    break;
            }
        }
    };


    @Override
    protected void initData()
    {
        t_id = getIntent().getStringExtra("T_ID");
        prd_no = getIntent().getStringExtra("PRD_NO");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_product_in);
    }

    @Override
    protected void initEvent()
    {
        ivBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        tvLibrary.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        getKv();
        tvTitle.setText("成品入库");
        tvSubmit.setVisibility(View.VISIBLE);
        tvSubmit.setText("入库");

        rvSn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSn.addItemDecoration(new DividerDecoration(this));

        mSNAdapter = new SNAdapter(mSnList, this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                mSnList.remove(position);
                mSNAdapter.notifyDataSetChanged();
            }
        });
        rvSn.setAdapter(mSNAdapter);
    }

    private void getKv()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CODE", "KW");
        DataRequest.instance().request(ProductInActivity.this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
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

            if (StringUtils.stringIsEmpty(kw_code))
            {
                ToastUtil.show(ProductInActivity.this, "请选择库位");
                return;
            }


            if (StringUtils.stringIsEmpty(sn))
            {
                ToastUtil.show(ProductInActivity.this, "请输入SN码!");
                return;
            }
            else
            {
                if (isSame(sn))
                {
                    ToastUtil.show(ProductInActivity.this, "已有相同SN码!");
                }
                else
                {
                    ProInfo mProInfo = new ProInfo();
                    mProInfo.setT_id(t_id);
                    mProInfo.setKw(kw_code);
                    mProInfo.setSn(sn);
                    mProInfo.setKwn(kw_name);
                    mProInfo.setBatch(etBatch.getText().toString());
                    mProInfo.setBat_no(etBatch.getText().toString());
                    mSnList.add(mProInfo);
                    //mSNAdapter.notifyDataSetChanged();

                    Map<String, String> valuePairs = new HashMap<>();
                    valuePairs.put("T_ID", t_id);
                    valuePairs.put("SN", sn);
                    valuePairs.put("PRD_NO", prd_no);
                    DataRequest.instance().request(this, Urls.getProdInChekUrl(), this, HttpRequest.POST, PRODUCT_CHECK, valuePairs,
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
            if (mSnList.isEmpty())
            {
                ToastUtil.show(ProductInActivity.this, "请先进行添加操作");
                return;
            }
            Map<String, List> valuePairs = new HashMap<>();
            valuePairs.put("list", mSnList);
            DataRequest.instance().request1(ProductInActivity.this, Urls.getProdCheckinUrl(), this, HttpRequest.POST, CHECK_IN, valuePairs,
                    new ResultHandler());
        }
    }


    private boolean isSame(String sn)
    {

        for (int i = 0; i < mSnList.size(); i++)
        {
            if (sn.equals(mSnList.get(i).getSn()))
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
        else if (PRODUCT_CHECK.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(PRODUCT_CHECK_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(PRODUCT_CHECK_FAIL, resultMsg));
            }
        }



    }

}
