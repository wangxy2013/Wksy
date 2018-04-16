package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.holder.CksqdHolder;
import com.twlrg.slbl.holder.NoticeHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;

/**
 */
public class NoticeSelectAdapter extends RecyclerView.Adapter<NoticeHolder>
{

    private List<TaskInfo>      list;
    private Context             mContext;
    private MyItemClickListener listener1;

    public NoticeSelectAdapter(List<TaskInfo> list, Context mContext, MyItemClickListener listener1)
    {
        this.list = list;
        this.mContext = mContext;
        this.listener1 = listener1;
    }

    @Override
    public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_notice, parent, false);
        NoticeHolder mHolder = new NoticeHolder(itemView, listener1);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(NoticeHolder holder, int position)
    {
        TaskInfo mTaskInfo = list.get(position);
        holder.setTaskInfo(mTaskInfo, position);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
