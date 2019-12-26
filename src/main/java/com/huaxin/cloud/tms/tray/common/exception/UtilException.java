package com.huaxin.cloud.tms.tray.common.exception;
/**
 * 
 * @Description: 工具类异常
 * @author Administrator
 * @date: 2019年12月24日下午2:54:10
 */
public class UtilException extends RuntimeException
{
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e)
    {
        super(e.getMessage(), e);
    }

    public UtilException(String message)
    {
        super(message);
    }

    public UtilException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
