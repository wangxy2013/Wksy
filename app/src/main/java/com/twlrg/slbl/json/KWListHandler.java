package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.KWInfo;
import com.twlrg.slbl.entity.MaterialInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class KWListHandler extends JsonHandler
{
    private List<KWInfo> kwInfos = new ArrayList<>();

    public List<KWInfo> getKWInfoList()
    {
        return kwInfos;
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
                    KWInfo mKWInfo = new KWInfo(arr.optJSONObject(i));
                    kwInfos.add(mKWInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
