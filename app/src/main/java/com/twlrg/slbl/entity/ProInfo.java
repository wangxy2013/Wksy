package com.twlrg.slbl.entity;

/**
 * 作者：王先云 on 2018/4/3 16:20
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ProInfo
{
    private String t_id;
    private String    dep;
    private String        dep_name;
    private String   cus_no;
    private String        cus_name;
    private String  bat_no;
    private String         kw;
    private String kwn;
    private String         sn;
    private String  so_id;
    private String         so_itm;
    private String  n_id;
    private String     n_itm;

    private String batch;

    public String getKw()
    {
        return kw;
    }

    public void setKw(String kw)
    {
        this.kw = kw;
    }

    public String getKwn()
    {
        return kwn;
    }

    public void setKwn(String kwn)
    {
        this.kwn = kwn;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getT_id()
    {
        return t_id;
    }

    public void setT_id(String t_id)
    {
        this.t_id = t_id;
    }

    public String getN_id()
    {
        return n_id;
    }

    public void setN_id(String n_id)
    {
        this.n_id = n_id;
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

    public String getN_itm()
    {
        return n_itm;
    }

    public void setN_itm(String n_itm)
    {
        this.n_itm = n_itm;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getCus_no() {
        return cus_no;
    }

    public void setCus_no(String cus_no) {
        this.cus_no = cus_no;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getBat_no() {
        return bat_no;
    }

    public void setBat_no(String bat_no) {
        this.bat_no = bat_no;
    }

    public String getBatch()
    {
        return batch;
    }

    public void setBatch(String batch)
    {
        this.batch = batch;
    }
}
