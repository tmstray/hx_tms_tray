package com.huaxin.cloud.tms.tray.dao;


import java.util.List;

import com.huaxin.cloud.tms.tray.common.annotation.DataSource;
import com.huaxin.cloud.tms.tray.common.enums.DataSourceType;
import com.huaxin.cloud.tms.tray.entity.BillInfo;
import com.huaxin.cloud.tms.tray.entity.CardBill;

/**
 * 交货单Mapper接口
 * 
 */
public interface BillInfoMapper 
{
	
	
	/**
     * 
     * @Description: 根据磁卡号查询交货单
     * @author Administrator
     * @date: 2020年1月7日下午4:27:39
     * @param card
     * @return
     */
	@DataSource(value = DataSourceType.SLAVE)
    public CardBill selectCardBillInfoByCard(String card);
    
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
    @DataSource(value = DataSourceType.SLAVE)
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
     * 删除交货单
     * 
     * @param id 交货单ID
     * @return 结果
     */
    public int deleteBillInfoById(Integer id);

    /**
     * 批量删除交货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBillInfoByIds(String[] ids);
}

