package com.twlrg.slbl.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2018/4/1 10:11
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TaskInfo implements Serializable
{


    private String bat_no;
    private String est_itm;//54,
    private String mo_no;//MO84130001",
    private String prd_name;//電芯",
    private String prd_no;//DX010107",
    private String pre_itm;//1,
    private String ql_dd;//2018-04-13",
    private String ql_no;//QL84130001",
    private String qty;// 1,
    private int qty_import;// 1,
    private String qty_rtn;// 0,
    private String wh;//01DX"
    private String    bathc;
    private String   kw;

    private String t_id;
    private String t_name;
    private String t_status;
    private String pro_name;
    private String pro_count;
    private String t_time;
    private String so_id;
    private String so_itm;
    private String pro_no;
    private String n_date;

    private String ql_id;
    private String t_itm;
    private String ql_itm;


    private String dep;
    private String dep_name;
    private String ql_date;

    private String  n_id;


    public TaskInfo(JSONObject obj)
    {


        this.bathc = obj.optString("BATHC");
        this.kw = obj.optString("KW");

        this.n_id = obj.optString("N_ID");
        this.bat_no = obj.optString("BAT_NO");
        this.est_itm = obj.optString("EST_ITM");
        this.mo_no = obj.optString("MO_NO");
        this.prd_name = obj.optString("PRD_NAME");
        this.prd_no = obj.optString("PRD_NO");
        this.pre_itm = obj.optString("PRE_ITM");
        this.ql_dd = obj.optString("QL_DD");
        this.ql_no = obj.optString("QL_NO");
        this.wh = obj.optString("WH");
        this.qty = obj.optString("QTY");
        this.qty_import = obj.optInt("QTY_IMPORT");
        this.qty_rtn = obj.optString("QTY_RTN");




        this.dep = obj.optString("DEP");
        this.dep_name = obj.optString("DEP_NAME");
        this.ql_date = obj.optString("QL_DATE");
        this.ql_id = obj.optString("QL_ID");
        this.t_itm = obj.optString("T_ITM");
        this.ql_itm = obj.optString("QL_ITM");

        this.n_date = obj.optString("N_DATE");
        this.pro_no = obj.optString("PRO_NO");
        this.so_itm = obj.optString("SO_ITM");
        this.so_id = obj.optString("SO_ID");
        this.t_id = obj.optString("T_ID");
        this.t_name = obj.optString("T_NAME");
        this.t_status = obj.optString("T_STATUS");
        this.pro_name = obj.optString("PRO_NAME");
        this.pro_count = obj.optString("PRO_COUNT");
        this.t_time = obj.optString("T_TIME");

    }

    public String getT_id()
    {
        return t_id;
    }

    public void setT_id(String t_id)
    {
        this.t_id = t_id;
    }

    public String getT_name()
    {
        return t_name;
    }

    public void setT_name(String t_name)
    {
        this.t_name = t_name;
    }

    public String getT_status()
    {
        return t_status;
    }

    public void setT_status(String t_status)
    {
        this.t_status = t_status;
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

    public String getT_time()
    {
        return t_time;
    }

    public void setT_time(String t_time)
    {
        this.t_time = t_time;
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

    public String getPro_no()
    {
        return pro_no;
    }

    public void setPro_no(String pro_no)
    {
        this.pro_no = pro_no;
    }

    public String getN_date()
    {
        return n_date;
    }

    public void setN_date(String n_date)
    {
        this.n_date = n_date;
    }

    public String getQl_id()
    {
        return ql_id;
    }

    public void setQl_id(String ql_id)
    {
        this.ql_id = ql_id;
    }

    public String getT_itm()
    {
        return t_itm;
    }

    public void setT_itm(String t_itm)
    {
        this.t_itm = t_itm;
    }

    public String getQl_itm()
    {
        return ql_itm;
    }

    public void setQl_itm(String ql_itm)
    {
        this.ql_itm = ql_itm;
    }


    public String getDep()
    {
        return dep;
    }

    public void setDep(String dep)
    {
        this.dep = dep;
    }

    public String getDep_name()
    {
        return dep_name;
    }

    public void setDep_name(String dep_name)
    {
        this.dep_name = dep_name;
    }

    public String getQl_date()
    {
        return ql_date;
    }

    public void setQl_date(String ql_date)
    {
        this.ql_date = ql_date;
    }


    public String getBat_no()
    {
        return bat_no;
    }

    public void setBat_no(String bat_no)
    {
        this.bat_no = bat_no;
    }

    public String getEst_itm()
    {
        return est_itm;
    }

    public void setEst_itm(String est_itm)
    {
        this.est_itm = est_itm;
    }

    public String getMo_no()
    {
        return mo_no;
    }

    public void setMo_no(String mo_no)
    {
        this.mo_no = mo_no;
    }

    public String getPrd_name()
    {
        return prd_name;
    }

    public void setPrd_name(String prd_name)
    {
        this.prd_name = prd_name;
    }

    public String getPrd_no()
    {
        return prd_no;
    }

    public void setPrd_no(String prd_no)
    {
        this.prd_no = prd_no;
    }

    public String getPre_itm()
    {
        return pre_itm;
    }

    public void setPre_itm(String pre_itm)
    {
        this.pre_itm = pre_itm;
    }

    public String getQl_dd()
    {
        return ql_dd;
    }

    public void setQl_dd(String ql_dd)
    {
        this.ql_dd = ql_dd;
    }

    public String getQl_no()
    {
        return ql_no;
    }

    public void setQl_no(String ql_no)
    {
        this.ql_no = ql_no;
    }

    public String getQty()
    {
        return qty;
    }

    public void setQty(String qty)
    {
        this.qty = qty;
    }


    public int getQty_import()
    {
        return qty_import;
    }

    public void setQty_import(int qty_import)
    {
        this.qty_import = qty_import;
    }

    public String getQty_rtn()
    {
        return qty_rtn;
    }

    public void setQty_rtn(String qty_rtn)
    {
        this.qty_rtn = qty_rtn;
    }

    public String getWh()
    {
        return wh;
    }

    public void setWh(String wh)
    {
        this.wh = wh;
    }


    public String getN_id() {
        return n_id;
    }

    public void setN_id(String n_id) {
        this.n_id = n_id;
    }

    public String getBathc()
    {
        return bathc;
    }

    public void setBathc(String bathc)
    {
        this.bathc = bathc;
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
