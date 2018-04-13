package com.twlrg.slbl.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2018/4/13 14:09
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ScanInCheckInfo
{
    private String kw;
    private String kwn;
    private String count;
    private int rest;


    public ScanInCheckInfo(JSONObject obj)
    {
        this.kw = obj.optString("KW");
        this.kwn = obj.optString("KWN");
        this.count = obj.optString("COUNT");
        this.rest = obj.optInt("REST");
    }

    public String getKwn()
    {
        return kwn;
    }

    public void setKwn(String kwn)
    {
        this.kwn = kwn;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public int getRest()
    {
        return rest;
    }

    public void setRest(int rest)
    {
        this.rest = rest;
    }

    public String getKw()
    {
        return kw;
    }

    public void setKw(String kw)
    {
        this.kw = kw;
    }
}
