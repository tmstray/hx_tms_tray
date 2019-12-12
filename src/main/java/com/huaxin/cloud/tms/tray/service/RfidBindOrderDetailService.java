package com.huaxin.cloud.tms.tray.service;


import java.util.List;

import com.huaxin.cloud.tms.tray.entity.RfidBindOrderDetail;

/**
 * 提货单绑定RFID明细Service接口
 * 
 */
public interface RfidBindOrderDetailService 
{
    /**
     * 查询提货单绑定RFID明细
     * 
     * @param id 提货单绑定RFID明细ID
     * @return 提货单绑定RFID明细
     */
    public RfidBindOrderDetail selectRfidBindOrderDetailById(Integer id);

    /**
     * 查询提货单绑定RFID明细列表
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 提货单绑定RFID明细集合
     */
    public List<RfidBindOrderDetail> selectRfidBindOrderDetailList(RfidBindOrderDetail rfidBindOrderDetail);

    /**
     * 新增提货单绑定RFID明细
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 结果
     */
    public int insertRfidBindOrderDetail(RfidBindOrderDetail rfidBindOrderDetail);

    /**
     * 修改提货单绑定RFID明细
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 结果
     */
    public int updateRfidBindOrderDetail(RfidBindOrderDetail rfidBindOrderDetail);

    /**
     * 批量删除提货单绑定RFID明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRfidBindOrderDetailByIds(String ids);

    /**
     * 删除提货单绑定RFID明细信息
     * 
     * @param id 提货单绑定RFID明细ID
     * @return 结果
     */
    public int deleteRfidBindOrderDetailById(Integer id);
}

