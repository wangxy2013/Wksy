package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProductInfo;


/**
 * Date:
 */
public class ProductOutItemHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mSNTv;
    private TextView mKwTv;

    public ProductOutItemHolder(View rootView)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mSNTv = (TextView) rootView.findViewById(R.id.tv_sn);
        mKwTv = (TextView) rootView.findViewById(R.id.tv_kw);
    }


    public void setTaskInfo(ProductInfo mProductInfo)
    {
        mSNTv.setText("成品SN码：" + mProductInfo.getSn());
        mProductNameTv.setText("产品名称：" + mProductInfo.getPro_name());
        mKwTv.setText("出库库位：" + mProductInfo.getKwn());
    }

}
