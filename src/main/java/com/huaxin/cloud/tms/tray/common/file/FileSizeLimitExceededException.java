package com.huaxin.cloud.tms.tray.common.file;

/**
 * 
 * @Description: 文件名大小限制异常类
 * @author Administrator
 * @date: 2019年12月12日下午3:46:12
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
