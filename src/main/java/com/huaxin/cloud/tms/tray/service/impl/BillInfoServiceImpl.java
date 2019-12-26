package com.huaxin.cloud.tms.tray.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaxin.cloud.tms.tray.common.utils.DateUtils;
import com.huaxin.cloud.tms.tray.common.utils.text.Convert;
import com.huaxin.cloud.tms.tray.dao.BillInfoMapper;
import com.huaxin.cloud.tms.tray.entity.BillInfo;
import com.huaxin.cloud.tms.tray.service.BillInfoService;

/**
 * 交货单Service业务层处理
 * 
 */
@Service
public class BillInfoServiceImpl implements BillInfoService 
{
    @Autowired
    private BillInfoMapper billInfoMapper;

    /**
     * 查询交货单
     * 
     * @param id 交货单ID
     * @return 交货单
     */
    @Override
    public BillInfo selectBillInfoById(Integer id)
    {
        return billInfoMapper.selectBillInfoById(id);
    }
    
    /**
     * 查询交货单
     * 
     * @param lCard 磁卡号
     * @return 交货单
     */
    @Override
    public String selectBillInfoByLCard(String lCard) {
    	
    	return billInfoMapper.selectBillInfoByLCard(lCard);
    }

    /**
     * 查询交货单列表
     * 
     * @param BillInfo 交货单
     * @return 交货单
     */
    @Override
    public List<BillInfo> selectBillInfoList(BillInfo BillInfo)
    {
        return billInfoMapper.selectBillInfoList(BillInfo);
    }

    /**
     * 新增交货单
     * 
     * @param BillInfo 交货单
     * @return 结果
     */
    @Override
    public int insertBillInfo(BillInfo BillInfo)
    {
        BillInfo.setCreateTime(DateUtils.getNowDate());
        return billInfoMapper.insertBillInfo(BillInfo);
    }

    /**
     * 修改交货单
     * 
     * @param BillInfo 交货单
     * @return 结果
     */
    @Override
    public int updateBillInfo(BillInfo BillInfo)
    {
        BillInfo.setUpdateTime(DateUtils.getNowDate());
        return billInfoMapper.updateBillInfo(BillInfo);
    }

    /**
     * 删除交货单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBillInfoByIds(String ids)
    {
        return billInfoMapper.deleteBillInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交货单信息
     * 
     * @param id 交货单ID
     * @return 结果
     */
    @Override
    public int deleteBillInfoById(Integer id)
    {
        return billInfoMapper.deleteBillInfoById(id);
    }
}
