package com.huaxin.cloud.tms.tray.dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huaxin.cloud.tms.tray.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 托盘基础信息管理对象 
 */
public class ResTrayInfoDTO extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 工厂代码 */
    private String factoryCode;

    /** 工厂名称 */
    private String factoryName;

    /** 托盘ID */
    private String rfid;

    /** 托盘类型 */
    private Integer rfidType;

    /** 托盘状态 */
    private Integer rfidStatus;

    /** 初始化时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date initTime;

    /** 绑定时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date bindingTime;

    /** 托盘类型名称 */
    private String rfidTypeName;

    /** 托盘状态名称 */
    private String rfidStatusName;

    /** 托盘数量 */
    private String rfidCount;

    /** 出厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date leaveTime;
    /** 回厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returnTime;

    /**第一次绑定水泥码（包装喷码）*/
    private String currentCode;
    /**第二次绑定（交货单号）*/
    private String orderNo;

    /** 物料编号 */
    @ApiModelProperty(value = "物料编号")
    private String meterielCode;

    /** 物料描述 */
    @ApiModelProperty(value = "物料描述 ")
    private String meterielDesc;

    public String getMeterielCode() {
        return meterielCode;
    }

    public void setMeterielCode(String meterielCode) {
        this.meterielCode = meterielCode;
    }

    public String getMeterielDesc() {
        return meterielDesc;
    }

    public void setMeterielDesc(String meterielDesc) {
        this.meterielDesc = meterielDesc;
    }

    /**
     * 托盘健康状态
     */
    private String rfidHealth;

    public String getRfidHealth() {
        return rfidHealth;
    }

    public void setRfidHealth(String rfidHealth) {
        this.rfidHealth = rfidHealth;
    }

    public String getRfidHealthName() {
        return rfidHealthName;
    }

    public void setRfidHealthName(String rfidHealthName) {
        this.rfidHealthName = rfidHealthName;
    }

    private String rfidHealthName;




    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getRfidTypeName() {
        return rfidTypeName;
    }

    public String getRfidStatusName() {
        return rfidStatusName;
    }

    public String getRfidCount() {
        return rfidCount;
    }

    public void setRfidTypeName(String rfidTypeName) {
        this.rfidTypeName = rfidTypeName;
    }

    public void setRfidStatusName(String rfidStatusName) {
        this.rfidStatusName = rfidStatusName;
    }

    public void setRfidCount(String rfidCount) {
        this.rfidCount = rfidCount;
    }


    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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
    public void setRfid(String rfid) 
    {
        this.rfid = rfid;
    }

    public String getRfid() 
    {
        return rfid;
    }
    public void setRfidType(Integer rfidType) 
    {
        this.rfidType = rfidType;
    }

    public Integer getRfidType() 
    {
        return rfidType;
    }
    public void setRfidStatus(Integer rfidStatus) 
    {
        this.rfidStatus = rfidStatus;
    }

    public Integer getRfidStatus() 
    {
        return rfidStatus;
    }
    public void setInitTime(Date initTime) 
    {
        this.initTime = initTime;
    }

    public Date getInitTime() 
    {
        return initTime;
    }
    public void setBindingTime(Date bindingTime) 
    {
        this.bindingTime = bindingTime;
    }

    public Date getBindingTime() 
    {
        return bindingTime;
    }
}