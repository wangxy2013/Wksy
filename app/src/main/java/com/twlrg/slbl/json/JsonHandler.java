package com.twlrg.slbl.json;


import android.content.Context;


import com.twlrg.slbl.utils.ConstantUtil;

import org.json.JSONObject;


public abstract class JsonHandler
{

    private String resultCode = null;
    private String resultMsg  = null;

    protected abstract void parseJson(JSONObject obj) throws Exception;

    public void parseJson(Context mContext, String jsonString)
    {
        try
        {
            if (null == jsonString)
            {
                setResultCode(ConstantUtil.RESULT_FAIL);
            }
            else
            {
                JSONObject jsonOject = new JSONObject(jsonString);
                JSONObject   obj = jsonOject.optJSONObject("data");


                if ("true".equals(obj.optString("success")))
                {
                    setResultCode(ConstantUtil.RESULT_SUCCESS);
                }
                else
                {
                    setResultCode(ConstantUtil.RESULT_FAIL);

                }
                setResultMsg(obj.optString("errorMsg"));

                if (null != jsonOject) parseJson(obj);
            }
        } catch (Exception e)
        {
            // e.printStackTrace();
            setResultCode(ConstantUtil.RESULT_FAIL);
            setResultMsg("网络请求失败...");
        }

    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultMsg()
    {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }

}
