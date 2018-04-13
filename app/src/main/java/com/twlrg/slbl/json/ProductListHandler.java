package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.MaterialInfo;
import com.twlrg.slbl.entity.ProductInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ProductListHandler extends JsonHandler
{
    private List<ProductInfo> productInfoList = new ArrayList<>();

    public List<ProductInfo> getProductInfoList()
    {
        return productInfoList;
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
                    ProductInfo mProductInfo = new ProductInfo(arr.optJSONObject(i));
                    productInfoList.add(mProductInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
