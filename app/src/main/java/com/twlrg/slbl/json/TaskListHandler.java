package com.twlrg.slbl.json;


import com.twlrg.slbl.entity.TaskInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TaskListHandler extends JsonHandler
{
    private List<TaskInfo> taskInfoList = new ArrayList<>();

    public List<TaskInfo> getTaskInfoList()
    {
        return taskInfoList;
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
                    TaskInfo mTaskInfo = new TaskInfo(arr.optJSONObject(i));
                    taskInfoList.add(mTaskInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
