package com.huaxin.cloud.tms.tray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.cloud.tms.tray.dao.TrayLogMapper;
import com.huaxin.cloud.tms.tray.entity.TrayLog;
import com.huaxin.cloud.tms.tray.service.TrayLogService;

/**
 * 托盘管理操作日志Service业务层处理
 * 
 */
@Service
public class TrayLogServiceImpl implements TrayLogService 
{
    @Autowired
    private TrayLogMapper trayLogMapper;

    /**
     * 查询托盘管理操作日志
     * 
     * @param id 托盘管理操作日志ID
     * @return 托盘管理操作日志
     */
    @Override
    public TrayLog selectTrayLogById(Integer id)
    {
        return null;
    }

    /**
     * 查询托盘管理操作日志列表
     * 
     * @param trayLog 托盘管理操作日志
     * @return 托盘管理操作日志
     */
    @Override
    public List<TrayLog> selectTrayLogList(TrayLog trayLog)
    {
        return null;
    }

    /**
     * 新增托盘管理操作日志
     * 
     * @param trayLog 托盘管理操作日志
     * @return 结果
     */
    @Override
    public int insertTrayLog(TrayLog trayLog)
    {
        return trayLogMapper.insertTrayLog(trayLog);
    }

    /**
     * 修改托盘管理操作日志
     * 
     * @param trayLog 托盘管理操作日志
     * @return 结果
     */
    @Override
    public int updateTrayLog(TrayLog trayLog)
    {
        return 0;
    }

    /**
     * 删除托盘管理操作日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTrayLogByIds(String ids)
    {
            return 0;
    }

    /**
     * 删除托盘管理操作日志信息
     * 
     * @param id 托盘管理操作日志ID
     * @return 结果
     */
    @Override
    public int deleteTrayLogById(Long id)
    {
        return 0;
    }
}
