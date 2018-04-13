package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.holder.MaterialOutHolder;
import com.twlrg.slbl.listener.MyItemClickListener;

import java.util.List;

/**
 */
public class MaterialOutAdapter extends RecyclerView.Adapter<MaterialOutHolder>
{

    private List<MaterialInfo> list;
    private Context            mContext;

    public MaterialOutAdapter(List<MaterialInfo> list, Context mContext)
    {
        this.list = list;
        this.mContext = mContext;

    }

    @Override
    public MaterialOutHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, parent, false);
        MaterialOutHolder mHolder = new MaterialOutHolder(itemView);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(MaterialOutHolder holder, int position)
    {
        MaterialInfo mMaterialInfo = list.get(position);
        holder.setTaskInfo(mMaterialInfo);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
