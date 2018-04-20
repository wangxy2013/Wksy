package com.twlrg.slbl.activity;

import android.content.Intent;
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
import com.twlrg.slbl.adapter.CksqdAdapter;
import com.twlrg.slbl.adapter.NoticeSelectAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.http.DataRequest;
import com.twlrg.slbl.http.HttpRequest;
import com.twlrg.slbl.http.IRequestListener;
import com.twlrg.slbl.json.KWListHandler;
import com.twlrg.slbl.json.TaskListHandler;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.listener.MyOnClickListener;
import com.twlrg.slbl.utils.ConstantUtil;
import com.twlrg.slbl.utils.DialogUtils;
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
 * 成品申请单号
 */
public class NoticeListSelectActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, View.OnClickListener, IRequestListener
{
    @BindView(R.id.btn_submit)
    Button   btnSubmit;
    @BindView(R.id.et_task_id)
    EditText etTaskId;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_department)
    TextView tvDepartment;

    @BindView(R.id.btn_submit3)
    Button   btnRestart;
    private ImageView                 mBackIv;
    private TextView                  mTitleTv;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView              mRecyclerView;

    private NoticeSelectAdapter mTaskAdapter;
    private List<TaskInfo> mCksqdList = new ArrayList<>();


    private List<KWInfo> kwInfoList = new ArrayList<>();
    private String kw_code="", kw_name;


    private int pn = 1;
    private int mRefreshStatus;
    private static final String GET_TASK_LIST   = "get_list";
    private static final String GET_WV          = "GET_WV";
    private static final int    REQUEST_SUCCESS = 0x01;
    private static final int    REQUEST_FAIL    = 0x02;
    private static final int    GET_WV_SUCCESS  = 0x04;
    private              String mTid            = "";
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
                    mCksqdList.addAll(mTaskListHandler.getTaskInfoList());
                    mTaskAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(NoticeListSelectActivity.this, msg.obj.toString());

                    break;

                case GET_WV_SUCCESS:
                    KWListHandler mKWListHandler = (KWListHandler) msg.obj;
                    kwInfoList.clear();
                    kwInfoList.addAll(mKWListHandler.getKWInfoList());
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
        setContentView(R.layout.activity_notice_select);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.pullToRefreshRecyclerView);
        mPullToRefreshRecyclerView.setPullLoadEnabled(true);
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        getKv();
    }

    private void getKv()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("CODE", "BM");
        DataRequest.instance().request(this, Urls.getKVUrl(), this, HttpRequest.POST, GET_WV, valuePairs,
                new KWListHandler());

    }

    @Override
    protected void initEvent()
    {
        btnRestart.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
        tvTime.setOnClickListener(this);
        tvDepartment.setOnClickListener(this);

    }

    @Override
    protected void initViewData()
    {

        mTaskAdapter = new NoticeSelectAdapter(mCksqdList, NoticeListSelectActivity.this, new MyItemClickListener()
        {

            //详情
            @Override
            public void onItemClick(View view, int position)
            {

                startActivity(new Intent(NoticeListSelectActivity.this, ProductOutListActivity.class).putExtra("N_ID", mCksqdList.get(position).getN_id()));
            }
        });
        mRecyclerView.setAdapter(mTaskAdapter);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void getProductList()
    {
        mCksqdList.clear();
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("N_ID", mTid);
        valuePairs.put("N_DEP", kw_code);
        valuePairs.put("N_DD", tvTime.getText().toString());
        DataRequest.instance().request(NoticeListSelectActivity.this, Urls.getNoticeSelectListUrl(), this, HttpRequest.POST, GET_TASK_LIST, valuePairs,
                new TaskListHandler());
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mCksqdList.clear();
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

            mCksqdList.clear();
            pn = 1;
            mRefreshStatus = 0;
            getProductList();
        }
        else if (v == tvTime)
        {
            DialogUtils.showTimeDialog(NoticeListSelectActivity.this, new MyOnClickListener.OnSubmitListener()
            {
                @Override
                public void onSubmit(String content)
                {
                    tvTime.setText(content);
                }
            });
        }
        else if (v == tvDepartment)
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
                            tvDepartment.setText(kw_name);
                        }
                    }
                });
            }
        }
        else  if(v == btnRestart)
        {
            tvDepartment.setText("");
            kw_code="";
            kw_name="";
            mTid="";
            etTaskId.setText("");
            tvTime.setText("");
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

        if (GET_TASK_LIST.equals(action))
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
        else   if (GET_WV.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(GET_WV_SUCCESS, obj));
            }

        }
    }



}
