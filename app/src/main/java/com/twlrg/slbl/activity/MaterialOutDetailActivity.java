package com.twlrg.slbl.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.MaterialOutAdapter;
import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.MaterialListHandler;
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
public class MaterialOutDetailActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, View.OnClickListener, IRequestListener
{
    @BindView(R.id.tv_product_id)
    TextView                  tvProductId;
    @BindView(R.id.tv_product_name)
    TextView                  tvProductName;
    @BindView(R.id.tv_product_amount)
    TextView                  tvProductAmount;
    @BindView(R.id.tv_xch)
    TextView                  tvXch;
    @BindView(R.id.ll_product)
    LinearLayout              llProduct;
    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private ImageView                 mBackIv;
    private TextView                  mTitleTv;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView              mRecyclerView;

    private MaterialOutAdapter mMaterialOutAdapter;
    private List<MaterialInfo> mMaterialInfoList = new ArrayList<>();

    private int pn = 1;
    private int mRefreshStatus;

    private static final String GET_MATER_LIST = "GET_MATER_LIST";

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
                    MaterialListHandler mMaterialListHandler = (MaterialListHandler) msg.obj;
                    mMaterialInfoList.addAll(mMaterialListHandler.getMaterialInfoList());
                    mMaterialOutAdapter.notifyDataSetChanged();
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(MaterialOutDetailActivity.this, msg.obj.toString());
                    break;


            }
        }
    };

    private TaskInfo mTaskInfo;

    @Override
    protected void initData()
    {
        mTaskInfo = (TaskInfo) getIntent().getSerializableExtra("TASKINFO");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_materail_detail);
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


        if (null != mTaskInfo)
        {
            tvProductId.setText("申请单号：" + mTaskInfo.getQl_id());
            tvProductName.setText("类别名称：" + mTaskInfo.getPro_name());
            tvProductAmount.setText("需求数量：" + mTaskInfo.getPro_count());
            tvXch.setText("项次号：" + mTaskInfo.getQl_itm());
        }

        mMaterialOutAdapter = new MaterialOutAdapter(mMaterialInfoList, MaterialOutDetailActivity.this);
        mRecyclerView.setAdapter(mMaterialOutAdapter);
        getProductList();

    }


    private void getProductList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("T_ID", mTaskInfo.getT_id());
        valuePairs.put("PAGE", pn + "");
        valuePairs.put("PAGESIZE", "15");

        DataRequest.instance().request(MaterialOutDetailActivity.this, Urls.gettaskMetelistUrl(), this, HttpRequest.POST, GET_MATER_LIST, valuePairs,
                new MaterialListHandler());
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mMaterialInfoList.clear();
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

        if (GET_MATER_LIST.equals(action))
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
