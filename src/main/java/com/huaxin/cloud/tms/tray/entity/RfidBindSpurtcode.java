package com.huaxin.cloud.tms.tray.entity;

/**
 * 喷码信息对象 sys_tms_spurtcode_info
 */
public class RfidBindSpurtcode extends BaseEntity {
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

    /** 当前编码 */
    private String currentCode;

    /** 已装数量  */
    private int installedQuantity;

    /** 包装单位 */
    private String unit;

    /** RFID编号 */
    private String rfid;

    /** 是否被使用 */
    private Integer isHaving;


    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
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
    public void setCurrentCode(String currentCode)
    {
        this.currentCode = currentCode;
    }

    public String getCurrentCode()
    {
        return currentCode;
    }

    public int getInstalledQuantity() {
        return installedQuantity;
    }

    public void setInstalledQuantity(int installedQuantity) {
        this.installedQuantity = installedQuantity;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setRfid(String rfid)
    {
        this.rfid = rfid;
    }

    public String getRfid()
    {
        return rfid;
    }
    public void setIsHaving(Integer isHaving)
    {
        this.isHaving = isHaving;
    }

    public Integer getIsHaving()
    {
        return isHaving;
    }
}