package com.twlrg.slbl.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * Date:
 */
public class ProductInHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mProductIdTv;
    private TextView mRroNoTv;
    private TextView mProductAmountTv;
    private RelativeLayout mItemLayout;
    private Button   mOutBtn;

    private MyItemClickListener listener1, listener2;

    public ProductInHolder(View rootView, MyItemClickListener listener1, MyItemClickListener listener2)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mProductIdTv = (TextView) rootView.findViewById(R.id.tv_product_id);
        mProductAmountTv = (TextView) rootView.findViewById(R.id.tv_product_amount);
        mItemLayout = (RelativeLayout) rootView.findViewById(R.id.rl_item);
        mRroNoTv= (TextView) rootView.findViewById(R.id.tv_pro_no);
        mOutBtn = (Button) rootView.findViewById(R.id.btn_out);
        this.listener1 = listener1;
        this.listener2 = listener2;
    }


    public void setTaskInfo(TaskInfo mTaskInfo, Context mContext, final int p)
    {
        mProductNameTv.setText("产品名称：" + mTaskInfo.getPro_name());
        mProductIdTv.setText("任务单号：" + mTaskInfo.getT_id());
        mProductAmountTv.setText("产品数量：" + mTaskInfo.getPro_count());
        mRroNoTv.setText("产品编号：" + mTaskInfo.getPro_no());
        mItemLayout.setOnClickListener(new View.OnClickListener()
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
