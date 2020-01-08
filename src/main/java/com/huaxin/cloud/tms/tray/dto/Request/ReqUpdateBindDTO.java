package com.huaxin.cloud.tms.tray.dto.Request;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;

/**
 * @Description 修改绑定关系，请求实体
 * @Author lixin
 * @Date 2020/1/7 11:32
 * @Version 1.0
 **/
public class ReqUpdateBindDTO {

    @Pattern(regexp = "^[A-Za-z0-9]{18}$", message = "喷码必须为18位字母或数字组成的字符串")
    private String currentCode;

    private String rfid;

    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
