package com.huaxin.cloud.tms.tray.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderDetailDTO;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrderDetail;

/**
 * 提货单绑定RFID明细Mapper接口
 * 
 */
public interface RfidBindOrderDetailMapper 
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
     * 删除提货单绑定RFID明细
     * 
     * @param id 提货单绑定RFID明细ID
     * @return 结果
     */
    public int deleteRfidBindOrderDetailById(String rfid, String currentCode);

    /**
     * 批量删除提货单绑定RFID明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRfidBindOrderDetailByIds(String[] ids);

    /**
     * 批量新增提货单绑定RFID明细
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public void batchRfidBindOrderDetai(List<RfidBindOrderDetail> list);

	/**
     * 解除绑定
     */
	public int unBind(RfidBindOrderDetailDTO rfidBindOrderDetailDTO);

    public int updateRfidAndCurrentCode(@Param("rfid") String rfid,@Param("oldCode") String oldCode,@Param("currentCode") String currentCode);
}

