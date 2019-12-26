package com.huaxin.cloud.tms.tray.common.enums;

/**
 * 
 * @Description: 用户状态
 * @author Administrator
 * @date: 2019年12月24日下午3:38:06
 */
public enum UserStatus
{
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
