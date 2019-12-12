package com.huaxin.cloud.tms.tray.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huaxin.cloud.tms.tray.entity.RfidBindSpurtcode;


/**
 * RFID绑定喷码信息Service接口
 * 
 */
public interface RfidBindSpurtcodeService 
{
    /**
     * 查询RFID绑定喷码信息列表
     * 
     * @param
     * @return RFID绑定喷码信息集合
     */
    public List<Map<String ,Object>> selectRfidBindSpurtcodeList(Date startTime, Date endTime);

    /**
     * 设备生产流程第一次绑定喷码和托盘码
     * 
     * @param rfid
     * @return 结果
     */
    public int insertRfidBindSpurtcode(String rfid);

    /**
     * 系统修改界面，重新修改绑定托盘码和喷码
     * @param currentCode
     * @param rfid
     * @return
     */
    public int updateBind(String currentCode,String rfid);

    /**
     * 查询第一次绑定状态接口
     * @return
     */
    Map<String, Object> selectFirstBind();

    /**
     * 报废第一次绑定关系
     * @param remark
     * @return
     */
    int scrap(String remark,String rfid);

}
