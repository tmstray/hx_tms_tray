package com.huaxin.cloud.tms.tray.printer.util;


import com.huaxin.cloud.tms.tray.printer.service.Code;

import java.io.Serializable;

/**
 * 简单封装返回参数
 * @author xiongjinfeng 2019/12/11
 */
public class Result implements Serializable {

    private Integer code;
    private Object data;
    private String msg;
    protected byte adr;
    protected byte status;

    public byte getAdr() {
        return adr;
    }

    public void setAdr(byte adr) {
        this.adr = adr;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public static final String MSG_OK = "success";
    public Result() {

    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result error(String msg) {
        return new Result(Code.FAILED, msg);
    }

    public static Result paramError(String msg){
        return new Result(Code.PARAM_IS_NULL,msg);
    }

    public static Result paramError(){
        return new Result(Code.PARAM_IS_NULL,"参数校验错误");
    }

    public static Result success(String msg) {
        return new Result(Code.SUCCESS, msg);
    }

    public static Result success() {
        return new Result(Code.SUCCESS, MSG_OK);
    }

    public static Result success(String msg, Object data) {
        return new Result(Code.SUCCESS, data, msg);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
