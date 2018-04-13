package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.ProductInfo;


/**
 * Date:
 */
public class ProductItemHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mProductIdTv;


    public ProductItemHolder(View rootView)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mProductIdTv = (TextView) rootView.findViewById(R.id.tv_sn);

    }


    public void setTaskInfo(ProductInfo mProductInfo)
    {
        mProductNameTv.setText("成品SN码：" + mProductInfo.getSn());
        mProductIdTv.setText("产品名称：" + mProductInfo.getPro_name());
    }

}
