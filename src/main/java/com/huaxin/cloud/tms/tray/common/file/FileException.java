package com.huaxin.cloud.tms.tray.common.file;

/**
 * 
 * @Description: 文件信息异常类
 * @author Administrator
 * @date: 2019年12月12日下午3:46:36
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
