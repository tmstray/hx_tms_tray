package com.huaxin.cloud.tms.tray.common.exception.user;

/**
 * 
 * @Description: 用户密码不正确或不符合规范异常类
 * @author Administrator
 * @date: 2019年12月24日下午3:12:07
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
