package com.huaxin.cloud.tms.tray.common.utils;

/**
 * 
 * @Description: 处理并记录日志文件
 * @author Administrator
 * @date: 2019年12月24日下午3:16:26
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
