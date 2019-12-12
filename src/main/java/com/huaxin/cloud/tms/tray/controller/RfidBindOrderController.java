package com.huaxin.cloud.tms.tray.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.enums.BusinessType;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.page.TableDataInfo;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderAndDetailDTO;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderDTO;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderDetailDTO;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrder;
import com.huaxin.cloud.tms.tray.service.RfidBindOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * RFID绑定提货单信息Controller
 * 
 */
@Api(tags = "RFID绑定提货单")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class RfidBindOrderController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(RfidBindOrderController.class);
	
	private String message;

    @Autowired
    private RfidBindOrderService rfidBindOrderService;

    
    /**
     *查询提货单信息列表
     */
    @ApiOperation(value = "查询提货单信息列表")
    @GetMapping("/orderlist")
    public TableDataInfo RfidBindOrderList(RfidBindOrderDTO rfidBindOrderDTO)
    {
    	startPage();
    	RfidBindOrder rfidBindOrder =new RfidBindOrder();
    	BeanUtils.copyProperties(rfidBindOrderDTO, rfidBindOrder);
        List<RfidBindOrder> list = rfidBindOrderService.selectOrderList(rfidBindOrder);
        return getDataTable(list);
    }

    /**
     * 查询RFID绑定提货单信息列表
     */
    @ApiOperation(value = "查询RFID绑定提货单信息列表")
    @GetMapping("/list")
    public TableDataInfo list(RfidBindOrderDTO rfidBindOrderDTO)
    {
    	startPage();
    	RfidBindOrder rfidBindOrder =new RfidBindOrder();
    	BeanUtils.copyProperties(rfidBindOrderDTO, rfidBindOrder);
        List<RfidBindOrder> list = rfidBindOrderService.selectRfidBindOrderList(rfidBindOrder);
        return getDataTable(list);
    }

    /**
     * 根据RFID绑定提货单编号获取详细信息
     */
    @ApiOperation(value = "根据id查询RFID绑定提货单信息列表")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "id", value = "主键id", required = true)
    public ResultInfo getInfo(@PathVariable Integer id)
    {
        return ResultInfo.success(rfidBindOrderService.selectRfidBindOrderById(id));
    }

    /**
     * 新增提货单号信息
     */
    @Log(title = "新增提货单号信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增提货单号信息")
    public ResultInfo add(@RequestBody RfidBindOrderDTO rfidBindOrderDTO) throws BusinessException{
    	try {
    		RfidBindOrder rfidBindOrder =new RfidBindOrder();
        	BeanUtils.copyProperties(rfidBindOrderDTO, rfidBindOrder);
        	return toAjax(rfidBindOrderService.insertRfidBindOrder(rfidBindOrder));
		} catch (Exception e) {
			message = "新增提货单号信息失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
		}
    	
    }
   
    /**
     * 新增RFID绑定提货单信息
     */
    @Log(title = "新增RFID绑定提货单信息", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "新增RFID绑定提货单信息")
    @PostMapping("/edit")
    public ResultInfo edit(@RequestBody List<RfidBindOrderDetailDTO> rfidBindOrderDetailDTOList)throws BusinessException {
    	try {
	    	 String message = rfidBindOrderService.updateRfidBindOrderDetail(rfidBindOrderDetailDTOList);
	         return ResultInfo.success(message);
    	} catch (Exception e) {
			message = "新增RFID绑定提货单信息失败";
	        log.error(message, e);
	        throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
    	}
    }
    
    /**
     * 同时增加订单和rfid明细
     */
    @Log(title = "同时增加订单和rfid明细", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "同时增加订单和rfid明细")
    @PostMapping("/addOrderAndDetail")
    public ResultInfo addOrderAndDetail(@RequestBody RfidBindOrderAndDetailDTO rfidBindOrderAndDetailDTO)throws BusinessException {
    	try {
	    	 String message = rfidBindOrderService.addOrderAndDetail(rfidBindOrderAndDetailDTO);
	         return ResultInfo.success(message);
    	} catch (Exception e) {
			message = "修改RFID绑定提货单信息失败";
	        log.error(message, e);
	        throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
    	}
    }

    /**
     * 删除RFID绑定提货单信息
     */
    @Log(title = "删除RFID绑定提货单信息", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除RFID绑定提货单信息")
    @DeleteMapping("/{id}")
    public ResultInfo remove(@PathVariable Integer id)throws BusinessException
    {
    	try {
    		return toAjax(rfidBindOrderService.deleteRfidBindOrderById(id));
		} catch (Exception e) {
			message = "删除RFID绑定提货单信息失败";
	        log.error(message, e);
	        throw new BusinessException(message);
		}
    }
    
    /**
     * 更新进厂时间
     */
    @Log(title = "更新进厂时间", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新进厂时间")
    @PutMapping("/updateEnteringTime")
    public ResultInfo updateEnteringTime(@RequestBody String orderNo)throws BusinessException {
    	try {
    		return toAjax(rfidBindOrderService.updateEnteringTime(orderNo));
		} catch (Exception e) {
			message = "更新进厂时间失败";
	        log.error(message, e);
	        throw new BusinessException(message);
		}
    }
    
    /**
     * 更新出厂时间
     */
    @Log(title = "更新出厂时间", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新出厂时间")
    @PutMapping("/updateLeaveTime")
    public ResultInfo updateLeaveTime(@RequestBody String orderNo)throws BusinessException {
    	try {
    		return toAjax(rfidBindOrderService.updateLeaveTime(orderNo));
		} catch (Exception e) {
			message = "更新出厂时间失败";
	        log.error(message, e);
	        throw new BusinessException(message);
		}
    }
    
    /**
     * 批量插入数据
     */
    @Log(title = "批量插入数据", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "批量插入数据")
    @PostMapping("/batchInsert")
    public ResultInfo batchInsert(@RequestBody List<RfidBindOrderDTO> rfidBindOrderDTOList)throws BusinessException{
    	try {
    		List<RfidBindOrder> rfidBindOrderList =new ArrayList<RfidBindOrder>();
    		BeanUtils.copyProperties(rfidBindOrderDTOList, rfidBindOrderList);
    		String message = rfidBindOrderService.batchInsert(rfidBindOrderList);
    		return ResultInfo.success(message);
		} catch (Exception e) {
			message = "批量插入数据失败";
	        log.error(message, e);
	        throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
		}
    }
    
    /**
     * 根据提货单号更新装运状态 
     */
    @Log(title = "根据提货单号更新装运状态 ", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新装运状态 ")
    @PutMapping("/updateShipmentStatus")
    public ResultInfo updateShipmentStatus(@RequestBody String orderNo) throws BusinessException{
    	try {
    		return toAjax(rfidBindOrderService.updateShipmentStatus(orderNo));
		} catch (Exception e) {
			message = "更新装运状态失败";
	        log.error(message, e);
	        throw new BusinessException(message);
		}
    	
    }
    
    /**
     * 解除绑定
     */
    @Log(title = "解除绑定", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "解除绑定 ")
    @DeleteMapping("/unBind")
    public ResultInfo unBind(@RequestBody RfidBindOrderDetailDTO rfidBindOrderDetailDTO) throws BusinessException{
    	try {
    		return toAjax(rfidBindOrderService.unBind(rfidBindOrderDetailDTO));
		} catch (Exception e) {
			message = "解除绑定失败";
	        log.error(message, e);
	        throw new BusinessException(message);
		}
    }
    
}
