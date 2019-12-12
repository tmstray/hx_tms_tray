package com.huaxin.cloud.tms.tray.service;

import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderAndDetailDTO;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderDetailDTO;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrder;

import java.util.List;


/**
 * RFID绑定提货单信息Service接口
 * 
 */
public interface RfidBindOrderService 
{
    /**
     * 查询RFID绑定提货单信息
     * 
     * @param id RFID绑定提货单信息ID
     * @return RFID绑定提货单信息
     */
    public RfidBindOrder selectRfidBindOrderById(Integer id);
    
    
    /**
     * 根据订单号查询RFID绑定提货单信息
     * 
     * @param orderNo 订单号
     * @return RFID绑定提货单信息
     */
    public RfidBindOrder selectRfidBindOrderByOrderNo(String orderNo);

    /**
     * 查询RFID绑定提货单信息列表
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return RFID绑定提货单信息集合
     */
    public List<RfidBindOrder> selectRfidBindOrderList(RfidBindOrder rfidBindOrder);

    
    /**
     * 查询提货单信息列表
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return RFID绑定提货单信息
     */
    public List<RfidBindOrder> selectOrderList(RfidBindOrder rfidBindOrder);
    

    /**
     * 新增RFID绑定提货单信息
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    public int insertRfidBindOrder(RfidBindOrder tmsRfidBindOrder)throws Exception;

    /**
     * 修改RFID绑定提货单信息
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    public String updateRfidBindOrderDetail(List<RfidBindOrderDetailDTO> rfidBindOrderDetailDTOList)throws Exception;
    
    
    /**
     * 同时增加订单和rfid明细
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    public String addOrderAndDetail(RfidBindOrderAndDetailDTO rfidBindOrderAndDetailDTO)throws Exception;

    /**
     * 批量删除RFID绑定提货单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRfidBindOrderByIds(String ids)throws Exception;

    /**
     * 删除RFID绑定提货单信息信息
     * 
     * @param id RFID绑定提货单信息ID
     * @return 结果
     */
    public int deleteRfidBindOrderById(Integer id)throws Exception;
    

    /**
     *  更新进厂时间
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateEnteringTime(String orderNo)throws Exception;

	 /**
     *  更新出厂时间
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateLeaveTime(String orderNo)throws Exception;

	/**
     * 批量插入数据
     * @param rfidBindOrderList RFID绑定提货单信息
     * @return 结果
     */
	public String batchInsert(List<RfidBindOrder> rfidBindOrderList)throws Exception;

	/**
     *  根据提货单号更新装运状态 
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateShipmentStatus(String orderNo)throws Exception;

	/**
	 * 解绑
	 * @Description: 
	 * @author Administrator
	 * @date: 2019年12月10日上午9:48:33
	 * @param rfidBindOrderDetailDTO
	 * @return
	 * @throws Exception
	 */
	public int unBind(RfidBindOrderDetailDTO rfidBindOrderDetailDTO)throws Exception;
	
	/**
	 * @Description: 第二次绑定
	 * @author Administrator
	 * @date: 2019年12月10日上午9:47:47
	 * @param rfidBindOrderAndDetailDTO
	 * @return
	 * @throws Exception
	 */
	public int SecondBind(RfidBindOrderAndDetailDTO rfidBindOrderAndDetailDTO)throws Exception;


}
