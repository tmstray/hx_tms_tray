package com.huaxin.cloud.tms.tray.service.impl;

import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.dao.RfidBindOrderDetailMapper;
import com.huaxin.cloud.tms.tray.dao.RfidBindOrderMapper;
import com.huaxin.cloud.tms.tray.dao.RfidBindSpurtcodeMapper;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderAndDetailDTO;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderDetailDTO;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrder;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrderDetail;
import com.huaxin.cloud.tms.tray.service.BillInfoService;
import com.huaxin.cloud.tms.tray.service.RfidBindOrderService;
import com.huaxin.cloud.tms.tray.service.TrayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * RFID绑定提货单信息Service业务层处理
 * 
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RfidBindOrderServiceImpl implements RfidBindOrderService 
{
	private static final Logger log = LoggerFactory.getLogger(RfidBindOrderServiceImpl.class);
	
    @Autowired
    private RfidBindOrderMapper rfidBindOrderMapper;
    
    @Autowired
    private RfidBindOrderDetailMapper rfidBindOrderDetailMapper;
    
    @Autowired
    private TrayInfoService trayInfoService;
    
    @Autowired
    private RfidBindSpurtcodeMapper rfidBindSpurtcodeMapper;
    
    @Autowired
    BillInfoService billInfoService;
    
    /**
     * 根据id查询RFID绑定提货单信息
     * 
     * @param id RFID绑定提货单信息ID
     * @return RFID绑定提货单信息
     */
    @Override
    public RfidBindOrder selectRfidBindOrderById(Integer id)
    {
        return rfidBindOrderMapper.selectRfidBindOrderById(id);
    }
    
    /**
     * 根据订单号查询RFID绑定提货单信息
     * 
     * @param orderNo 订单号
     * @return RFID绑定提货单信息
     */
    @Override
    public RfidBindOrder selectRfidBindOrderByOrderNo(String orderNo)
    {
        return rfidBindOrderMapper.selectRfidBindOrderByOrderNo(orderNo);
    }

    /**
     * 查询RFID绑定提货单信息列表
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return RFID绑定提货单信息
     */
    @Override
    public List<RfidBindOrder> selectRfidBindOrderList(RfidBindOrder rfidBindOrder)
    {
        return rfidBindOrderMapper.selectRfidBindOrderList(rfidBindOrder);
    }
    
    /**
     * 查询提货单信息列表
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return RFID绑定提货单信息
     */
    @Override
    public List<RfidBindOrder> selectOrderList(RfidBindOrder rfidBindOrder)
    {
        return rfidBindOrderMapper.selectOrderList(rfidBindOrder);
    }

    /**
     * 新增RFID绑定提货单信息
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    @Override
    public int insertRfidBindOrder(RfidBindOrder rfidBindOrder)throws Exception{
    	String orderNo = rfidBindOrder.getOrderNo();
    	RfidBindOrder rd = selectRfidBindOrderByOrderNo(orderNo);
    	if(StringUtils.isNotNull(rd)) {
    		throw new BusinessException("该订单已存在，请检查数据.");
    	}
        return rfidBindOrderMapper.insertRfidBindOrder(rfidBindOrder);
    }

    
    
    /**
	 * @Description: 第二次绑定
	 * @author Administrator
	 * @date: 2019年12月10日上午9:47:47
	 * @param rfidBindOrderAndDetailDTO
	 * @return
	 * @throws Exception
	 */
    @Override
    @Transactional
    public int SecondBind(RfidBindOrderAndDetailDTO rfidBindOrderAndDetailDTO)throws Exception{
    	int result=0;
    	String oldOrderNo = rfidBindOrderAndDetailDTO.getOldOrderNo();
    	String oldCurrentCode = rfidBindOrderAndDetailDTO.getOldCurrentCode();
    	String orderNo = rfidBindOrderAndDetailDTO.getOrderNo();
    	String currentCode = rfidBindOrderAndDetailDTO.getCurrentCode();
    	String rfid = rfidBindOrderAndDetailDTO.getRfidBindOrderDetails().get(0).getRfid();
    	
    	if(StringUtils.isNotEmpty(oldOrderNo)) {
    		//第一种情况 	oldCurrentCode != newCurrentCode && oldOrderNo ==  newOrderNo
        	if(!oldCurrentCode.equals(currentCode) && oldOrderNo.equals(orderNo)) {
				result=rfidBindOrderMapper.updateCurrentCode(currentCode,oldOrderNo);
        	}else if(oldCurrentCode.equals(currentCode) && !oldOrderNo.equals(orderNo) && StringUtils.isNotEmpty(orderNo)) {
        		//第二种情况 	oldCurrentCode = newCurrentCode && oldOrderNo !=  newOrderNo
				result=rfidBindOrderMapper.updateOrderNo(orderNo, oldOrderNo);
        	}else if (!oldCurrentCode.equals(currentCode) && !oldOrderNo.equals(orderNo)) {
        		//第三种情况 	oldCurrentCode != newCurrentCode && oldOrderNo !=  newOrderNo
				result=rfidBindOrderMapper.updateOrderNoAndCurrentCode(orderNo, oldOrderNo, currentCode);
        	}else if(StringUtils.isEmpty(orderNo)) {
        		//第四种情况: 订单号置空，把明细表的数据删除
				result=rfidBindOrderDetailMapper.deleteRfidBindOrderDetailById(rfid, currentCode);
        	}
    	}else {
			//第五种情况  新增操作
			//先插入订单表
			if (StringUtils.isNotEmpty(orderNo)) {
				RfidBindOrder rfidBindOrderC = new RfidBindOrder();
				BeanUtils.copyProperties(rfidBindOrderAndDetailDTO, rfidBindOrderC);
				result=rfidBindOrderMapper.insertRfidBindOrder(rfidBindOrderC);

				//插入订单明细表
				for (RfidBindOrderDetail rfidBindOrderDetail : rfidBindOrderAndDetailDTO.getRfidBindOrderDetails()) {
					rfidBindOrderDetail.setCurrentCode(currentCode);
					rfidBindOrderDetail.setRfid(rfidBindOrderDetail.getRfid());
					rfidBindOrderDetail.setPid(rfidBindOrderC.getId());
					result=rfidBindOrderDetailMapper.insertRfidBindOrderDetail(rfidBindOrderDetail);
				}
			}
		}
    	return result;
    }
    
    

    /**
     * @param rfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    @Override
    @Transactional
    public String addOrderAndDetail(RfidBindOrderAndDetailDTO rfidBindOrderAndDetailDTO)throws Exception{
    	 //提货单号
    	 String orderNo = rfidBindOrderAndDetailDTO.getOrderNo();
		 RfidBindOrder rfidBindOrder = selectRfidBindOrderByOrderNo(orderNo);
    	 if(StringUtils.isNotNull(rfidBindOrder)) {
     		throw new BusinessException("该订单已存在，请检查数据.");
     	    }
    	 //先插入订单表
    	 RfidBindOrder rfidBindOrderC =new RfidBindOrder();
    	 BeanUtils.copyProperties(rfidBindOrderAndDetailDTO, rfidBindOrderC);
    	 rfidBindOrderMapper.insertRfidBindOrder(rfidBindOrderC);
    	
		StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
		int successNum = 0;
        int failureNum = 0;
        
        for (RfidBindOrderDetail rfidBindOrderDetail : rfidBindOrderAndDetailDTO.getRfidBindOrderDetails()) {
			try {
		    	 //插入rfid明细表
				 String currentCode = rfidBindSpurtcodeMapper.selectCurrentCodeByRfid(rfidBindOrderDetail.getRfid());
				 if(StringUtils.isEmpty(currentCode)) {
					 throw new BusinessException("请验证当前喷码数据是否有效");
				 }
				 rfidBindOrderDetail.setCurrentCode(currentCode);
				 rfidBindOrderDetail.setRfid(rfidBindOrderDetail.getRfid());
				 rfidBindOrderDetail.setPid(rfidBindOrderC.getId());
				 rfidBindOrderDetailMapper.insertRfidBindOrderDetail(rfidBindOrderDetail);
				 successNum++;
	             successMsg.append("插入成功");
			} catch (Exception e) {
				failureNum++;
	            String msg = "插入失败：";
	            failureMsg.append(msg + e.getMessage());
	            log.error(msg, e);
			}
			
		 }
		if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，插入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部插入成功！共 " + successNum + " 条");
        }
		//根据提货单更新托盘状态
//		String orderNo =rfidBindOrderAndDetailDTO.getOrderNo();
		String factoryCode = rfidBindOrderAndDetailDTO.getFactoryCode();
		trayInfoService.updateStatusByWayBillNo(factoryCode, orderNo, Constants.RFID_STATUS_LOADEDCAR, null);
		return successMsg.toString();
        
        
    }
    
    /**
     * 修改RFID绑定提货单信息
     * 
     * @param rfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
    @Override
    @Transactional
    public String updateRfidBindOrderDetail(List<RfidBindOrderDetailDTO> rfidBindOrderDetailDTOList)throws Exception{
    	if (StringUtils.isNull(rfidBindOrderDetailDTOList) || rfidBindOrderDetailDTOList.size() == 0)
        {
            throw new BusinessException("RFID绑定提货单信息数据不能为空！");
        }	
		StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
		int successNum = 0;
        int failureNum = 0;
		for (RfidBindOrderDetailDTO rfidBindOrderDetailDTO : rfidBindOrderDetailDTOList) {
			try {
				 String orderNo = rfidBindOrderDetailDTO.getOrderNo();
				 RfidBindOrder rfidBindOrder = selectRfidBindOrderByOrderNo(orderNo);
				 if (StringUtils.isNull(rfidBindOrder))
			        {
			            throw new BusinessException("没有查到对应的订单号，请检查数据.");
			        }	
				 RfidBindOrderDetail rfidBindOrderDetail = new RfidBindOrderDetail();
				 rfidBindOrderDetail.setCurrentCode(rfidBindOrderDetailDTO.getCurrentCode());
				 rfidBindOrderDetail.setRfid(rfidBindOrderDetailDTO.getRfid());
				 rfidBindOrderDetail.setPid(rfidBindOrder.getId());
				 rfidBindOrderDetailMapper.insertRfidBindOrderDetail(rfidBindOrderDetail);
				 successNum++;
	             successMsg.append("插入成功");
			} catch (Exception e) {
				failureNum++;
	            String msg = "插入失败：";
	            failureMsg.append(msg + e.getMessage());
	            log.error(msg, e);
			}
			
		 }
		if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，插入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
//            successMsg.insert(0, "恭喜您，数据已全部插入成功！共 " + successNum + " 条");
        	successMsg.insert(0, "操作成功");
        }
		//根据提货单更新托盘状态
//		String orderNo = rfidBindOrderDetailDTOList.get(0).getOrderNo();
//		String factoryCode = rfidBindOrderDetailDTOList.get(0).getFactoryCode();
//		trayInfoService.updateStatusByWayBillNo(factoryCode, orderNo, null, null);
		return successMsg.toString();
    }
    

    /**
     * 删除RFID绑定提货单信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRfidBindOrderByIds(String ids)throws Exception
    {
        return 0;
    }

    /**
     * 删除RFID绑定提货单信息信息
     * 
     * @param id RFID绑定提货单信息ID
     * @return 结果
     */
    @Override
    public int deleteRfidBindOrderById(Integer id)throws Exception
    {
        return rfidBindOrderMapper.deleteRfidBindOrderById(id);
    }

    /**
     *  更新进厂时间
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	@Override
	public int updateEnteringTime(String orderNo) throws Exception{
		
		return rfidBindOrderMapper.updateEnteringTime(orderNo);
	}

	/**
     *  更新出厂时间
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	@Override
	public int updateLeaveTime(String orderNo) throws Exception{
		
		return rfidBindOrderMapper.updateLeaveTime(orderNo);
	}

	/**
     * 批量插入数据
     * @param rfidBindOrderList RFID绑定提货单信息
     * @return 结果
     */
	@Override
	public String batchInsert(List<RfidBindOrder> rfidBindOrderList)throws Exception {
		if (StringUtils.isNull(rfidBindOrderList) || rfidBindOrderList.size() == 0)
        {
            throw new BusinessException("RFID绑定提货单信息数据不能为空！");
        }	
		StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
		int successNum = 0;
        int failureNum = 0;
        for (RfidBindOrder rfidBindOrder : rfidBindOrderList)
        {
            try
            {
                // 验证是否存在这个用户
                {
                	rfidBindOrderMapper.insertRfidBindOrder(rfidBindOrder);
                    successNum++;
                    successMsg.append("插入成功");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "插入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，插入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部插入成功！共 " + successNum + " 条");
        }
		return successMsg.toString();
	}
	
	/**
     *  根据提货单号更新装运状态 
     * 
     * @param tmsRfidBindOrder RFID绑定提货单信息
     * @return 结果
     */
	@Override
	public int updateShipmentStatus(String orderNo) throws Exception{
		
		return rfidBindOrderMapper.updateShipmentStatus(orderNo);
	}

	/**
     * 解除绑定
     */
	@Override
    public int unBind(RfidBindOrderDetailDTO rfidBindOrderDetailDTO) throws Exception{
		
		return rfidBindOrderDetailMapper.unBind(rfidBindOrderDetailDTO);
	}

	
}
