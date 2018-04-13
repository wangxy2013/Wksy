package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.holder.ProductItemHolder;
import com.twlrg.slbl.holder.ProductOutItemHolder;

import java.util.List;

/**
 */
public class ProductOutItemAdapter extends RecyclerView.Adapter<ProductOutItemHolder>
{

    private List<ProductInfo> list;
    private Context           mContext;

    public ProductOutItemAdapter(List<ProductInfo> list, Context mContext)
    {
        this.list = list;
        this.mContext = mContext;

    }

    @Override
    public ProductOutItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_out, parent, false);
        ProductOutItemHolder mHolder = new ProductOutItemHolder(itemView);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(ProductOutItemHolder holder, int position)
    {
        ProductInfo mMaterialInfo = list.get(position);
        holder.setTaskInfo(mMaterialInfo);
    }

    @Override
    public int getItemCount()
    {

        return list.size();


    }
}
