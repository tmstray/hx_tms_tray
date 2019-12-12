package com.huaxin.cloud.tms.tray.dao;

import com.huaxin.cloud.tms.tray.entity.RfidBindSpurtcode;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RfidBindSpurtcodeMapper {
    /**
     * 根据主键id删除绑定信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的绑定记录
     * @param record
     * @return
     */
    int insert(RfidBindSpurtcode record);

    /**
     * 插入字段不为null的绑定记录
     * @param record
     * @return
     */
    int insertSelective(RfidBindSpurtcode record);

    /**
     * 根据主键id查询绑定记录
     * @param id
     * @return
     */
    RfidBindSpurtcode selectByPrimaryKey(Integer id);

    /**
     * 更新不为null的字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RfidBindSpurtcode record);

    /**
     * 全部字段更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(RfidBindSpurtcode record);

    /**
     * 查询绑定清单
     * @return
     */
    List<Map<String ,Object>> selectRfidBindSpurtcodeList(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     *解除绑定关系
     * @return
     */
    int updateDeleteFlag(@Param("rfid") String rfid);

    /**
     * 根据喷码和rfid查询是否已有绑定生效的绑定关系
     * @param
     * @param rfid
     * @return
     */
    RfidBindSpurtcode selectByCurrentCodeAndRfid( @Param("rfid") String rfid);

    /**
     * 更新托盘码绑定表的 是否使用状态
     * @param rfid
     * @return
     */
    int updateIsHavaing(@Param("rfid") String rfid);

    /**
     * 根据托盘rfid查询当前绑定生效的喷码
     * @param rfid
     * @return
     */
    String selectCurrentCodeByRfid(@Param("rfid") String rfid);

    /**
     * 查询今天已生产的总数
     * @return
     */
    int selectTotalTotal();

    /**
     * 查询最新一条绑定关系
     * @return
     */
    RfidBindSpurtcode selectNewest();

    /**
     * 修改绑定关系状态为删除作废
     * @param remark
     * @return
     */
    int update(@Param("remark") String remark,@Param("rfid") String rfid);

    /**
     * 根据rfid查询喷码信息
     * @param rfid
     * @return
     */
    SpurtcodeInfo selectSpurtCodeByRfid(@Param("rfid") String rfid);

    /**
     * 根据rfid查询绑定关系数量
     * @param rfid
     * @return
     */
    int selectByRfid(@Param("rfid") String rfid);

    /**
     * 更改绑定关系
     * @param currentCode
     * @param rfid
     */
    int updateBind(@Param("currentCode") String currentCode,@Param("rfid") String rfid);
}