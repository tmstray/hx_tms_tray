package com.huaxin.cloud.tms.tray.common.manager;

import java.util.TimerTask;

import com.huaxin.cloud.tms.tray.common.utils.ip.AddressUtils;
import com.huaxin.cloud.tms.tray.common.utils.spring.SpringUtils;
import com.huaxin.cloud.tms.tray.entity.TrayLog;
import com.huaxin.cloud.tms.tray.service.TrayLogService;


/**
 * 异步工厂（产生任务用）
 * 
 */
public class AsyncFactory
{

    /**
     * 操作日志记录
     * 
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final TrayLog trayLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
            	// 远程查询操作地点
            	trayLog.setOperLocation(AddressUtils.getRealAddressByIP(trayLog.getIp()));
                SpringUtils.getBean(TrayLogService.class).insertTrayLog(trayLog);
            }
        };
    }
}

