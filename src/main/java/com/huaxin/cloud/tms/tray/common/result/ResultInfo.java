package com.huaxin.cloud.tms.tray.common.result;

import java.util.HashMap;

import com.huaxin.cloud.tms.tray.common.constant.HttpStatus;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;

/**
 * 操作消息提醒
 * 
 */
public class ResultInfo extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ResultInfo()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     */
    public ResultInfo(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ResultInfo(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static ResultInfo success()
    {
        return ResultInfo.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static ResultInfo success(Object data)
    {
        return ResultInfo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResultInfo success(String msg)
    {
        return ResultInfo.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultInfo success(String msg, Object data)
    {
        return new ResultInfo(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static ResultInfo error()
    {
        return ResultInfo.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultInfo error(String msg)
    {
        return ResultInfo.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultInfo error(String msg, Object data)
    {
        return new ResultInfo(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultInfo error(int code, String msg)
    {
        return new ResultInfo(code, msg, null);
    }
}
