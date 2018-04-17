package com.twlrg.slbl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twlrg.slbl.R;
import com.twlrg.slbl.entity.ProInfo;
import com.twlrg.slbl.listener.MyItemClickListener;


/**
 * Date:
 */
public class SNHolder extends RecyclerView.ViewHolder
{
    private TextView            mSnTv;
    private TextView            mKwnTv;
    private TextView            mBatchTv;
    private Button              mDelBtn;
    private MyItemClickListener listener;

    public SNHolder(View rootView, MyItemClickListener listener)
    {
        super(rootView);

        mSnTv = (TextView) rootView.findViewById(R.id.tv_sn);
        mKwnTv = (TextView) rootView.findViewById(R.id.tv_kwn);
        mBatchTv= (TextView) rootView.findViewById(R.id.tv_batch);
        mDelBtn = (Button) rootView.findViewById(R.id.btn_del);
        this.listener = listener;
    }


    public void setProInfo(ProInfo mProInfo, final int p)
    {
         mSnTv.setText("SN码：" + mProInfo.getSn());
        mKwnTv.setText("库位：" + mProInfo.getKwn());
        mBatchTv.setText("批次号：" + mProInfo.getBat_no());
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
