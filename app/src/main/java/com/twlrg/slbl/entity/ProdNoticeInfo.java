package com.twlrg.slbl.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2018/4/3 16:57
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ProdNoticeInfo implements Serializable
{
    private String n_id;
    private String kw;
    private String n_date;

    private String n_itm;
    private String so_id;
    private String so_itm;
    private String pro_name;
    private String pro_count;
    private String client_name;
    private String kwn;
    private String client_id;

    public ProdNoticeInfo(JSONObject obj)
    {
        this.client_id = obj.optString("CLIENT_ID");
        this.kwn = obj.optString("KWN");
        this.client_name = obj.optString("CLIENT_NAME");
        this.so_itm = obj.optString("SO_ITM");
        this.so_id = obj.optString("SO_ID");
        this.so_id = obj.optString("SO_ID");
        this.n_id = obj.optString("N_ID");
        this.pro_name = obj.optString("PRO_NAME");
        this.pro_count = obj.optString("PRO_COUNT");
        this.kw = obj.optString("KW");
        this.n_itm = obj.optString("N_ITM");

    }


    public String getN_id()
    {
        return n_id;
    }

    public void setN_id(String n_id)
    {
        this.n_id = n_id;
    }

    public String getPro_name()
    {
        return pro_name;
    }

    public void setPro_name(String pro_name)
    {
        this.pro_name = pro_name;
    }

    public String getPro_count()
    {
        return pro_count;
    }

    public void setPro_count(String pro_count)
    {
        this.pro_count = pro_count;
    }

    public String getKw()
    {
        return kw;
    }

    public void setKw(String kw)
    {
        this.kw = kw;
    }

    public String getN_date()
    {
        return n_date;
    }

    public void setN_date(String n_date)
    {
        this.n_date = n_date;
    }

    public String getN_itm()
    {
        return n_itm;
    }

    public void setN_itm(String n_itm)
    {
        this.n_itm = n_itm;
    }

    public String getSo_id()
    {
        return so_id;
    }

    public void setSo_id(String so_id)
    {
        this.so_id = so_id;
    }

    public String getSo_itm()
    {
        return so_itm;
    }

    public void setSo_itm(String so_itm)
    {
        this.so_itm = so_itm;
    }

    public String getClient_name()
    {
        return client_name;
    }

    public void setClient_name(String client_name)
    {
        this.client_name = client_name;
    }

    public String getKwn()
    {
        return kwn;
    }

    public void setKwn(String kwn)
    {
        this.kwn = kwn;
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }
}
