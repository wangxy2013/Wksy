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

import com.alibaba.fastjson.JSON;
import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.SNAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.entity.ProdNoticeInfo;
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
 * 描述：成品出库操作
 */
public class ProductOutActivity extends BaseActivity implements IRequestListener
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

    @BindView(R.id.et_n_itm)
    EditText     etN_itm;
    private List<ProInfo> mSnList = new ArrayList<>();
    private SNAdapter mSNAdapter;
    private List<KWInfo> kwInfoList = new ArrayList<>();
    private String kw_code, kw_name, n_id, n_itm, so_id, so_itm;

    private ProdNoticeInfo mProdNoticeInfo;
    private static final int REQUEST_SUCCESS = 0x01;
    public static final  int REQUEST_FAIL    = 0x02;
    private static final int GET_WV_SUCCESS  = 0x04;
    public static final  int PROD_OUT_FAIL    = 0x05;
    private static final int PROD_OUT_SUCCESS  = 0x06;
    private static final String GET_WV    = "GET_WV";
    private static final String CHECK_OUT = "CHECK_OUT";
    private static final String PROD_OUT = "PROD_OUT";;
    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case REQUEST_SUCCESS:
                    ToastUtil.show(ProductOutActivity.this, "操作成功！");
                    finish();
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProductOutActivity.this, msg.obj.toString());
                    break;

                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
                    break;

                case PROD_OUT_SUCCESS:
                    ToastUtil.show(ProductOutActivity.this,"添加成功");
                    mSNAdapter.notifyDataSetChanged();
                    break;
                case PROD_OUT_FAIL:
                    ToastUtil.show(ProductOutActivity.this, msg.obj.toString());
                    mSnList.remove(mSnList.size()-1);
                    break;
            }
        }
    };


    @Override
    protected void initData()
    {
        mProdNoticeInfo = (ProdNoticeInfo) getIntent().getSerializableExtra("TASKINFO");
        if (null != mProdNoticeInfo)
        {
            n_id = mProdNoticeInfo.getN_id();
            n_itm = mProdNoticeInfo.getN_itm();
            so_id = mProdNoticeInfo.getSo_id();
            so_itm = mProdNoticeInfo.getSo_itm();
            kw_code = mProdNoticeInfo.getKw();
            kw_name= mProdNoticeInfo.getKwn();
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_product_out);
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
        tvTitle.setText("成品出库");
        tvSubmit.setVisibility(View.VISIBLE);
        tvSubmit.setText("出库");
        etN_itm.setText(mProdNoticeInfo.getBat_no());
        tvLibrary.setText(kw_name);
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
        DataRequest.instance().request(ProductOutActivity.this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
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
                ToastUtil.show(ProductOutActivity.this, "请选择库位");
                return;
            }


            if (StringUtils.stringIsEmpty(sn))
            {
                ToastUtil.show(ProductOutActivity.this, "请输入SN码!");
                return;
            }
            else
            {
                if (isSame(sn))
                {
                    ToastUtil.show(ProductOutActivity.this, "已有相同SN码!");
                }
                else
                {
                    ProInfo mProInfo = new ProInfo();
                    mProInfo.setN_id(n_id);
                    mProInfo.setKw(kw_code);
                    mProInfo.setSn(sn);
                    mProInfo.setKwn(kw_name);
                    mProInfo.setSo_id(so_id);
                    mProInfo.setN_itm(n_itm);
                    mProInfo.setSo_itm(so_itm);
                    mProInfo.setBat_no(mProdNoticeInfo.getBat_no());
                    mProInfo.setCus_name(mProdNoticeInfo.getCus_name());
                    mProInfo.setCus_no(mProdNoticeInfo.getCus_no());
                    mProInfo.setDep(mProdNoticeInfo.getDep());
                    mProInfo.setDep_name(mProdNoticeInfo.getDep_name());
                    mSnList.add(mProInfo);

                    Map<String, String> valuePairs = new HashMap<>();
                    valuePairs.put("SN", sn);
                    valuePairs.put("N_ID", n_id);
                    valuePairs.put("N_ITM", n_itm);
                    valuePairs.put("PRD_NO", mProdNoticeInfo.getPro_no());
                    DataRequest.instance().request(ProductOutActivity.this, Urls.getProdOutChekUrl(), this, HttpRequest.POST, PROD_OUT, valuePairs,
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
                return;
            }
            Map<String, List> valuePairs = new HashMap<>();
            valuePairs.put("list", mSnList);
            DataRequest.instance().request1(ProductOutActivity.this, Urls.getProdCheckoutUrl(), this, HttpRequest.POST, CHECK_OUT, valuePairs,
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
        else if (PROD_OUT.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(PROD_OUT_SUCCESS, obj));
            }
            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(PROD_OUT_FAIL, resultMsg));
            }
        }
    }
}
