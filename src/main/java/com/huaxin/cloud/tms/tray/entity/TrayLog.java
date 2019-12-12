package com.huaxin.cloud.tms.tray.entity;

import java.util.Date;

/**
 * 托盘管理操作日志对象 sys_tms_tray_log
 * 
 */
public class TrayLog {

    /** 主键ID */
    private Integer id;
    
    /** 操作模块 */
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer businessType;


    /** 工厂编码 */
    private String factoryCode;

    /** 操作方法 */
    private String method;


    /** 操作者IP */
    private String ip;
    
    
    /** 返回参数 */
    private String jsonResult;
    
    /** 请求url */
    private String operUrl;
    
    /** 请求方式 */
    private String requestMethod;
    
    /** 操作类别（0其它 1后台用户 2手机端用户） */
    private Integer operatorType;
    
    /** 请求参数 */
    private String operParam;
    
    /** 操作地点 */
    private String operLocation;
    
    /** 操作状态（0正常 1异常） */
    private Integer status;
    
    /** 错误消息 */
    private String errorMsg;
    
    
    
    /** 备注 */
    private String remarks;

    /** 删除标志 */
    private Integer deleteFlag;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;
    
    

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setBusinessType(Integer businessType) 
    {
        this.businessType = businessType;
    }

    public Integer getBusinessType() 
    {
        return businessType;
    }


    public void setFactoryCode(String factoryCode) 
    {
        this.factoryCode = factoryCode;
    }

    public String getFactoryCode() 
    {
        return factoryCode;
    }

    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }

    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }

    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    public void setDeleteFlag(Integer deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag() 
    {
        return deleteFlag;
    }

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

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOperParam() {
		return operParam;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}

	public String getOperLocation() {
		return operLocation;
	}

	public void setOperLocation(String operLocation) {
		this.operLocation = operLocation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
	
}