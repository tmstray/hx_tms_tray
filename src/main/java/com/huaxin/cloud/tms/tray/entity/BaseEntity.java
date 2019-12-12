package com.huaxin.cloud.tms.tray.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Entity基类
 */
@ApiModel(description = "Entity基类")
public class BaseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
    /** 创建者 */
	@ApiModelProperty(value = "创建者")
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date updateTime;

    /** 版本号 */
    @ApiModelProperty(value = "版本号 ")
    private String version;

    /** 删除标志
	 * 	0	有效
	 * 	1	无效
	 * */
    @ApiModelProperty(value = "版本号 " , example = "123")
    private Integer deleteFlag;
    
    /** 备注 */
    @ApiModelProperty(value = "备注 ")
    private String remarks;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
