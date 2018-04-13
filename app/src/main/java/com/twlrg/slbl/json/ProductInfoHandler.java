package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.ProductInfo;
import com.twlrg.slbl.utils.ConfigManager;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：用户登录
 */
public class ProductInfoHandler extends JsonHandler
{
    private ProductInfo productInfo;

    public ProductInfo getProductInfo()
    {
        return productInfo;
    }

    @Override
    protected void parseJson(JSONObject jsonObj) throws Exception
    {
        try
        {
            JSONObject obj = jsonObj.optJSONObject("bodys");

            if (null != obj)
            {
                productInfo = new ProductInfo(obj);
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
