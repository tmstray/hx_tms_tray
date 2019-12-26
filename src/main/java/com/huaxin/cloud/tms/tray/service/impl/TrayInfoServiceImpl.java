package com.huaxin.cloud.tms.tray.service.impl;

import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.utils.DateUtils;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.dao.RfidBindSpurtcodeMapper;
import com.huaxin.cloud.tms.tray.dao.TrayInfoMapper;
import com.huaxin.cloud.tms.tray.dto.Request.ReqTrayInfoDTO;
import com.huaxin.cloud.tms.tray.dto.Request.RfidBindOrderAndDetailDTO;
import com.huaxin.cloud.tms.tray.dto.Response.ResTrayInfoDTO;
import com.huaxin.cloud.tms.tray.entity.RfidBindOrderDetail;
import com.huaxin.cloud.tms.tray.entity.TrayInfo;
import com.huaxin.cloud.tms.tray.service.RfidBindOrderService;
import com.huaxin.cloud.tms.tray.service.RfidBindSpurtcodeService;
import com.huaxin.cloud.tms.tray.service.TrayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 托盘基础信息管理Service业务层处理
 * 
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TrayInfoServiceImpl implements TrayInfoService
{

    private static final Logger logger = LoggerFactory.getLogger(TrayInfoServiceImpl.class);
    @Autowired
    private TrayInfoMapper trayInfoMapper;

    @Autowired
    private RfidBindSpurtcodeMapper rfidBindSpurtcodeMapper;

    @Autowired
    RfidBindSpurtcodeService rfidBindSpurtcodeService;

    @Autowired
    private RfidBindOrderService rfidBindOrderService;

    @Value("${spurtcode.factory-code}")
    private String factoryCode;
    @Value("${spurtcode.factory-name}")
    private String factoryName;
    /**
     * 查询托盘基础信息管理列表
     * 
     * @param trayInfo 托盘基础信息管理
     * @return 托盘基础信息管理
     */
    @Override
    public List<ResTrayInfoDTO> selectTrayInfoList(ReqTrayInfoDTO trayInfo)
    {
        return trayInfoMapper.selectTrayInfoList(trayInfo);
    }

    /**
     * 满托数据查询
     * @param trayInfo 满托查询
     * @return
     */
    @Override
    public List<ResTrayInfoDTO> selectTrayInfoFull(ReqTrayInfoDTO trayInfo)
    {
        return trayInfoMapper.selectTrayInfoFull(trayInfo);
    }

    @Override
    public List<ResTrayInfoDTO> selectTrayInfoByStatues(TrayInfo trayInfo) {
        ReqTrayInfoDTO infoDTO= new ReqTrayInfoDTO();
        BeanUtils.copyProperties(trayInfo,infoDTO);
        return trayInfoMapper.selectListGroupbyStatus(infoDTO);
    }

    /**
     * 新增托盘基础信息管理
     * 
     * @param trayInfo 托盘基础信息管理
     * @return 结果
     */
    @Override
    public int insertTrayInfo(TrayInfo trayInfo) throws  Exception
    {
        String rfid= trayInfo.getRfid();
        rfidBindSpurtcodeMapper.updateIsHavaing(rfid); // 设置托盘与水泥码绑定使用状态

        ResTrayInfoDTO  resTrayInfoDTO= trayInfoMapper.getTrayInfoByRfid(rfid,factoryCode);
        if(resTrayInfoDTO!=null){
           int rfidStatus =resTrayInfoDTO.getRfidStatus();
           if(rfidStatus==Constants.RFID_STATUS_FULL|| rfidStatus==Constants.RFID_STATUS_ONWAY ||rfidStatus ==Constants.RFID_STATUS_NOTLOCAL){
               TrayInfo infoEntity =new TrayInfo();
               BeanUtils.copyProperties(resTrayInfoDTO,infoEntity);
               infoEntity.setInitTime(DateUtils.getNowDate());
               infoEntity.setRfidStatus(Constants.RFID_STATUS_EMPTY); // 空托盘
               infoEntity.setUpdateBy("update");
               infoEntity.setBindingTime(null);
               infoEntity.setUpdateTime(DateUtils.getNowDate());
               infoEntity.setReturnTime(DateUtils.getNowDate());
               int t = trayInfoMapper.updateByRfid(infoEntity);
               return t;
           }else {
               logger.info("空状态托盘不能重复入库");
//               throw new CustomException("空状态托盘不能重复入库");
               throw new BusinessException("空托盘重复初始化");
           }
        }else {
            trayInfo.setCreateTime(DateUtils.getNowDate());
            trayInfo.setRfidStatus(Constants.RFID_STATUS_EMPTY);
            trayInfo.setRfidType(Constants.RFID_TYPE_WOOD);
            trayInfo.setCreateBy("Test001");
            trayInfo.setVersion("1.0");
            trayInfo.setInitTime(DateUtils.getNowDate());
            trayInfo.setFactoryName(factoryName);
            trayInfo.setFactoryCode(factoryCode);
            int t = trayInfoMapper.insertSelective(trayInfo);
           return t;
        }

    }

    @Override
    public int insertBatch(List<TrayInfo> trayInfoList) throws  Exception{
        int t=0;
        for(TrayInfo trayInfo: trayInfoList){
             t= insertTrayInfo(trayInfo);
        }
        return t;
    }

    /**
     * 修改托盘基础信息管理
     * 
     * @param reqTrayInfo 托盘基础信息管理
     * @return 结果
     */
    @Override
    @Transactional
    public int modifyTrayInfo(ReqTrayInfoDTO reqTrayInfo) throws  Exception {
        int result =0;
        String orderNo=reqTrayInfo.getOrderNo();
        String currentCode =reqTrayInfo.getCurrentCode();
        String rfid =reqTrayInfo.getRfid();

        String oldCode =reqTrayInfo.getOldCode();
        String oldOrderNo =reqTrayInfo.getOldOrderNo();

        if(StringUtils.isEmpty(oldCode)
                && StringUtils.isEmpty(currentCode)
                && StringUtils.isEmpty(oldOrderNo)
                && StringUtils.isEmpty(orderNo)
        ){
            throw new BusinessException("未做任何操作不能保存！");
        }

        if(StringUtils.isEmpty(currentCode) && StringUtils.isNotEmpty(orderNo)){
            throw new BusinessException("第一次绑定关系不存在,不能做第二次绑定！");
        }

        // 第一次绑定处理：
            result= rfidBindSpurtcodeService.updateBind(currentCode, rfid);

//        // 第二次绑定处理： 旧的交货单号不为空，且新的交货单号也不为空，且新旧交货单号不一样的：先解绑后新增绑定：
        if(StringUtils.isNotEmpty(orderNo) || StringUtils.isNotEmpty(oldOrderNo)) {
            RfidBindOrderAndDetailDTO dto = new RfidBindOrderAndDetailDTO();
            RfidBindOrderDetail detail = new RfidBindOrderDetail();
            List<RfidBindOrderDetail> rfidBindOrderDetails = new ArrayList<>();
            // 设置明细表rfid 和 code (喷码)
            detail.setRfid(rfid);
            detail.setCurrentCode(currentCode);

            // 设置主表信息：
            dto.setRfid(rfid);
            dto.setOrderNo(orderNo);
            dto.setOldOrderNo(oldOrderNo);
            dto.setCurrentCode(currentCode);
            dto.setOldCurrentCode(oldCode);
            dto.setFactoryCode(factoryCode);
            dto.setFactoryName(factoryName);
            dto.setMeterielCode(reqTrayInfo.getMeterielCode());
            dto.setMeterielDesc(reqTrayInfo.getMeterielDesc());

            rfidBindOrderDetails.add(detail);
            dto.setRfidBindOrderDetails(rfidBindOrderDetails);
            rfidBindOrderService.SecondBind(dto);

        }
        // 修改绑定关系时：需要处理托盘状态为 装车
        if(StringUtils.isNotEmpty(orderNo) || StringUtils.isNotEmpty(oldOrderNo)) {
            TrayInfo trayInfoVo = new TrayInfo();
            trayInfoVo.setRfid(rfid);
            trayInfoVo.setUpdateBy("addOrderNo");
            trayInfoVo.setUpdateTime(new Date());
            trayInfoVo.setRfidStatus(Constants.RFID_STATUS_LOADEDCAR);// 已装车
            trayInfoVo.setBindingTime(reqTrayInfo.getBindingTime());
            if(StringUtils.isEmpty(orderNo)){
                trayInfoVo.setRfidStatus(Constants.RFID_STATUS_FULL);//满拖
            }
            result=trayInfoMapper.updateByRfid(trayInfoVo);
        }
        return result;
    }


    /**
     * 删除托盘基础信息管理对象
     * 根据id 批量删除
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTrayInfoByIds(String ids) throws  Exception
    {
        String strId[] = ids.split(",");
        int t=0;
        for (int i =0;i<strId.length;i++){
           t =deleteTrayInfoById(Integer.parseInt(strId[i]),"batchDelete");
        }
        return t;
    }

    /**
     * 删除托盘基础信息管理信息
     * 
     * @param id 托盘基础信息管理ID
     * @return 结果
     */
    @Override
    public int  deleteTrayInfoById(Integer id,String updateBy) throws  Exception
    {
       return trayInfoMapper.deleteByPrimaryKey(id,"delSys");
    }

    @Override
    public int updateStatusByWayBillNo(String factoryCode1, String orderNo, Integer rfidStatus,String updateBy)throws  Exception {
        return trayInfoMapper.updateStatusByWayBillNo(factoryCode,orderNo,rfidStatus,updateBy);
    }

    @Override
    public int freeZeTrayInfo(ReqTrayInfoDTO reqTrayInfo) throws  Exception{
        TrayInfo trayInfo =new TrayInfo();
        trayInfo.setRfid(reqTrayInfo.getRfid());
        trayInfo.setFactoryCode(factoryCode);
        trayInfo.setRfidHealth(Constants.RFID_HEALTH_FROZEN); // 冻结托盘 修改托盘健康状态
        trayInfo.setUpdateTime(DateUtils.getNowDate());
        trayInfo.setUpdateBy("freeZe");
        return trayInfoMapper.updateByRfid(trayInfo);
    }


    @Override
    public int updateTrayInfoByRfid(ReqTrayInfoDTO reqTrayInfo) throws  Exception{
        TrayInfo trayInfo =new TrayInfo();
        trayInfo.setRfid(reqTrayInfo.getRfid());
        trayInfo.setRfidHealth(reqTrayInfo.getRfidHealth()); // 修改托盘健状态  报废或报损
        trayInfo.setUpdateTime(DateUtils.getNowDate());
        trayInfo.setRemarks(reqTrayInfo.getRemarks());
        trayInfo.setDamagedReason(reqTrayInfo.getDamagedReason());
        trayInfo.setScrappedReason(reqTrayInfo.getScrappedReason());
        trayInfo.setUpdateBy("scrapOrLoss");
        return trayInfoMapper.updateByRfid(trayInfo);
    }
    
    /**
     * 导入托盘数据
     * 
     * @param trayInfoList 托盘数据列表
     * @return 结果
     */
    @Override
    public String importTrayInfo(List<TrayInfo> trayInfoList){
        if (StringUtils.isNull(trayInfoList) || trayInfoList.size() == 0)
        {
            throw new BusinessException("导入托盘数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        String message="";
        int sum=0;
        StringBuilder successMsg = new StringBuilder();
        for (TrayInfo trayInfo : trayInfoList)
        {
            sum++;
            try
            {
                    trayInfo.setFactoryCode(factoryCode);
                    this.insertTrayInfo(trayInfo);
                    successNum++;
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + " 导入失败："+e.getMessage();
                logger.error(msg, e);
                message=e.getMessage();
            }
        }
        if (failureNum > 0 && successNum>0)
        {
            successMsg.insert(0, "提示：共导入："+sum+"条记录，其中(重复初始化)失败：" + failureNum + "条;   成功:" + successNum + " 条!");
            throw new BusinessException(successMsg.toString());
        }
        else if(failureNum > 0)
        {
            message= StringUtils.isNotEmpty(message)?message :"格式错误";
            successMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + "条,"+message +"!");
        }else{
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条!");
        }
        return successMsg.toString();
    }
}
