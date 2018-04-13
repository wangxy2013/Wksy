package com.twlrg.slbl.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2018/3/28 15:12
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ProductInfo
{
    private String batch;//GDS2323
    private String c;//2620~2660
    private String count;//10000
    private String ctime;//2018-03-13
    private String mete_id;//TPPS0001-1
    private String r;//21~25
    private String supplier;//GF0007
    private String type_name;//電芯
    private String type_no;//DX020101
    private String v;//3635~3640"


    //    材料编号	METE_ID
    //    材料类型编号	TYPE_NO
    //    材料类型	TYPE_NAME
    //    供应商	SUPPLIER
    //    批次号	BATCH
    //    电压	V
    //    电阻	R
    //    容量	C
    //    材料数量	COUNT
    //    检测日期	CTIME
    private String sn;
    private String pro_name;
    private String kwn;
    private String pro_date;
    private String kw;

    private String t_id;
    private String pro_count;
    private String n_date;


    public ProductInfo(JSONObject obj)
    {
        this.sn = obj.optString("SN");
        this.pro_name = obj.optString("PRO_NAME");
        this.kwn = obj.optString("KWN");
        this.pro_date = obj.optString("PRO_DATE");
        this.batch = obj.optString("BATCH");
        this.ctime = obj.optString("CTIME");
        this.count = obj.optString("COUNT");
        this.c = obj.optString("C");
        this.r = obj.optString("R");
        this.v = obj.optString("V");
        this.supplier = obj.optString("SUPPLIER");
        this.type_name = obj.optString("TYPE_NAME");
        this.mete_id = obj.optString("METE_ID");
        this.type_no = obj.optString("TYPE_NO");
        this.pro_count = obj.optString("PRO_COUNT");
        this.n_date = obj.optString("N_DATE");
        this.kw = obj.optString("KW");

    }


    public String getBatch()
    {
        return batch;
    }

    public void setBatch(String batch)
    {
        this.batch = batch;
    }

    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getCtime()
    {
        return ctime;
    }

    public void setCtime(String ctime)
    {
        this.ctime = ctime;
    }

    public String getMete_id()
    {
        return mete_id;
    }

    public void setMete_id(String mete_id)
    {
        this.mete_id = mete_id;
    }

    public String getR()
    {
        return r;
    }

    public void setR(String r)
    {
        this.r = r;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getType_name()
    {
        return type_name;
    }

    public void setType_name(String type_name)
    {
        this.type_name = type_name;
    }

    public String getType_no()
    {
        return type_no;
    }

    public void setType_no(String type_no)
    {
        this.type_no = type_no;
    }

    public String getV()
    {
        return v;
    }

    public void setV(String v)
    {
        this.v = v;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getPro_name()
    {
        return pro_name;
    }

    public void setPro_name(String pro_name)
    {
        this.pro_name = pro_name;
    }

    public String getKwn()
    {
        return kwn;
    }

    public void setKwn(String kwn)
    {
        this.kwn = kwn;
    }

    public String getPro_date()
    {
        return pro_date;
    }

    public void setPro_date(String pro_date)
    {
        this.pro_date = pro_date;
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

    public String getPro_count()
    {
        return pro_count;
    }

    public void setPro_count(String pro_count)
    {
        this.pro_count = pro_count;
    }

    public String getN_date()
    {
        return n_date;
    }

    public void setN_date(String n_date)
    {
        this.n_date = n_date;
    }
}
