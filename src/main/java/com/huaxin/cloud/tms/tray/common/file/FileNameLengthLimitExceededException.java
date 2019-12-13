package com.huaxin.cloud.tms.tray.common.file;

/**
 * 
 * @Description: 文件名称超长限制异常类
 * @author Administrator
 * @date: 2019年12月12日下午3:50:19
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
