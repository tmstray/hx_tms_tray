package com.huaxin.cloud.tms.tray.service;

import java.util.List;

import com.huaxin.cloud.tms.tray.entity.TrayLog;

/**
 * 托盘管理操作日志Service接口
 * 
 */
public interface TrayLogService 
{
    /**
     * 查询托盘管理操作日志
     * 
     * @param id 托盘管理操作日志ID
     * @return 托盘管理操作日志
     */
    public TrayLog selectTrayLogById(Integer id);

    /**
     * 查询托盘管理操作日志列表
     * 
     * @param TrayLog 托盘管理操作日志
     * @return 托盘管理操作日志集合
     */
    public List<TrayLog> selectTrayLogList(TrayLog TrayLog);

    /**
     * 新增托盘管理操作日志
     * 
     * @param TrayLog 托盘管理操作日志
     * @return 结果
     */
    public int insertTrayLog(TrayLog TrayLog);

    /**
     * 修改托盘管理操作日志
     * 
     * @param TrayLog 托盘管理操作日志
     * @return 结果
     */
    public int updateTrayLog(TrayLog TrayLog);

    /**
     * 批量删除托盘管理操作日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrayLogByIds(String ids);

    /**
     * 删除托盘管理操作日志信息
     * 
     * @param id 托盘管理操作日志ID
     * @return 结果
     */
    public int deleteTrayLogById(Long id);
}
