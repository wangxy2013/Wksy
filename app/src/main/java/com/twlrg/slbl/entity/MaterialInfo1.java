package com.twlrg.slbl.entity;

import java.io.Serializable;

/**
 * 作者：王先云 on 2018/4/4 16:10
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class MaterialInfo1 implements Serializable
{
    private String mete_id;
    private String out_count;
    private String kw;
    private String t_id;
    private String is_in;
    private String kwn;
    private int in_count;
    private String t_itm;
    private String ql_id;
    private String ql_itm;

    public int getIn_count()
    {
        return in_count;
    }

    public void setIn_count(int in_count)
    {
        this.in_count = in_count;
    }

    public String getMete_id()
    {
        return mete_id;
    }

    public void setMete_id(String mete_id)
    {
        this.mete_id = mete_id;
    }

    public String getOut_count()
    {
        return out_count;
    }

    public void setOut_count(String out_count)
    {
        this.out_count = out_count;
    }

    public String getKw()
    {
        return kw;
    }

    public void setKw(String kw)
    {
        this.kw = kw;
    }

    public String getT_id()
    {
        return t_id;
    }

    public void setT_id(String t_id)
    {
        this.t_id = t_id;
    }

    public String getIs_in()
    {
        return is_in;
    }

    public void setIs_in(String is_in)
    {
        this.is_in = is_in;
    }

    public String getKwn()
    {
        return kwn;
    }

    public void setKwn(String kwn)
    {
        this.kwn = kwn;
    }

    public String getT_itm()
    {
        return t_itm;
    }

    public void setT_itm(String t_itm)
    {
        this.t_itm = t_itm;
    }

    public String getQl_id()
    {
        return ql_id;
    }

    public void setQl_id(String ql_id)
    {
        this.ql_id = ql_id;
    }

    public String getQl_itm()
    {
        return ql_itm;
    }

    public void setQl_itm(String ql_itm)
    {
        this.ql_itm = ql_itm;
    }
}
