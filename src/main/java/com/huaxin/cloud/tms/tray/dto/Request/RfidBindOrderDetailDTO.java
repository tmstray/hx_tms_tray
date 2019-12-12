package com.huaxin.cloud.tms.tray.dto.Request;
import com.huaxin.cloud.tms.tray.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 新增提货单绑定RFID明细
 * 
 * @param rfidBindOrder RFID绑定提货单信息
 * @return 结果
 */
@ApiModel(description = "提货单绑定RFID明细表")
public class RfidBindOrderDetailDTO extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /** 当前编码 */
	@ApiModelProperty(value = "当前编码")
    private String currentCode;

    /** RFID编号 */
	@ApiModelProperty(value = "RFID编号")
    private String rfid;

    /** 交货单号 */
	@ApiModelProperty(value = "交货单号 ")
    private String orderNo;
    
    /** 工厂代码 */
	@ApiModelProperty(value = "工厂代码")
    private String factoryCode;
    
    
    public void setCurrentCode(String currentCode) 
    {
        this.currentCode = currentCode;
    }

    public String getCurrentCode() 
    {
        return currentCode;
    }
    public void setRfid(String rfid) 
    {
        this.rfid = rfid;
    }

    public String getRfid() 
    {
        return rfid;
    }
    
    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	
}
