package com.huaxin.cloud.tms.tray.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 提货单绑定RFID明细对象 sys_tms_rfid_bind_order_detail
 */
@ApiModel(description = "提货单绑定RFID明细对象")
public class RfidBindOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 当前编码 */
    @ApiModelProperty(value = "当前编码")
    private String currentCode;

    /** RFID编号 */
    @ApiModelProperty(value = "RFID编号")    
    private String rfid;

    /** 删除标志 */
    private Integer deleteFlag;

    /** 父表ID */
    private Integer pid;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
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
    @Override
    public void setDeleteFlag(Integer deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public Integer getDeleteFlag()
    {
        return deleteFlag;
    }
    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("currentCode", getCurrentCode())
            .append("rfid", getRfid())
            .append("createTime", getCreateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("pid", getPid())
            .toString();
    }
}

