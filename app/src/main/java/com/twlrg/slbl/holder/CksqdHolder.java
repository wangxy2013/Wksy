package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * 出库申请单
 */
public class CksqdHolder extends RecyclerView.ViewHolder
{
    private TextView            mSqdhTv;
    private TextView            mSqsjTv;
    private TextView            mSqbmTv;
    private RelativeLayout      itemLayout;
    private MyItemClickListener listener;

    public CksqdHolder(View rootView, MyItemClickListener listener)
    {
        super(rootView);

        mSqdhTv = (TextView) rootView.findViewById(R.id.tv_sqdh);
        mSqsjTv = (TextView) rootView.findViewById(R.id.tv_sqrq);
        mSqbmTv = (TextView) rootView.findViewById(R.id.tv_sqbm);
        itemLayout = (RelativeLayout)  rootView.findViewById(R.id.rl_item);
        this.listener = listener;
    }


    public void setTaskInfo(TaskInfo mTaskInfo, final int p)
    {
        mSqdhTv.setText("申请单号：" + mTaskInfo.getQl_id());
        mSqsjTv.setText("申请日期：" + mTaskInfo.getQl_date());
        mSqbmTv.setText("申请部门：" + mTaskInfo.getDep_name());
        itemLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(v, p);
            }
        });
    }

}
