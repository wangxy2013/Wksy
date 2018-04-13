package com.twlrg.slbl.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.twlrg.slbl.R;
import com.twlrg.slbl.adapter.CategoryAdapter;
import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.listener.MyItemClickListener;
import com.twlrg.slbl.listener.MyOnClickListener;
import com.twlrg.slbl.widget.NoScrollListView;
import com.twlrg.slbl.widget.wheel.ScreenInfo;
import com.twlrg.slbl.widget.wheel.WheelMain;

import java.util.Calendar;
import java.util.List;

/**
 * 作者：王先云 on 2017/12/1 10:07
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class DialogUtils
{

    public static void showVersionUpdateDialog(Context mContext, String content, final MyOnClickListener.OnSubmitListener listener)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_version, null);
        dialog.setContentView(view);
        TextView mContent = (TextView) view.findViewById(R.id.tv_content);
        Button mSubmitBtn = (Button) view.findViewById(R.id.btn_submit);
        Button mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);

        mContent.setText(content);
        mSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dialog.dismiss();
                listener.onSubmit("1");
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                listener.onSubmit("2");
            }
        });
        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.75);
        mWindow.setGravity(Gravity.CENTER);
        mWindow.setAttributes(lp);
        dialog.setCancelable(false);
        dialog.show();
    }


    public static Dialog showKWInfoDialog(Context mContext, List<KWInfo> list, final MyItemClickListener mMyItemClickListener)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_type, null);
        dialog.setContentView(view);

        NoScrollListView mListView = (NoScrollListView) view.findViewById(R.id.lv_type);
        CategoryAdapter mCategoryAdapter = new CategoryAdapter(mContext, list);
        mListView.setAdapter(mCategoryAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mMyItemClickListener.onItemClick(view, position);
                dialog.dismiss();
            }
        });

        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.75);
        mWindow.setGravity(Gravity.CENTER);
        mWindow.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }

    public static Dialog showTimeDialog(Activity mContext, final MyOnClickListener.OnSubmitListener mClickListener)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_time_choose, null);
        dialog.setContentView(view);
        int year, month, day, hour, min;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        ScreenInfo screenInfo = new ScreenInfo(mContext);

        final WheelMain wheelMain = new WheelMain(view, 1);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.initDateTimePicker(year, month, day, hour, min);

        TextView mCancelTv = (TextView) view.findViewById(R.id.tv_cancel);
        TextView mSubmitTv = (TextView) view.findViewById(R.id.tv_submit);


        mCancelTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dialog.dismiss();
            }
        });

        mSubmitTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mClickListener.onSubmit(wheelMain.getTime());
                dialog.dismiss();
            }
        });


        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = dm.widthPixels;
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }
}
