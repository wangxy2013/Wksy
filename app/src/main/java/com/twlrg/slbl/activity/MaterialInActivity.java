package com.twlrg.slbl.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.KWListHandler;
import com.twlrg.slbl.json.ProductInfoHandler;
import com.twlrg.slbl.json.ResultHandler;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.DialogUtils;
import com.twlrg.slbl.utils.StringUtils;
import com.twlrg.slbl.utils.ToastUtil;
import com.twlrg.slbl.utils.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2018/3/28 11:12
 * 邮箱：wangxianyun1@163.com
 * 描述：材料入库
 */
public class MaterialInActivity extends BaseActivity implements IRequestListener
{

    @BindView(R.id.iv_back)
    ImageView    ivBack;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.tv_mete_id)
    TextView     tvMeteId;
    @BindView(R.id.tv_type_name)
    TextView     tvTypeName;
    @BindView(R.id.tv_type_no)
    TextView     tvTypeNo;
    @BindView(R.id.tv_supplier)
    TextView     tvSupplier;
    @BindView(R.id.tv_batch)
    TextView     tvBatch;
    @BindView(R.id.tv_count)
    TextView     tvCount;
    @BindView(R.id.tv_ctime)
    TextView     tvCtime;
    @BindView(R.id.tv_v)
    TextView     tvV;
    @BindView(R.id.tv_r)
    TextView     tvR;
    @BindView(R.id.tv_c)
    TextView     tvC;
    @BindView(R.id.ll_electric)
    LinearLayout llElectric;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.btn_submit)
    Button   btnSubmit;
    @BindView(R.id.tv_library)
    TextView tvLibrary;

    private List<KWInfo> kwInfoList = new ArrayList<>();

    private static final int    REQUEST_SUCCESS         = 0x01;
    public static final  int    REQUEST_FAIL            = 0x02;
    private static final int    PRODUCT_STORAGE_SUCCESS = 0x03;
    private static final int    GET_WV_SUCCESS          = 0x04;
    private static final int    GET_WV_REQUEST          = 0x05;
    private static final String GET_PRODUCT_DETAIL      = "GET_PRODUCT_DETAIL";
    private static final String PRODUCT_STORAGE         = "PRODUCT_STORAGE";
    private static final String GET_WV                  = "GET_WV";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case REQUEST_SUCCESS:

                    ProductInfoHandler mProductInfoHandler = (ProductInfoHandler) msg.obj;
                    ProductInfo mProductInfo = mProductInfoHandler.getProductInfo();

                    if (null != mProductInfo)
                    {
                        tvMeteId.setText(mProductInfo.getMete_id());
                        tvTypeName.setText(mProductInfo.getType_name());
                        tvTypeNo.setText(mProductInfo.getType_no());
                        tvSupplier.setText(mProductInfo.getSupplier());
                        tvBatch.setText(mProductInfo.getBatch());
                        tvCount.setText(mProductInfo.getCount());
                        tvCtime.setText(mProductInfo.getCtime());
                        tvV.setText(mProductInfo.getV());
                        tvR.setText(mProductInfo.getR());
                        tvC.setText(mProductInfo.getC());

                        if (StringUtils.stringIsEmpty(mProductInfo.getV()))
                        {
                            llElectric.setVisibility(View.GONE);
                        }
                        else
                        {
                            llElectric.setVisibility(View.VISIBLE);
                        }

                    }

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(MaterialInActivity.this, msg.obj.toString());
                    break;

                case PRODUCT_STORAGE_SUCCESS:
                    ToastUtil.show(MaterialInActivity.this, "操作成功");
                    finish();
                    break;


                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
                    break;

                case GET_WV_REQUEST:
                    getKv();
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
        setContentView(R.layout.activity_material_in);
    }

    @Override
    protected void initEvent()
    {
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvLibrary.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("材料入库");
        tvSubmit.setVisibility(View.GONE);
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CONTENT", getIntent().getStringExtra("PRODUCT_ID"));
        DataRequest.instance().request(MaterialInActivity.this, Urls.getProductDetailUrl(), this, HttpRequest.POST, GET_PRODUCT_DETAIL, valuePairs,
                new ProductInfoHandler());

        mHandler.sendEmptyMessageDelayed(GET_WV_REQUEST, 1 * 1000);


    }


    private void getKv()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CODE", "KW");
        DataRequest.instance().request(MaterialInActivity.this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
                new KWListHandler());

    }


    private void storage()
    {
        if(StringUtils.stringIsEmpty(kw_code))
        {
            ToastUtil.show(this,"请选择库位");
            return;
        }

        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("METE_ID", getIntent().getStringExtra("PRODUCT_ID"));
        valuePairs.put("KW", kw_code);
        DataRequest.instance().request(MaterialInActivity.this, Urls.getProductCheckinUrl(), this, HttpRequest.POST, PRODUCT_STORAGE, valuePairs,
                new ResultHandler());
    }

    private String kw_code;

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_submit:
                storage();
                break;

            case R.id.tv_library:

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
                                tvLibrary.setText(mKWInfo.getKw_name());
                            }
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (GET_PRODUCT_DETAIL.equals(action))
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
        else if (PRODUCT_STORAGE.equals(action))
        {

            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(PRODUCT_STORAGE_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
        else if (GET_WV.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(GET_WV_SUCCESS, obj));
            }

        }
    }



}
