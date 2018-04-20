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
import com.twlrg.slbl.adapter.ProductTaskAdapter;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
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
 * 成品列表
 */
public class ProductInListActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, View.OnClickListener, IRequestListener
{
    @BindView(R.id.btn_submit)
    Button   btnSubmit;
    @BindView(R.id.et_task_id)
    EditText etTaskId;
    private ImageView                 mBackIv;
    private TextView                  mTitleTv;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView              mRecyclerView;

    private ProductTaskAdapter mTaskAdapter;
    private List<TaskInfo> mMaterialInfoList = new ArrayList<>();

    private int pn = 1;
    private int mRefreshStatus;
    private static final String GET_PRODUCT_LIST = "GET_PRODUCT_LIST";

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
                    TaskListHandler mTaskListHandler = (TaskListHandler) msg.obj;
                    mMaterialInfoList.addAll(mTaskListHandler.getTaskInfoList());
                    mTaskAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProductInListActivity.this, msg.obj.toString());

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
        setContentView(R.layout.activity_product_list);
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
        //        etTaskId.addTextChangedListener(new TextWatcher()
        //        {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int count, int after)
        //            {
        //
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before, int count)
        //            {
        //
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s)
        //            {
        //                if (s.length() == 0)
        //                {
        //                    mTid = "";
        //                    mMaterialInfoList.clear();
        //                    pn = 1;
        //                    mRefreshStatus = 0;
        //                    getProductList();
        //
        //                }
        //            }
        //        });
    }

    @Override
    protected void initViewData()
    {

        mTaskAdapter = new ProductTaskAdapter(mMaterialInfoList, ProductInListActivity.this, new MyItemClickListener()
        {

            //详情
            @Override
            public void onItemClick(View view, int position)
            {

                Bundle b = new Bundle();
                b.putSerializable("TASKINFO", mMaterialInfoList.get(position));
                startActivity(new Intent(ProductInListActivity.this, ProductInDetailActivity.class).putExtras(b));
            }
        }, new MyItemClickListener()
        {
            //出库操作
            @Override
            public void onItemClick(View view, int position)
            {
                t_id = mMaterialInfoList.get(position).getT_id();
                Bundle b = new Bundle();
                b.putSerializable("TASKINFO", mMaterialInfoList.get(position));
                startActivity(new Intent(ProductInListActivity.this, ProductInActivity.class).putExtras(b));
                //                startActivity(new Intent(ProductInListActivity.this, ProductInActivity.class)
                //                        .putExtra("T_ID", t_id)
                //                        .putExtra("PRD_NO", mMaterialInfoList.get(position).getPro_no()) );


            }
        });
        mRecyclerView.setAdapter(mTaskAdapter);


    }

    int count = 0;

    @Override
    protected void onResume()
    {
        super.onResume();
        count++;
        if (count > 0)
        {
            mMaterialInfoList.clear();
            pn = 1;
            getProductList();
        }
    }

    private void getProductList()
    {
        Map<String, String> valuePairs = new HashMap<>();

        valuePairs.put("T_ID", mTid);
        valuePairs.put("PAGE", pn + "");
        valuePairs.put("PAGESIZE", "15");
        DataRequest.instance().request(ProductInListActivity.this, Urls.getMesTaskListUrl(), this, HttpRequest.POST, GET_PRODUCT_LIST, valuePairs,
                new TaskListHandler());
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
        else if (v == btnSubmit)
        {
            mTid = etTaskId.getText().toString();

            //            if (StringUtils.stringIsEmpty(mTid))
            //            {
            //                ToastUtil.show(this, "请输入任务单号");
            //                return;
            //            }


            mMaterialInfoList.clear();
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
