package com.huaxin.cloud.tms.tray.dao;

import java.util.List;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrder;
public interface RfidBindOrderMapper {

	/**
     * 查询RFID绑定提货单信息
     *
     * @param id RFID绑定提货单信息ID
     * @return RFID绑定提货单信息
     */
    public RfidBindOrder selectRfidBindOrderById(Integer id);

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

    int deleteByPrimaryKey(Integer id);

    /**
     * 新增RFID绑定提货单信息
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    public int insertRfidBindOrder(RfidBindOrder tmsRfidBindOrder);

    /**
     * 修改RFID绑定提货单信息
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    public int updateRfidBindOrder(RfidBindOrder tmsRfidBindOrder);

    /**
     * 删除RFID绑定提货单信息信息
     *
     * @param id RFID绑定提货单信息ID
     * @return 结果
     */
    public int deleteRfidBindOrderById(Integer id);

    int insertSelective(RfidBindOrder record);



    int updateByPrimaryKeySelective(RfidBindOrder record);

    int updateByPrimaryKey(RfidBindOrder record);

    /**
     * 根据订单号查询RFID绑定提货单信息
     *
     * @param orderNo 订单号
     * @return RFID绑定提货单信息
     */
	public RfidBindOrder selectRfidBindOrderByOrderNo(String orderNo);

	/**
     *  根据提货单号更新装运状态
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */

	/**
     *  根据提货单号更新装运状态
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateShipmentStatus(String orderNo,String updateBy);

	/**
     *  更新进厂时间
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateEnteringTime(String orderNo,String updateBy);

	/**
     *  更新出厂时间
     *
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	public int updateLeaveTime(String orderNo,String updateBy);


	/**
	 *
	 * @Description: 第一种情况:修改订单明细表的 currentCode
	 * @author Administrator
	 * @date: 2019年12月10日上午10:45:55
	 * @param newCurrentCode
	 * @param newOrderNo
	 * @return
	 */
	public int updateCurrentCode(String newCurrentCode,String newOrderNo);

	/**
	 *
	 * @Description: 第二种情况:修改订单表的订单号
	 * @author Administrator
	 * @date: 2019年12月10日上午10:45:48
	 * @param newOrderNo
	 * @param oldOrderNo
	 * @return
	 */
	public int updateOrderNo(String newOrderNo,String oldOrderNo,String updateBy);

	/**
	 *
	 * @Description: 第三种情况:同时修改订单表的订单号和明细表的喷码编号
	 * @author Administrator
	 * @date: 2019年12月10日上午10:44:49
	 * @param newOrderNo
	 * @param oldOrderNo
	 * @param newCurrentCode
	 * @return
	 */
	public int updateOrderNoAndCurrentCode(String newOrderNo,String oldOrderNo,String newCurrentCode);
}
