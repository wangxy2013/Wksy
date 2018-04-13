package com.twlrg.slbl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.holder.ProductItemHolder;

import java.util.List;

/**
 */
public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemHolder>
{

    private List<ProductInfo> list;
    private Context           mContext;

    public ProductItemAdapter(List<ProductInfo> list, Context mContext)
    {
        this.list = list;
        this.mContext = mContext;

    }

    @Override
    public ProductItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ProductItemHolder mHolder = new ProductItemHolder(itemView);
        return mHolder;
    }


    @Override
    public void onBindViewHolder(ProductItemHolder holder, int position)
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
