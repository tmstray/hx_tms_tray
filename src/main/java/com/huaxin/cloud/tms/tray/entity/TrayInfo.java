package com.huaxin.cloud.tms.tray.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huaxin.cloud.tms.tray.common.annotation.Excel;

import java.util.Date;

/**
 * 托盘基础信息管理对象 tms_tray_info
 */
public class TrayInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 工厂代码 */
    private String factoryCode;

    /** 工厂名称 */
    private String factoryName;

    /** 托盘ID */
    @Excel(name = "托盘ID")
    private String rfid;

    /** 托盘类型
     *1	木质
     *2 塑胶
     *3	其它
     */
    private Integer rfidType;

    /** 托盘流转状态
    *1	满盘
     *2	空拖
     *3	在途（中转库）
     *4	异地(客户） 
     *5 已装车
     */
    private Integer rfidStatus;



    /**
     * 托盘健康状态
     * 0 良好
     * 1 报损
     * 2 报废
     * 3 冻结
     */
    private Integer rfidHealth;

    /**
     * 报损原因
     */
    private String damagedReason;

    /**
     * 报废原因
     */
    private String scrappedReason;

    public String getDamagedReason() {
        return damagedReason;
    }

    public void setDamagedReason(String damagedReason) {
        this.damagedReason = damagedReason;
    }

    public String getScrappedReason() {
        return scrappedReason;
    }

    public void setScrappedReason(String scrappedReason) {
        this.scrappedReason = scrappedReason;
    }

    public Integer getRfidHealth() {
        return rfidHealth;
    }
    public void setRfidHealth(Integer rfidHealth) {
        this.rfidHealth = rfidHealth;
    }

    /** 初始化时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date initTime;

    /** 绑定时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date bindingTime;

    /** 出厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date leaveTime;
    /** 回厂时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returnTime;

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