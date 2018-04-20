package com.twlrg.slbl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.ProdNoticeAdapter;
import com.twlrg.slbl.adapter.TaskAdapter;
import com.twlrg.slbl.entity.ProdNoticeInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.ProdNoticeListHandler;
import com.twlrg.slbl.json.ProductListHandler;
import com.twlrg.slbl.json.TaskListHandler;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.LogUtil;
import com.twlrg.slbl.utils.StringUtils;
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

/**
 * 成品出库
 */
public class ProductOutListActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, View.OnClickListener, IRequestListener
{
    @BindView(R.id.btn_submit)
    Button   btnSubmit;
    @BindView(R.id.et_task_id)
    EditText etTaskId;
    private ImageView                 mBackIv;
    private TextView                  mTitleTv;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView              mRecyclerView;

    private ProdNoticeAdapter mProdNoticeAdapter;

    private List<ProdNoticeInfo> mProdNoticeInfoList = new ArrayList<>();

    private int pn = 1;
    private int mRefreshStatus;
    private static final String GET_PROD_LIST = "GET_PROD_LIST";

    private static final int REQUEST_SUCCESS = 0x01;
    private static final int REQUEST_FAIL    = 0x02;

    private String mTid = "";
    private String t_id;
    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    ProdNoticeListHandler mProdNoticeListHandler = (ProdNoticeListHandler) msg.obj;
                    mProdNoticeInfoList.addAll(mProdNoticeListHandler.getProdNoticeInfos());
                    mProdNoticeAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProductOutListActivity.this, msg.obj.toString());

                    break;


            }
        }
    };

    @Override
    protected void initData()
    {
        mTid = getIntent().getStringExtra("N_ID");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_prod_notice_list);
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
        btnSubmit.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
       /* etTaskId.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 0)
                {
                    mTid = "";
                    mProdNoticeInfoList.clear();
                    pn = 1;
                    mRefreshStatus = 0;
                    getProductList();

                }
            }
        });*/
    }

    @Override
    protected void initViewData()
    {

        mProdNoticeAdapter = new ProdNoticeAdapter(mProdNoticeInfoList, ProductOutListActivity.this, new MyItemClickListener()
        {

            //详情
            @Override
            public void onItemClick(View view, int position)
            {

                Bundle b = new Bundle();
                b.putSerializable("TASKINFO", mProdNoticeInfoList.get(position));
                startActivity(new Intent(ProductOutListActivity.this, ProductOutDetailActivity.class).putExtras(b));
            }
        }, new MyItemClickListener()
        {
            //出库操作
            @Override
            public void onItemClick(View view, int position)
            {
                t_id = mProdNoticeInfoList.get(position).getN_id();
                Bundle b = new Bundle();
                b.putSerializable("TASKINFO", mProdNoticeInfoList.get(position));
                startActivity(new Intent(ProductOutListActivity.this, ProductOutActivity.class).putExtras(b));
            }
        });
        mRecyclerView.setAdapter(mProdNoticeAdapter);


    }


    @Override
    protected void onResume()
    {
        super.onResume();
        getProductList();
    }

    private void getProductList()
    {
        mProdNoticeInfoList.clear();
        Map<String, String> valuePairs = new HashMap<>();

        valuePairs.put("N_ID", mTid);
        valuePairs.put("PAGE", pn + "");
        valuePairs.put("PAGESIZE", "15");
        DataRequest.instance().request(ProductOutListActivity.this, Urls.getNoticeListUrl(), this, HttpRequest.POST, GET_PROD_LIST, valuePairs,
                new ProdNoticeListHandler());
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mProdNoticeInfoList.clear();
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
        else if (v == btnSubmit)
        {
            mTid = etTaskId.getText().toString();

            if (StringUtils.stringIsEmpty(mTid))
            {
                ToastUtil.show(this, "请输入任务单号");
                return;
            }


            mProdNoticeInfoList.clear();
            pn = 1;
            mRefreshStatus = 0;
            getProductList();
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

        if (GET_PROD_LIST.equals(action))
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

    public void toScan()
    {
        Intent intent = new Intent(ProductOutListActivity.this,
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
                    startActivity(new Intent(ProductOutListActivity.this, MaterialOutActivity.class).putExtra("PRODUCT_ID", content).putExtra("T_ID", t_id));

            }
        }
    }


}
