package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.holder.ProductInHolder;
import com.twlrg.slbl.holder.TaskHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;

/**
 */
public class ProductTaskAdapter extends RecyclerView.Adapter<ProductInHolder>
{

    private List<TaskInfo> list;
    private Context            mContext;
    private MyItemClickListener listener1;
    private  MyItemClickListener listener2;
    public ProductTaskAdapter(List<TaskInfo> list, Context mContext, MyItemClickListener listener1, MyItemClickListener listener2)
    {
        this.list = list;
        this.mContext = mContext;
        this.listener1 = listener1;
        this.listener2 = listener2;
    }

    @Override
    public ProductInHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_in, parent, false);
        ProductInHolder mHolder = new ProductInHolder(itemView,listener1,listener2);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(ProductInHolder holder, int position)
    {
        TaskInfo mTaskInfo = list.get(position);
        holder.setTaskInfo(mTaskInfo, mContext,position);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
