package com.twlrg.slbl.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * Date:
 */
public class MaterialOutHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mProductIdTv;
    private TextView mProductAmountTv;



    public MaterialOutHolder(View rootView)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mProductIdTv = (TextView) rootView.findViewById(R.id.tv_product_id);
        mProductAmountTv = (TextView) rootView.findViewById(R.id.tv_product_amount);

    }


    public void setTaskInfo(MaterialInfo mMaterialInfo)
    {
        mProductNameTv.setText("物料类别名：" + mMaterialInfo.getType_name());
        mProductIdTv.setText("物料类别号：" + mMaterialInfo.getType_no());
        mProductAmountTv.setText("出库数量：" + mMaterialInfo.getCount());
    }

}
