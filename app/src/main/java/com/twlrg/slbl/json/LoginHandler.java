package com.twlrg.slbl.json;


import com.twlrg.slbl.utils.ConfigManager;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：用户登录
 */
public class LoginHandler extends JsonHandler
{


    @Override
    protected void parseJson(JSONObject jsonObj) throws Exception
    {
        try
        {
            JSONObject obj = jsonObj.optJSONObject("bodys");
            ConfigManager.instance().setUserId(obj.optString("USER_ID"));
            ConfigManager.instance().setToken(obj.optString("TOKEN"));
            ConfigManager.instance().setUserImg(obj.optString("USER_IMG"));
            ConfigManager.instance().setRoleName(obj.optString("ROLE_NAME"));
            ConfigManager.instance().setUserScore(obj.optString("USER_SCORE"));
            ConfigManager.instance().setBusId(obj.optString("BUS_ID"));
            ConfigManager.instance().setUserName(obj.optString("NAME"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
