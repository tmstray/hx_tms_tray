package com.huaxin.cloud.tms.tray.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.cloud.tms.tray.common.utils.text.Convert;
import com.huaxin.cloud.tms.tray.dao.RfidBindOrderDetailMapper;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrderDetail;
import com.huaxin.cloud.tms.tray.service.RfidBindOrderDetailService;

/**
 * 提货单绑定RFID明细Service业务层处理
 */
@Service
public class RfidBindOrderDetailServiceImpl implements RfidBindOrderDetailService 
{
    @Autowired
    private RfidBindOrderDetailMapper rfidBindOrderDetailMapper;

    /**
     * 查询提货单绑定RFID明细
     * 
     * @param id 提货单绑定RFID明细ID
     * @return 提货单绑定RFID明细
     */
    @Override
    public RfidBindOrderDetail selectRfidBindOrderDetailById(Integer id)
    {
        return rfidBindOrderDetailMapper.selectRfidBindOrderDetailById(id);
    }

    /**
     * 查询提货单绑定RFID明细列表
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 提货单绑定RFID明细
     */
    @Override
    public List<RfidBindOrderDetail> selectRfidBindOrderDetailList(RfidBindOrderDetail rfidBindOrderDetail)
    {
        return rfidBindOrderDetailMapper.selectRfidBindOrderDetailList(rfidBindOrderDetail);
    }

    /**
     * 新增提货单绑定RFID明细
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 结果
     */
    @Override
    public int insertRfidBindOrderDetail(RfidBindOrderDetail rfidBindOrderDetail)
    {
        return rfidBindOrderDetailMapper.insertRfidBindOrderDetail(rfidBindOrderDetail);
    }

    /**
     * 修改提货单绑定RFID明细
     * 
     * @param rfidBindOrderDetail 提货单绑定RFID明细
     * @return 结果
     */
    @Override
    public int updateRfidBindOrderDetail(RfidBindOrderDetail rfidBindOrderDetail)
    {
        return rfidBindOrderDetailMapper.updateRfidBindOrderDetail(rfidBindOrderDetail);
    }

    /**
     * 删除提货单绑定RFID明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRfidBindOrderDetailByIds(String ids)
    {
        return rfidBindOrderDetailMapper.deleteRfidBindOrderDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提货单绑定RFID明细信息
     * 
     * @param id 提货单绑定RFID明细ID
     * @return 结果
     */
    @Override
    public int deleteRfidBindOrderDetailById(Integer id)
    {
//        return rfidBindOrderDetailMapper.deleteRfidBindOrderDetailById(id);
    	return 0;
    }
}

