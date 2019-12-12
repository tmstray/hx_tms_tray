package com.huaxin.cloud.tms.tray.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaxin.cloud.tms.tray.common.annotation.DataSource;
import com.huaxin.cloud.tms.tray.common.constant.HttpStatus;
import com.huaxin.cloud.tms.tray.common.enums.DataSourceType;
import com.huaxin.cloud.tms.tray.common.exception.CustomException;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.dao.RfidBindSpurtcodeMapper;
import com.huaxin.cloud.tms.tray.dao.SpurtcodeInfoMapper;
import com.huaxin.cloud.tms.tray.dao.TrayInfoMapper;
import com.huaxin.cloud.tms.tray.entity.RfidBindSpurtcode;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import com.huaxin.cloud.tms.tray.entity.TrayInfo;
import com.huaxin.cloud.tms.tray.service.RfidBindSpurtcodeService;
import com.huaxin.cloud.tms.tray.service.TrayInfoService;
import org.springframework.web.client.RestTemplate;

/**
 * RFID绑定喷码信息Service业务层处理
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RfidBindSpurtcodeServiceImpl implements RfidBindSpurtcodeService {

    private static final Logger log = LoggerFactory.getLogger(RfidBindSpurtcodeServiceImpl.class);

    @Value(("${spurtcode.number}"))
    private int number;

    @Autowired
    private RfidBindSpurtcodeMapper rfidBindSpurtcodeMapper;

    @Autowired
    private SpurtcodeInfoMapper spurtcodeInfoMapper;

    @Autowired
    private TrayInfoMapper trayInfoMapper;

    /**
     * 查询RFID绑定喷码信息列表
     *
     * @param
     * @return RFID绑定喷码信息
     */
    @Override
    public List<Map<String, Object>> selectRfidBindSpurtcodeList(Date startTime, Date endTime) {
        return rfidBindSpurtcodeMapper.selectRfidBindSpurtcodeList(startTime, endTime);
    }

    /**
     * 设备生产流程第一次绑定喷码和托盘码
     *
     * @param rfid
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRfidBindSpurtcode(String rfid) {
        //校验是否有该空托盘
        TrayInfo trayInfo = trayInfoMapper.selectTrayInfoByRfidAndRfidStatus(rfid);
        if (trayInfo == null) {
            log.error("无法找到该空托盘，不能进行绑定");
            throw new BusinessException("无法找到该空托盘，不能进行绑定");
        }
        RfidBindSpurtcode rfidBindSpurtcode = new RfidBindSpurtcode();
        //查询当前等待绑定的喷码信息
        SpurtcodeInfo spurtcodeInfo = spurtcodeInfoMapper.selectByCurrentStatus();
        BeanUtils.copyProperties(spurtcodeInfo, rfidBindSpurtcode);
        rfidBindSpurtcode.setRfid(rfid);
        rfidBindSpurtcode.setInstalledQuantity(number);
        rfidBindSpurtcode.setUnit(Constants.UNIT);
        rfidBindSpurtcode.setVersion(Constants.VERSION);
        rfidBindSpurtcode.setCreateTime(new Date());
        rfidBindSpurtcode.setUpdateTime(null);
        //插入绑定信息
        rfidBindSpurtcodeMapper.insertSelective(rfidBindSpurtcode);
        //更新喷码状态,待绑定->已经绑定
        spurtcodeInfo.setCurrentStatus(Constants.CURRENT_STATUS_OK);
        spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfo);
        //更改托盘状态,空托-->满托
        return trayInfoMapper.updateRfidStatus(Constants.RFID_STATUS_FULL, rfid);
    }

    /**
     * 系统修改界面，重新修改绑定托盘码和喷码
     * 系统录入的喷码有可能不存在，但是目前需求，先不管，还是给定一个绑定关系，
     * 但是改变喷码状态时需要做一个判断
     *
     * @param currentCode
     * @param rfid
     * @return
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateBind(String currentCode, String rfid) {
//        RfidBindSpurtcode rfidBindSpurtcodeOrign = rfidBindSpurtcodeMapper.selectByCurrentCodeAndRfid(rfid);
//        if(rfidBindSpurtcodeOrign==null){
//            log.error("该绑定记录不存在，不能修改");
//            throw new BusinessException("该绑定记录不存在，不能修改");
//        }
//        if(currentCode!=null){
//            //判断要修改的喷码和原来喷码是否一样，一样不做操作，直接返回成功
//            if(currentCode.equals(rfidBindSpurtcodeOrign.getCurrentCode())){
//                return 1;
//            }
//            //判断喷码是否是数据中喷码，更新喷码状态
//            SpurtcodeInfo spurtcodeInfoOrign = spurtcodeInfoMapper.selectSpurtcodeByCurrentCode(currentCode);
//            //如果喷码存在数据库中
//            if (spurtcodeInfoOrign != null) {
//                if (spurtcodeInfoOrign.getCurrentStatus().equals(Constants.CURRENT_STATUS_USED)) {
//                    log.error("要绑定的喷码为喷码机正在使用的喷码，不能绑定");
//                    throw new BusinessException("要绑定的喷码为喷码机正在使用的喷码，不能绑定");
//                } else if(spurtcodeInfoOrign.getCurrentStatus().equals(Constants.CURRENT_STATUS_OK)){
//                    log.error("要绑定的喷码已经进行过绑定，不能绑定");
//                    throw new BusinessException("要绑定的喷码已经进行过绑定，不能绑定");
//                }else {
//                    //更改绑定关系
//                    rfidBindSpurtcodeOrign.setCurrentCode(currentCode);
//                    rfidBindSpurtcodeMapper.updateByPrimaryKeySelective(rfidBindSpurtcodeOrign);
//                    //更改喷码状态 等待绑定->已绑定
//                    spurtcodeInfoOrign.setCurrentStatus(Constants.CURRENT_STATUS_OK);
//                    return spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfoOrign);
//                }
//            } else {
//                //如果喷码不存在数据库中，往数据库中插入喷码信息
//                SpurtcodeInfo spurtcodeInfo = rfidBindSpurtcodeMapper.selectSpurtCodeByRfid(rfid);
//                SpurtcodeInfo newSpurtcodeInfo = new SpurtcodeInfo();
//                newSpurtcodeInfo.setMeterielCode(spurtcodeInfo.getMeterielCode());
//                newSpurtcodeInfo.setMeterielDesc(spurtcodeInfo.getMeterielDesc());
//                newSpurtcodeInfo.setFactoryCode(spurtcodeInfo.getFactoryCode());
//                newSpurtcodeInfo.setFactoryName(spurtcodeInfo.getFactoryName());
//                newSpurtcodeInfo.setCurrentCode(currentCode);
//                newSpurtcodeInfo.setCurrentStatus(Constants.CURRENT_STATUS_OK);
//                newSpurtcodeInfo.setCurrentNumber(number);
//                spurtcodeInfoMapper.insertSelective(newSpurtcodeInfo);
//                //修改绑定关系的喷码
//                rfidBindSpurtcodeOrign.setCurrentCode(currentCode);
//                return rfidBindSpurtcodeMapper.updateByPrimaryKeySelective(rfidBindSpurtcodeOrign);
//            }
//        }else {
//            //如果currentCode为空，解绑为空托盘
//            rfidBindSpurtcodeOrign.setCurrentCode(null);
//            rfidBindSpurtcodeMapper.updateByPrimaryKeySelective(rfidBindSpurtcodeOrign);
//            //托盘状态,满托->空托
//            return trayInfoMapper.updateRfidStatus(Constants.RFID_STATUS_EMPTY, rfid);
//        }
//}
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBind(String currentCode, String rfid) {
        RfidBindSpurtcode rfidBindSpurtcodeOrign = rfidBindSpurtcodeMapper.selectByCurrentCodeAndRfid(rfid);
        if (rfidBindSpurtcodeOrign == null) {
            log.error("该绑定记录不存在，不能修改");
            throw new BusinessException("该绑定记录不存在，不能修改");
        }
        //判断要修改的喷码和原来喷码是否一样，一样不做操作，直接返回成功
        if (currentCode.equals(rfidBindSpurtcodeOrign.getCurrentCode())) {
            return 1;
        }
        //判断喷码是否是数据中喷码，更新喷码状态
        SpurtcodeInfo spurtcodeInfoOrign = spurtcodeInfoMapper.selectSpurtcodeByCurrentCode(currentCode);
        //如果喷码存在数据库中
        if (spurtcodeInfoOrign != null) {
            if (spurtcodeInfoOrign.getCurrentStatus().equals(Constants.CURRENT_STATUS_USED)) {
                log.error("要绑定的喷码为喷码机正在使用的喷码，不能绑定");
                throw new BusinessException("要绑定的喷码为喷码机正在使用的喷码，不能绑定");
            } else if (spurtcodeInfoOrign.getCurrentStatus().equals(Constants.CURRENT_STATUS_OK)) {
                log.error("要绑定的喷码已经进行过绑定，不能绑定");
                throw new BusinessException("要绑定的喷码已经进行过绑定，不能绑定");
            } else {
                //更改绑定关系
                rfidBindSpurtcodeOrign.setCurrentCode(currentCode);
                rfidBindSpurtcodeMapper.updateByPrimaryKeySelective(rfidBindSpurtcodeOrign);
                //更改喷码状态 等待绑定->已绑定
                spurtcodeInfoOrign.setCurrentStatus(Constants.CURRENT_STATUS_OK);
                return spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfoOrign);
            }
        } else {
            //修改绑定关系的喷码
            rfidBindSpurtcodeOrign.setCurrentCode(currentCode);
            return rfidBindSpurtcodeMapper.updateByPrimaryKeySelective(rfidBindSpurtcodeOrign);
        }
    }

    /**
     * 查询第一次绑定状态接口
     *
     * @return
     */
    @Override
    public Map<String, Object> selectFirstBind() {
        Map<String, Object> map = new HashMap<>();
        int total = rfidBindSpurtcodeMapper.selectTotalTotal();
        RfidBindSpurtcode rfidBindSpurtcode = rfidBindSpurtcodeMapper.selectNewest();
        map.put("total", total);
        map.put("rfidBindSpurtcode", rfidBindSpurtcode);
        return map;
    }

    /**
     * 报废第一次绑定关系
     *
     * @param remark
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int scrap(String remark, String rfid) {
        int count = rfidBindSpurtcodeMapper.selectByRfid(rfid);
        if (count != 1) {
            log.error("要被作废的绑定关系不存在");
            throw new CustomException("要被作废的绑定关系不存在", HttpStatus.BAD_REQUEST);
        }
        //作废，修改托盘状态，满托->空托
        trayInfoMapper.updateRfidStatus(Constants.RFID_STATUS_EMPTY, rfid);
        //修改第一绑定关系状态
        return rfidBindSpurtcodeMapper.update(remark, rfid);
    }

}
