package com.huaxin.cloud.tms.tray.common.exception.user;

import com.huaxin.cloud.tms.tray.common.exception.BaseException;

/**
 * 
 * @Description: 用户信息异常类
 * @author Administrator
 * @date: 2019年12月24日下午3:06:37
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
