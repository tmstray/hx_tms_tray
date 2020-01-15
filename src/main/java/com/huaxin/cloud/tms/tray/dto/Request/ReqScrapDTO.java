package com.huaxin.cloud.tms.tray.dto.Request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * @Description 报废第一次绑定请求实体
 * @Author lixin
 * @Date 2020/1/7 11:35
 * @Version 1.0
 **/
public class ReqScrapDTO {

    @Length(max=50,message = "最多50个字符")
    private String remark;

    @NotBlank(message = "不能为空")
    private String rfid;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
