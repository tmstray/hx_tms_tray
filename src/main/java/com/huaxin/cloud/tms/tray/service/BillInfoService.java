package com.huaxin.cloud.tms.tray.service;


import java.util.List;

import com.huaxin.cloud.tms.tray.entity.BillInfo;

/**
 * 交货单Service接口
 * 
 */
public interface BillInfoService 
{
    /**
     * 查询交货单
     * 
     * @param id 交货单ID
     * @return 交货单
     */
    public BillInfo selectBillInfoById(Integer id);
    
    /**
     * 查询交货单
     * 
     * @param lCard 磁卡号
     * @return 交货单
     */
    public String selectBillInfoByLCard(String lCard);
    

    /**
     * 查询交货单列表
     * 
     * @param BillInfo 交货单
     * @return 交货单集合
     */
    public List<BillInfo> selectBillInfoList(BillInfo BillInfo);

    /**
     * 新增交货单
     * 
     * @param BillInfo 交货单
     * @return 结果
     */
    public int insertBillInfo(BillInfo BillInfo);

    /**
     * 修改交货单
     * 
     * @param BillInfo 交货单
     * @return 结果
     */
    public int updateBillInfo(BillInfo BillInfo);

    /**
     * 批量删除交货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBillInfoByIds(String ids);

    /**
     * 删除交货单信息
     * 
     * @param id 交货单ID
     * @return 结果
     */
    public int deleteBillInfoById(Integer id);
}

