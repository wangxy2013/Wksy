package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.holder.SNHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;


public class SNAdapter extends RecyclerView.Adapter<SNHolder>
{

    private List<ProInfo> list;

    private Context             mContext;
    private MyItemClickListener listener;

    public SNAdapter(List<ProInfo> list, Context mContext, MyItemClickListener listener)
    {
        this.list = list;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public SNHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sn, parent, false);
        SNHolder mGroupBlogHolder = new SNHolder(itemView, listener);
        return mGroupBlogHolder;
    }


    @Override
    public void onBindViewHolder(SNHolder holder, int position)
    {
        holder.setProInfo(list.get(position), position);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
