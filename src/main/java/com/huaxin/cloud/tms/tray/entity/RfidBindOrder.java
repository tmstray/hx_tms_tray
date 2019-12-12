package com.huaxin.cloud.tms.tray.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;

/**
 * RFID绑定提货单信息对象 tms_rfid_bind_order
 */
@ApiModel(description = "RFID绑定提货单信息对象")
public class RfidBindOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 物料编号 */
    private String meterielCode;

    /** 物料描述 */
    private String meterielDesc;

    /** 工厂代码 */
    private String factoryCode;

    /** 工厂描述 */
    private String factoryName;

    /** 电子提货单号 */
    private String waybillNo;

    /** 车牌号 */
    private String licensePlate;

    /** 司机姓名 */
    private String driverName;

    /** 司机电话 */
    private String driverPhone;

    /** 交货单号 */
    private String orderNo;

    /** 商品规格 */
    private String productName;

    /** 送货地址 */
    private String deliveryAddress;

    /** 提货数量  */
    private Double goodsNumber;

    /** 装运状态  */
    private Integer shipmentStatus;

    /** 绑定时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date bindingTime;

    /** 进厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date enteringTime;

    /** 出厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date leaveTime;
    
    /** 明细对象 */
    private List<RfidBindOrderDetail> rfidBindOrderDetails;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMeterielCode(String meterielCode) 
    {
        this.meterielCode = meterielCode;
    }

    public String getMeterielCode() 
    {
        return meterielCode;
    }
    public void setMeterielDesc(String meterielDesc) 
    {
        this.meterielDesc = meterielDesc;
    }

    public String getMeterielDesc() 
    {
        return meterielDesc;
    }
    public void setFactoryCode(String factoryCode) 
    {
        this.factoryCode = factoryCode;
    }

    public String getFactoryCode() 
    {
        return factoryCode;
    }
    public void setFactoryName(String factoryName) 
    {
        this.factoryName = factoryName;
    }

    public String getFactoryName() 
    {
        return factoryName;
    }
    
    public void setWaybillNo(String waybillNo) 
    {
        this.waybillNo = waybillNo;
    }

    public String getWaybillNo() 
    {
        return waybillNo;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setDriverPhone(String driverPhone) 
    {
        this.driverPhone = driverPhone;
    }

    public String getDriverPhone() 
    {
        return driverPhone;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setDeliveryAddress(String deliveryAddress) 
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() 
    {
        return deliveryAddress;
    }
    public void setGoodsNumber(Double goodsNumber) 
    {
        this.goodsNumber = goodsNumber;
    }

    public Double getGoodsNumber() 
    {
        return goodsNumber;
    }
    public void setShipmentStatus(Integer shipmentStatus) 
    {
        this.shipmentStatus = shipmentStatus;
    }

    public Integer getShipmentStatus() 
    {
        return shipmentStatus;
    }
    public void setBindingTime(Date bindingTime) 
    {
        this.bindingTime = bindingTime;
    }

    public Date getBindingTime() 
    {
        return bindingTime;
    }
    public void setEnteringTime(Date enteringTime) 
    {
        this.enteringTime = enteringTime;
    }

    public Date getEnteringTime() 
    {
        return enteringTime;
    }
    public void setLeaveTime(Date leaveTime) 
    {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime() 
    {
        return leaveTime;
    }

	public List<RfidBindOrderDetail> getRfidBindOrderDetails() {
		return rfidBindOrderDetails;
	}

	public void setRfidBindOrderDetails(List<RfidBindOrderDetail> rfidBindOrderDetails) {
		this.rfidBindOrderDetails = rfidBindOrderDetails;
	}
    
    
}