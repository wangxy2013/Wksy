package com.twlrg.slbl.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProdNoticeInfo;
import com.twlrg.slbl.entity.TaskInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * Date:
 */
public class ProdNoticeHolder extends RecyclerView.ViewHolder
{
    private TextView mProductNameTv;
    private TextView mN_ITMTv;
    private TextView mPrdNoTv;
    private TextView mProductIdTv;
    private TextView mProductAmountTv;

    private TextView mKwTv;
    private RelativeLayout mItemLayout;
    private Button   mOutBtn;

    private MyItemClickListener listener1, listener2;

    public ProdNoticeHolder(View rootView, MyItemClickListener listener1, MyItemClickListener listener2)
    {
        super(rootView);

        mProductNameTv = (TextView) rootView.findViewById(R.id.tv_product_name);
        mProductIdTv = (TextView) rootView.findViewById(R.id.tv_product_id);
        mProductAmountTv = (TextView) rootView.findViewById(R.id.tv_product_amount);
        mKwTv = (TextView) rootView.findViewById(R.id.tv_kw);
        mN_ITMTv = (TextView) rootView.findViewById(R.id.tv_n_itm);
        mPrdNoTv= (TextView) rootView.findViewById(R.id.tv_prd_no);
        mItemLayout = (RelativeLayout) rootView.findViewById(R.id.rl_item);
        mOutBtn = (Button) rootView.findViewById(R.id.btn_out);
        this.listener1 = listener1;
        this.listener2 = listener2;
    }


    public void setProdNoticeInfo(ProdNoticeInfo mProdNoticeInfo, Context mContext, final int p)
    {
        mProductNameTv.setText("产品名称：" + mProdNoticeInfo.getPro_name());
        mProductIdTv.setText("通知单号：" + mProdNoticeInfo.getN_id());
        mProductAmountTv.setText("未交数量：" + mProdNoticeInfo.getQty_import());
        mKwTv.setText("客户名称：" + mProdNoticeInfo.getCus_name());
        mN_ITMTv.setText("项次号：" + mProdNoticeInfo.getN_itm());
        mPrdNoTv.setText("产品编号：" + mProdNoticeInfo.getPro_no());
        mItemLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener1.onItemClick(v, p);
            }
        });


        mOutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener2.onItemClick(v, p);
            }
        });
    }

}
