package com.twlrg.slbl;

import android.app.Application;


import com.twlrg.slbl.utils.APPUtils;
import com.twlrg.slbl.utils.ConfigManager;
import com.twlrg.slbl.utils.StringUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：王先云 on 2016/9/19 14:46
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class WKSYApplication extends Application
{
    private static WKSYApplication instance;
    public static WKSYApplication getInstance() {return instance;}
    private String          latitude, longitude;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        APPUtils.configImageLoader(getApplicationContext());
        ConfigManager.instance().init(this);

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
    }


    public boolean isLogin()
    {
        if (StringUtils.stringIsEmpty(ConfigManager.instance().getUserID()))
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }
}
