package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.MaterialInfo1;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.holder.MaterOut1Holder;
import com.twlrg.slbl.holder.SNHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;


public class MaterialAdapter extends RecyclerView.Adapter<MaterOut1Holder>
{

    private List<MaterialInfo1> list;

    private Context             mContext;
    private MyItemClickListener listener;

    public MaterialAdapter(List<MaterialInfo1> list, Context mContext, MyItemClickListener listener)
    {
        this.list = list;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public MaterOut1Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mater_out_1, parent, false);
        MaterOut1Holder mGroupBlogHolder = new MaterOut1Holder(itemView, listener);
        return mGroupBlogHolder;
    }


    @Override
    public void onBindViewHolder(MaterOut1Holder holder, int position)
    {
        holder.setMaterInfo(list.get(position), position);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
