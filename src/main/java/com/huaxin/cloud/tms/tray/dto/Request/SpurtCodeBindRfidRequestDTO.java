package com.huaxin.cloud.tms.tray.dto.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * @Description 查询喷码和托盘rfid已绑定清单请求实体
 * @Author lixin
 * @Date 2019/11/25 10:55
 * @Version 1.0
 **/
@ApiModel(description = "RFID绑定提货单信息对象")
public class SpurtCodeBindRfidRequestDTO {

//    @ApiModelProperty(value = "第几页" ,example = "1")
    @Min(value = 0,message = "页码大于等于0")
    private int pageNum;

//    @ApiModelProperty(value = "每页几条" ,example = "1")
    @Size(min = 1,max = 1000,message = "每页数据条数范围为1~1000")
    private int pageSize;

    @ApiModelProperty(value = "查询的开始时间" ,example = "2019-12-03")
    @Pattern(regexp = "^(\\d{4}|\\d{2})\\-((0*([1-9]{1}))|(1([0-2]{1})))-(([1-2][0-9]{1})|(3[0|1]{1})|(0*([1-9])))$",
            message = "格式必须为yyyy-MM-dd")
    private String startTime;

    @ApiModelProperty(value = "查询的结束时间" ,example = "2019-12-03")
    @Pattern(regexp = "^(\\d{4}|\\d{2})\\-((0*([1-9]{1}))|(1([0-2]{1})))-(([1-2][0-9]{1})|(3[0|1]{1})|(0*([1-9])))$",
    message = "格式必须为yyyy-MM-dd")
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SpurtCodeBindRfidRequestDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
