package com.twlrg.slbl.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author
 */
public class ConfigManager
{
    /**
     * instance
     */
    private static ConfigManager     sConfigManager     = null;
    /**
     * mCtx
     */
    private        Context           mCtx               = null;
    /**
     * SharedPreferences
     */
    private        SharedPreferences mSharedPreferences = null;
    /**
     * config
     */
    private static String            CONFIG_NAME        = "config";


    private static final String USER_PWD    = "user_pwd";
    private static final String USER_NAME   = "user_name";// 用户名
    private static final String USER_ID     = "user_id";
    private static final String PREF_UUID   = "miei";
    private static final String UNIQUE_CODE = "unique_code";
    private static final String USER_MOBILE = "mobile";
    private static final String IS_SAVE_PWD = "is_save_pwd";

    private static final String IS_FRIST_LOGING = "IS_FRIST_LOGING";
    private static final String USER_TYPE       = "user_type";
    private static final String TOKEN           = "token";
    private static final String ROLE_NAME       = "role_name";
    private static final String USER_IMG        = "user_img";
    private static final String USER_SCORE      = "user_score";
    private static final String BUS_ID          = "bus_id";
    private static final String REGISTER_ID     = "register_id";
    private static final String USER_POWER          = "USER_POWER";
    private static final String  BASE_URL ="base_url";


    /**
     * 返回实例
     *
     * @return
     */
    public static ConfigManager instance()
    {
        if (sConfigManager == null)
        {
            sConfigManager = new ConfigManager();
        }

        return sConfigManager;
    }

    /**
     * init shared Preferences
     *
     * @param context
     */
    public void init(Context context)
    {
        mCtx = context;
        mSharedPreferences = context.getSharedPreferences(CONFIG_NAME, 0);

    }


    public String getUserPwd()
    {
        return mSharedPreferences.getString(USER_PWD, "");
    }

    public void setUserPwd(String pwd)
    {
        mSharedPreferences.edit().putString(USER_PWD, pwd).commit();
        return;
    }


    // 用户名
    public String getUserName()
    {
        return mSharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String userName)
    {
        mSharedPreferences.edit().putString(USER_NAME, userName).commit();
    }


    public String getUserID()
    {
        return mSharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String userId)
    {
        mSharedPreferences.edit().putString(USER_ID, userId).commit();
        return;
    }

    public void setUUID(String uuid)
    {
        mSharedPreferences.edit().putString(PREF_UUID, uuid).commit();
    }

    public String getUUID()
    {
        return mSharedPreferences.getString(PREF_UUID, null);
    }


    public String getUniqueCode()
    {
        return mSharedPreferences.getString(UNIQUE_CODE, "");
    }

    public void setUniqueCode(String uniqueCode)
    {
        mSharedPreferences.edit().putString(UNIQUE_CODE, uniqueCode).commit();
    }

    public String getMobile()
    {
        return mSharedPreferences.getString(USER_MOBILE, "");
    }

    public void setMobile(String mobile)
    {
        mSharedPreferences.edit().putString(USER_MOBILE, mobile).commit();
    }


    public boolean getIsSavePwd()
    {
        return mSharedPreferences.getBoolean(IS_SAVE_PWD, false);
    }

    public void setIsSavePwd(boolean status)
    {
        mSharedPreferences.edit().putBoolean(IS_SAVE_PWD, status).commit();
    }

    public boolean getIsFristLogin()
    {
        return mSharedPreferences.getBoolean(IS_FRIST_LOGING, true);
    }

    public void setIsFristLogin(boolean status)
    {
        mSharedPreferences.edit().putBoolean(IS_FRIST_LOGING, status).commit();
    }


    public String getToken()
    {
        return mSharedPreferences.getString(TOKEN, "");
    }

    public void setToken(String token)
    {
        mSharedPreferences.edit().putString(TOKEN, token).commit();
    }


    public String getRoleName()
    {
        return mSharedPreferences.getString(ROLE_NAME, "");
    }

    public void setRoleName(String mRoleName)
    {
        mSharedPreferences.edit().putString(ROLE_NAME, mRoleName).commit();
    }

    public String getUserImg()
    {
        return mSharedPreferences.getString(USER_IMG, "");
    }

    public void setUserImg(String mUserImg)
    {
        mSharedPreferences.edit().putString(USER_IMG, mUserImg).commit();
    }

    public String getUserScore()
    {
        return mSharedPreferences.getString(USER_SCORE, "0");
    }

    public void setUserScore(String mUserScore)
    {
        mSharedPreferences.edit().putString(USER_SCORE, mUserScore).commit();
    }

    public String getBusId()
    {
        return mSharedPreferences.getString(BUS_ID, "");
    }

    public void setBusId(String mBusId)
    {
        mSharedPreferences.edit().putString(BUS_ID, mBusId).commit();
    }


    public String getRegisterId()
    {
        return mSharedPreferences.getString(REGISTER_ID, "");
    }

    public void setRegisterId(String mRegisterId)
    {
        mSharedPreferences.edit().putString(REGISTER_ID, mRegisterId).commit();
    }



    public String getBaseUrl()
    {
        return mSharedPreferences.getString(BASE_URL, "http://180.100.223.13:8088/cloud");
    }
//    public String getBaseUrl()
//    {
//        return mSharedPreferences.getString(BASE_URL, "http://192.168.1.110:8080/cloud");
//    }
    public void setBaseUrl(String base_url)
    {
        mSharedPreferences.edit().putString(BASE_URL, base_url).commit();
    }

    public String getUserPower()
    {
        return mSharedPreferences.getString(USER_POWER, "1111");
    }
    //    public String getBaseUrl()
//    {
//        return mSharedPreferences.getString(BASE_URL, "http://192.168.1.110:8080/cloud");
//    }
    public void setUserPower(String base_url)
    {
        mSharedPreferences.edit().putString(USER_POWER, base_url).commit();
    }
}

