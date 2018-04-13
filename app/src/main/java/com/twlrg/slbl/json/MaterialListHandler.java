package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.TaskInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MaterialListHandler extends JsonHandler
{
    private List<MaterialInfo> materialInfo = new ArrayList<>();

    public List<MaterialInfo> getMaterialInfoList()
    {
        return materialInfo;
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
                    MaterialInfo mMaterialInfo = new MaterialInfo(arr.optJSONObject(i));
                    materialInfo.add(mMaterialInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
