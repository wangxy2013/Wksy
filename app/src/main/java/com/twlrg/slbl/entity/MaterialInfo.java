package com.twlrg.slbl.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2018/3/28 16:34
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class MaterialInfo implements Serializable
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


    public MaterialInfo(JSONObject obj)
    {
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
}
