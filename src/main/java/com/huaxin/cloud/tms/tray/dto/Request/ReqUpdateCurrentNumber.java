package com.huaxin.cloud.tms.tray.dto.Request;

/**
 * @Description 修改当前已喷水泥代数请求实体
 * @Author lixin
 * @Date 2020/1/7 11:13
 * @Version 1.0
 **/
public class ReqUpdateCurrentNumber {

    private int currentNumber;

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }
}
