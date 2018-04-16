package com.twlrg.slbl.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.ProductItemAdapter;
import com.twlrg.slbl.adapter.ProductOutItemAdapter;
import com.twlrg.slbl.entity.ProdNoticeInfo;
import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.ProductListHandler;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.ToastUtil;
import com.twlrg.slbl.utils.Urls;
import com.twlrg.slbl.widget.DividerDecoration;
import com.twlrg.slbl.widget.list.refresh.PullToRefreshBase;
import com.twlrg.slbl.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 材料出库详情
 */
public class ProductOutDetailActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, View.OnClickListener, IRequestListener
{
    @BindView(R.id.tv_product_id)
    TextView tvProductId;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    @BindView(R.id.tv_n_itm)
    TextView tvN_itm;
    @BindView(R.id.tv_prd_no)
    TextView tvProductNo;
    @BindView(R.id.tv_product_amount)
    TextView tvProductAmount;
    @BindView(R.id.tv_kw)
    TextView tvKw;
    private ImageView                 mBackIv;
    private TextView                  mTitleTv;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView              mRecyclerView;

    private ProductOutItemAdapter mProductOutItemAdapter;
    private List<ProductInfo> mProductInfoList = new ArrayList<>();

    private int pn = 1;
    private int mRefreshStatus;

    private static final String GET_PRODUCT_LIST = "GET_PRODUCT_LIST";

    private static final int REQUEST_SUCCESS = 0x01;
    private static final int REQUEST_FAIL    = 0x02;

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    ProductListHandler mProductListHandler = (ProductListHandler) msg.obj;
                    mProductInfoList.addAll(mProductListHandler.getProductInfoList());
                    mProductOutItemAdapter.notifyDataSetChanged();
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProductOutDetailActivity.this, msg.obj.toString());
                    break;


            }
        }
    };

    private ProdNoticeInfo mProdNoticeInfo;

    @Override
    protected void initData()
    {
        mProdNoticeInfo = (ProdNoticeInfo) getIntent().getSerializableExtra("TASKINFO");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_product_out_detail);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.pullToRefreshRecyclerView);
        mPullToRefreshRecyclerView.setPullLoadEnabled(true);
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
    }


    @Override
    protected void initEvent()
    {
        mBackIv.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
    }

    @Override
    protected void initViewData()
    {


        if (null != mProdNoticeInfo)
        {
            tvProductId.setText("任务单号：" + mProdNoticeInfo.getN_id());
            tvProductName.setText("产品名称：" + mProdNoticeInfo.getPro_name());
            tvProductAmount.setText("产品数量：" + mProdNoticeInfo.getPro_count());
            tvKw.setText("客户名称：" + mProdNoticeInfo.getCus_name());
            tvN_itm.setText("项次号：" + mProdNoticeInfo.getN_itm());
            tvProductNo.setText("产品编号：" + mProdNoticeInfo.getPro_no());
        }

        mProductOutItemAdapter = new ProductOutItemAdapter(mProductInfoList, ProductOutDetailActivity.this);
        mRecyclerView.setAdapter(mProductOutItemAdapter);
        getProductList();

    }


    private void getProductList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("N_ID", mProdNoticeInfo.getN_id());
        valuePairs.put("N_ITM", mProdNoticeInfo.getN_itm());
        valuePairs.put("PAGE", pn + "");
        valuePairs.put("PAGESIZE", "15");

        DataRequest.instance().request(ProductOutDetailActivity.this, Urls.getProdNoticeUrl(), this, HttpRequest.POST, GET_PRODUCT_LIST, valuePairs,
                new ProductListHandler());
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mProductInfoList.clear();
        pn = 1;
        mRefreshStatus = 0;
        getProductList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        pn += 1;
        mRefreshStatus = 1;
        getProductList();
    }


    @Override
    public void onClick(View v)
    {
        if (v == mBackIv)
        {
            finish();
        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (mRefreshStatus == 1)
        {
            mPullToRefreshRecyclerView.onPullUpRefreshComplete();
        }
        else
        {
            mPullToRefreshRecyclerView.onPullDownRefreshComplete();
        }

        if (GET_PRODUCT_LIST.equals(action))
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
    }



}
