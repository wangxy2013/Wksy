package com.twlrg.slbl.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2018/4/1 13:11
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class KWInfo
{
    private String kw_code;//01DZ0
    private String kw_name;//原料廢品倉

    public KWInfo(JSONObject obj)
    {
        this.kw_code = obj.optString("KW_CODE");
        this.kw_name = obj.optString("KW_NAME");
    }

    public String getKw_code()
    {
        return kw_code;
    }

    public void setKw_code(String kw_code)
    {
        this.kw_code = kw_code;
    }

    public String getKw_name()
    {
        return kw_name;
    }

    public void setKw_name(String kw_name)
    {
        this.kw_name = kw_name;
    }
}
