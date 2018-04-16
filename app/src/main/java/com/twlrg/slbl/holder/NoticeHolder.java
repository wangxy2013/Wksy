package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * 出库通知单
 */
public class NoticeHolder extends RecyclerView.ViewHolder
{
    private TextView            mTzdhTv; 
    private TextView            mTzbmTv; 
    private TextView            mTzrqTv; 
    private RelativeLayout      itemLayout;
    private MyItemClickListener listener;

    public NoticeHolder(View rootView, MyItemClickListener listener)
    {
        super(rootView);

        mTzdhTv = (TextView) rootView.findViewById(R.id.tv_tzdh);
        mTzbmTv = (TextView) rootView.findViewById(R.id.tv_tzbm);
        mTzrqTv = (TextView) rootView.findViewById(R.id.tv_tzrq);
        itemLayout = (RelativeLayout)  rootView.findViewById(R.id.rl_item);
        this.listener = listener;
    }


    public void setTaskInfo(TaskInfo mTaskInfo, final int p)
    {
        mTzdhTv.setText("通知单号：" + mTaskInfo.getN_id());
        mTzrqTv.setText("通知日期：" + mTaskInfo.getN_date());
        mTzbmTv.setText("通知部门：" + mTaskInfo.getDep_name());
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
