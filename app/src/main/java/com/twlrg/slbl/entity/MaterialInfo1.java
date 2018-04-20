package com.twlrg.slbl.entity;

import java.io.Serializable;

/**
 * 作者：王先云 on 2018/4/4 16:10
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class MaterialInfo1 implements Serializable
{

    private String pro_no;
    private String pro_name;

    private String ql_no;
    private String pre_itm;
    private String prd_no;
    private String bat_no;
    private String mo_no;
    private String est_itm;


    private String mete_id;
    private String out_count;
    private String kw;
    private String t_id;
    private String is_in;
    private String kwn;
    private int    in_count;
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

    public String getQl_no()
    {
        return ql_no;
    }

    public void setQl_no(String ql_no)
    {
        this.ql_no = ql_no;
    }

    public String getPre_itm()
    {
        return pre_itm;
    }

    public void setPre_itm(String pre_itm)
    {
        this.pre_itm = pre_itm;
    }

    public String getPrd_no()
    {
        return prd_no;
    }

    public void setPrd_no(String prd_no)
    {
        this.prd_no = prd_no;
    }

    public String getBat_no()
    {
        return bat_no;
    }

    public void setBat_no(String bat_no)
    {
        this.bat_no = bat_no;
    }

    public String getMo_no()
    {
        return mo_no;
    }

    public void setMo_no(String mo_no)
    {
        this.mo_no = mo_no;
    }

    public String getEst_itm()
    {
        return est_itm;
    }

    public void setEst_itm(String est_itm)
    {
        this.est_itm = est_itm;
    }

    public String getPro_no()
    {
        return pro_no;
    }

    public void setPro_no(String pro_no)
    {
        this.pro_no = pro_no;
    }

    public String getPro_name()
    {
        return pro_name;
    }

    public void setPro_name(String pro_name)
    {
        this.pro_name = pro_name;
    }
}
