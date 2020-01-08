package com.huaxin.cloud.tms.tray.dto.Request;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;

/**
 * @Description 修改喷码规则请求实体
 * @Author lixin
 * @Date 2020/1/7 11:15
 * @Version 1.0
 **/
public class ReqUpdateRuleDTO {

    @Pattern(regexp = "^[A-Za-z0-9]{1,5}$", message = "规则必须为1~5位字母或数字组成的字符串")
    private String prefixRule;

    private String zId;

    public String getPrefixRule() {
        return prefixRule;
    }

    public void setPrefixRule(String prefixRule) {
        this.prefixRule = prefixRule;
    }

    public String getzId() {
        return zId;
    }

    public void setzId(String zId) {
        this.zId = zId;
    }
}
