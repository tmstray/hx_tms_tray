package com.huaxin.cloud.tms.tray.service;

import com.huaxin.cloud.tms.tray.dto.Request.ReqTrayInfoDTO;
import com.huaxin.cloud.tms.tray.dto.Response.ResTrayInfoDTO;
import com.huaxin.cloud.tms.tray.entity.TrayInfo;
import java.util.List;

/**
 * 托盘基础信息管理Service接口
 * 
 */
public interface TrayInfoService 
{
    /**
     * 查询托盘基础信息管理列表
     * 
     * @param trayInfo 托盘基础信息管理
     * @return 托盘基础信息管理集合
     */
    public List<ResTrayInfoDTO> selectTrayInfoList(TrayInfo trayInfo);


    /**
     * 根据托盘状态 返回对应的托盘数量：
     * @param trayInfo
     * @return
     */
    public List<ResTrayInfoDTO> selectTrayInfoByStatues(TrayInfo trayInfo);

    /**
     * 新增托盘基础信息管理
     * 
     * @param trayInfo 托盘基础信息管理
     * @return 结果
     */
    public int insertTrayInfo(TrayInfo trayInfo) throws  Exception;


    /**
     * 新增托盘基础信息管理
     *
     * @param trayInfo 托盘基础信息管理
     * @return 结果
     */
    public int insertBatch(List<TrayInfo> trayInfo) throws  Exception;
    /**
     * 修改托盘基础信息管理
     * 
     * @param trayInfo 托盘基础信息管理
     * @return 结果
     */
    public int modifyTrayInfo(ReqTrayInfoDTO trayInfo) throws  Exception;

    /**
     * 冻结托盘
     * @param rfid
     * @param factoryCode
     * @return
     */
    public int freeZeTrayInfo(ReqTrayInfoDTO trayInfo) throws Exception;

    /**
     * 批量删除托盘基础信息管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrayInfoByIds(String ids) throws  Exception;

    /**
     * 删除托盘基础信息管理信息
     * 
     * @param id 托盘基础信息管理ID
     * @return 结果
     */
    public int deleteTrayInfoById(Integer id,String updateBy) throws  Exception;

    /**
     * 根据提货单更新托盘信息：
     * @param factoryCode
     * @param orderNo
     * @param rfidStatus
     * @param updateBy
     * @return
     */
    public int updateStatusByWayBillNo(String factoryCode,String orderNo,Integer rfidStatus,String updateBy) throws  Exception;


    /**
     * 根据托盘id 更新托盘健康状态
     * @param trayInfo
     * @return
     * @throws Exception
     */
    public int updateTrayInfoByRfid(ReqTrayInfoDTO trayInfo) throws Exception;

    
    /**
     * 导入托盘数据
     * 
     * @param trayInfoList 托盘数据列表
     * @return 结果
     */
    public String importTrayInfo(List<TrayInfo> trayInfoList);

}
