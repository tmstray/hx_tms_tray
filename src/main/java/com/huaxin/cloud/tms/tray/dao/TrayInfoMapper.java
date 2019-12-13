package com.huaxin.cloud.tms.tray.dao;

import com.huaxin.cloud.tms.tray.dto.Request.ReqTrayInfoDTO;
import com.huaxin.cloud.tms.tray.dto.Response.ResTrayInfoDTO;
import com.huaxin.cloud.tms.tray.entity.TrayInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface TrayInfoMapper {
    int deleteByPrimaryKey(Integer id,String updateBy);

    int insertSelective(TrayInfo record);

    int insertBatch(@Param("records") List<TrayInfo> records);

    int updateByRfid(TrayInfo record);

    List<ResTrayInfoDTO> selectTrayInfoList(ReqTrayInfoDTO trayInfo);

    List<ResTrayInfoDTO> selectTrayInfoFull(ReqTrayInfoDTO trayInfo);


    List<ResTrayInfoDTO> selectListGroupbyStatus(ReqTrayInfoDTO trayInfo);

    int updateStatusByWayBillNo(@Param("factoryCode") String factoryCode,
                                @Param("orderNo") String orderNo,
                                @Param("rfidStatus") Integer rfidStatus,
                                @Param("updateBy") String updateBy);

    ResTrayInfoDTO getTrayInfoByRfid(String rfid,String factoryCode);

    /**
     * 根据rfid和空托状态查询，是否有该空托盘
     * @param rfid
     * @return
     */
    TrayInfo selectTrayInfoByRfidAndRfidStatus(@Value("rfid") String rfid);

    /**
     *修改托盘状态
     * @param rfidStatus
     * @param rfid
     * @return
     */
    int updateRfidStatus(@Param("rfidStatus") Integer rfidStatus,@Param("rfid") String rfid);
}