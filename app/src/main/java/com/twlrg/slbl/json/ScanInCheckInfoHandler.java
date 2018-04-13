package com.twlrg.slbl.json;



import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.entity.ScanInCheckInfo;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 */
public class ScanInCheckInfoHandler extends JsonHandler
{

    private ScanInCheckInfo scanInCheckInfo;

    public ScanInCheckInfo getScanInCheckInfo()
    {
        return scanInCheckInfo;
    }

    @Override
    protected void parseJson(JSONObject jsonObj) throws Exception
    {
        try
        {
            JSONObject obj = jsonObj.optJSONObject("bodys");

            if (null != obj)
            {
                scanInCheckInfo = new ScanInCheckInfo(obj);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
