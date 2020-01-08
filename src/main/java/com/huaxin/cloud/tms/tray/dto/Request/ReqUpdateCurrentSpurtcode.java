package com.huaxin.cloud.tms.tray.dto.Request;

import javax.validation.constraints.Pattern;

/**
 * @Description 修改当前喷码请求实体
 * @Author lixin
 * @Date 2020/1/7 11:09
 * @Version 1.0
 **/
public class ReqUpdateCurrentSpurtcode {

    @Pattern(regexp = "^[A-Za-z0-9]{18}$", message = "喷码必须为18位字母或数字组成的字符串")
    private String currentSpurtcode;

    public String getCurrentSpurtcode() {
        return currentSpurtcode;
    }

    public void setCurrentSpurtcode(String currentSpurtcode) {
        this.currentSpurtcode = currentSpurtcode;
    }
}
