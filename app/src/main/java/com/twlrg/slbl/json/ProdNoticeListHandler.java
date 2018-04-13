package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.ProdNoticeInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ProdNoticeListHandler extends JsonHandler
{
    private List<ProdNoticeInfo> prodNoticeInfos = new ArrayList<>();

    public List<ProdNoticeInfo> getProdNoticeInfos()
    {
        return prodNoticeInfos;
    }

    @Override
    protected void parseJson(JSONObject jsonObj) throws Exception
    {
        try
        {
            JSONObject obj = jsonObj.optJSONObject("bodys");

            if (null != obj)
            {
                JSONArray arr = obj.optJSONArray("list");


                for (int i = 0; i < arr.length(); i++)
                {
                    ProdNoticeInfo mProdNoticeInfo = new ProdNoticeInfo(arr.optJSONObject(i));
                    prodNoticeInfos.add(mProdNoticeInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
