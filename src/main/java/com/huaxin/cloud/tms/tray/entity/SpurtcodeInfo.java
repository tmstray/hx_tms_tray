package com.huaxin.cloud.tms.tray.entity;

/**
 * RFID绑定喷码信息对象 sys_tms_rfid_bind_spurtcode
 */
public class SpurtcodeInfo extends BaseEntity {
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
    
    /** 当前数量 */
    private Integer currentNumber;

    /**
     * 喷码状态
     * 0-喷码已完成托盘id绑定，1-等待绑定的喷码，2-正在喷涂水泥的喷码
     */
    private Integer currentStatus;
    
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
    
    
    public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }
}