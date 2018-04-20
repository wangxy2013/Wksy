package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.MaterialInfo1;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.utils.StringUtils;


/**
 * Date:
 */
public class MaterOut1Holder extends RecyclerView.ViewHolder
{
    private TextView mMateIdTv;
    private TextView mKwTv;
    private TextView mCountTv;

    private TextView            mProNoTv;
    private TextView            mProNameTv;
    private Button              mDelBtn;
    private MyItemClickListener listener;

    public MaterOut1Holder(View rootView, MyItemClickListener listener)
    {
        super(rootView);

        mMateIdTv = (TextView) rootView.findViewById(R.id.tv_mete_id);
        mKwTv = (TextView) rootView.findViewById(R.id.tv_kw);
        mProNoTv = (TextView) rootView.findViewById(R.id.tv_pro_no);
        mProNameTv = (TextView) rootView.findViewById(R.id.tv_pro_name);
        mDelBtn = (Button) rootView.findViewById(R.id.btn_del);
        mCountTv = (TextView) rootView.findViewById(R.id.tv_count);
        this.listener = listener;
    }


    public void setMaterInfo(MaterialInfo1 mMaterialInfo1, final int p)
    {
        mMateIdTv.setText("条码编号：" + mMaterialInfo1.getMete_id());

        if (StringUtils.stringIsEmpty(mMaterialInfo1.getPro_no()))
        {
            mProNoTv.setVisibility(View.GONE);
        }
        else
        {
            mProNoTv.setVisibility(View.VISIBLE);
            mProNoTv.setText("物料编号：" + mMaterialInfo1.getPro_no());
        }

        if (StringUtils.stringIsEmpty(mMaterialInfo1.getPro_name()))
        {
            mProNameTv.setVisibility(View.GONE);
        }
        else
        {
            mProNameTv.setVisibility(View.VISIBLE);
            mProNameTv.setText("物料名称：" + mMaterialInfo1.getPro_name());
        }

        if ("1".equals(mMaterialInfo1.getIs_in()))
        {
            mKwTv.setText("入库库位：" + mMaterialInfo1.getKwn());
            mCountTv.setText("入库数量：" + mMaterialInfo1.getIn_count());
        }
        else
        {
            mKwTv.setText("出库库位：" + mMaterialInfo1.getKwn());
            mCountTv.setText("出库数量：" + mMaterialInfo1.getOut_count());
            if (StringUtils.stringIsEmpty(mMaterialInfo1.getOut_count()))
            {
                mCountTv.setVisibility(View.GONE);
            }
        }


        mDelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(v, p);
            }
        });
    }

}
