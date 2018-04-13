package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProdNoticeInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.holder.ProdNoticeHolder;
import com.twlrg.slbl.holder.TaskHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;

/**
 */
public class ProdNoticeAdapter extends RecyclerView.Adapter<ProdNoticeHolder>
{

    private List<ProdNoticeInfo> list;
    private Context              mContext;
    private MyItemClickListener  listener1;
    private  MyItemClickListener listener2;
    public ProdNoticeAdapter(List<ProdNoticeInfo> list, Context mContext, MyItemClickListener listener1, MyItemClickListener listener2)
    {
        this.list = list;
        this.mContext = mContext;
        this.listener1 = listener1;
        this.listener2 = listener2;
    }

    @Override
    public ProdNoticeHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prod_notice_out, parent, false);
        ProdNoticeHolder mHolder = new ProdNoticeHolder(itemView,listener1,listener2);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(ProdNoticeHolder holder, int position)
    {
        ProdNoticeInfo mProdNoticeInfo = list.get(position);
        holder.setProdNoticeInfo(mProdNoticeInfo, mContext,position);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
