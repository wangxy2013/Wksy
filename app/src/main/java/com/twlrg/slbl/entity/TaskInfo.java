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

    public TaskInfo(JSONObject obj)
    {

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
}
