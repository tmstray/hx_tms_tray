package com.huaxin.cloud.tms.tray.dao;

import com.huaxin.cloud.tms.tray.entity.TrayLog;

public interface TrayLogMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增托盘管理操作日志
     * 
     * @param TrayLog 托盘管理操作日志
     * @return 结果
     */
    int insertTrayLog(TrayLog trayLog);

    int insertSelective(TrayLog record);

    int updateByPrimaryKeySelective(TrayLog record);

    int updateByPrimaryKey(TrayLog record);
}