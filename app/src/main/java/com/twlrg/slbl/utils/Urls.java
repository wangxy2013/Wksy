package com.twlrg.slbl.utils;

/**
 * URL管理类
 *
 * @date 2014年9月16日 上午9:48:03
 * @since[产品/模块版本]
 * @seejlj
 */
public class Urls
{
    //public static final String HTTP_IP = "http://180.100.223.13:8089";

    public static final String BASE_URL = ConfigManager.instance().getBaseUrl();


    //用戶登录
    public static String getLoginUrl()
    {
        return BASE_URL + "/trace/app/login";
    }

    //用戶登录
    public static String geVersionUrl()
    {
        return BASE_URL + "/trace/app/login";
    }

    //获取商品详情
    public static String getProductDetailUrl()
    {
        return BASE_URL + "/trace/app/scan";
    }

    //w物料入库操作
    public static String getProductCheckinUrl()
    {
        return BASE_URL + "/trace/app/checkin";
    }

    //生产任务单列表
    public static String getTasklistUrl()
    {
        return BASE_URL + "/trace/app/tasklist";
    }

    //出库申请单选择
    public static String getTaskSelectListUrl()
    {
        return BASE_URL + "/trace/app/taskSelectList";
    }

    //成品出库申请单选择
    public static String getNoticeSelectListUrl()
    {
        return BASE_URL + "/trace/app/noticeSelectList";
    }



    //任务出库材料列表
    public static String gettaskMetelistUrl()
    {
        return BASE_URL + "/trace/app/taskMetelist";
    }

    //获取库位
    public static String getKVUrl()
    {
        return BASE_URL + "/trace/app/getKV";
    }

    //材料出库操作
    public static String getCheckoutUrl()
    {
        return BASE_URL + "/trace/app/checkout";
    }


    //产品入库任务列表
    public static String getProductListUrl()
    {
        return BASE_URL + "/trace/app/noticeProdlist";
    }

    //任务及成品列表
    public static String getTaskProdlistUrl()
    {
        return BASE_URL + "/trace/app/taskProdlist";
    }

    //成品入库
    public static String getProdCheckinUrl()
    {
        return BASE_URL + "/trace/app/prodIn";
    }

    //成品出库通知单列表
    public static String getProdNoticeUrl()
    {
        return BASE_URL + "/trace/app/noticeProdlist";
    }


    //成品出库操作
    public static String getProdCheckoutUrl()
    {
        return BASE_URL + "/trace/app/prodOut";
    }


    public static String getNoticeListUrl()
    {
        return BASE_URL + "/trace/app/noticeList";
    }


    //成品出庫任務列表
    public static String getMesTaskListUrl()
    {
        return BASE_URL + "/trace/app/mesTaskList";
    }

    //物料入庫校驗
    public static String getScanInCheckUrl()
    {
        return BASE_URL + "/trace/app/scanInCheck";
    }

    //物料出庫校驗
    public static String getScanOutCheckUrl()
    {
        return BASE_URL + "/trace/app/scanOutCheck";
    }


    //成品出库校验
    public static String getProdOutChekUrl()
    {
        return BASE_URL + "/trace/app/prodOutChek";
    }


    //成品入库库校验
    public static String getProdInChekUrl()
    {
        return BASE_URL + "/trace/app/prodInChek";
    }



}
