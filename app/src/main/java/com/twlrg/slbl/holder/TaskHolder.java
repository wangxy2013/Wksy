package com.twlrg.slbl.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * Date:
 */
public class TaskHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mProductIdTv;
    private TextView mPrd_noTv;
    private TextView mqLItmTv;
    private TextView mProductAmountTv;
    private Button   mDetailBtn;
    private Button   mOutBtn;

    private MyItemClickListener listener1, listener2;

    public TaskHolder(View rootView, MyItemClickListener listener1, MyItemClickListener listener2)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mProductIdTv = (TextView) rootView.findViewById(R.id.tv_product_id);
        mqLItmTv = (TextView) rootView.findViewById(R.id.tv_ql_itm);
        mProductAmountTv = (TextView) rootView.findViewById(R.id.tv_product_amount);
        mDetailBtn = (Button) rootView.findViewById(R.id.btn_detail);
        mPrd_noTv = (TextView) rootView.findViewById(R.id.tv_prd_no);
        mOutBtn = (Button) rootView.findViewById(R.id.btn_out);
        this.listener1 = listener1;
        this.listener2 = listener2;
    }


    public void setTaskInfo(TaskInfo mTaskInfo, Context mContext, final int p)
    {
        mProductNameTv.setText("物料名称：" + mTaskInfo.getPro_name());
        mProductIdTv.setText("申请单号：" + mTaskInfo.getQl_id());
        mProductAmountTv.setText("需求数量：" + mTaskInfo.getPro_count());
        mqLItmTv.setText("项次号：" + mTaskInfo.getQl_itm());
        mPrd_noTv.setText("物料编号：" + mTaskInfo.getPro_no());
        mDetailBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener1.onItemClick(v, p);
            }
        });


        mOutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener2.onItemClick(v, p);
            }
        });
    }

}
