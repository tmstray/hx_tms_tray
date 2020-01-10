package com.huaxin.cloud.tms.tray.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.enums.BusinessType;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.page.TableDataInfo;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.utils.DateUtils;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.common.utils.poi.ExcelUtil;
import com.huaxin.cloud.tms.tray.dto.Request.ReqTrayInfoDTO;
import com.huaxin.cloud.tms.tray.dto.Response.ResTrayInfoDTO;
import com.huaxin.cloud.tms.tray.entity.TrayInfo;
import com.huaxin.cloud.tms.tray.service.TrayInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 托盘基础信息管理Controller
 *
 * @author huzw
 * @date 2019-10-17
 */
@RestController
@RequestMapping("/trayInfo")
@Api(tags = "托盘基本信息")
@CrossOrigin
public class TrayInfoController extends BaseController
{
    private String message;
    private static final Logger log = LoggerFactory.getLogger(TrayInfoController.class);

    @Autowired
    private TrayInfoService trayInfoService;
    /**
     * 查询托盘明细信息
     */
    @ApiOperation(value = "查询托盘明细信息")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody JSONObject jsonObject)
    {
        try {
            ReqTrayInfoDTO trayInfo =new ReqTrayInfoDTO();
            Integer pageNum = jsonObject.getInteger("pageNum");
            Integer pageSise = jsonObject.getInteger("pageSize");
            trayInfo.setRfidStatus(jsonObject.getInteger("rfidStatus") == 0 ? null : jsonObject.getInteger("rfidStatus"));
            trayInfo.setRfidType(jsonObject.getInteger("rfidType") == 0 ? null : jsonObject.getInteger("rfidType"));
            PageHelper.startPage(pageNum, pageSise);
            List<ResTrayInfoDTO> list = trayInfoService.selectTrayInfoList(trayInfo);
            PageInfo p = new PageInfo<>(list);
            return getDataTable(p.getList());
        }catch (Exception e){
            message = "查询条件有误，请检查";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }


    /**
     *  根据工厂编码返回：对应状态的托盘数量：
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "查询托盘状态数量")
    @PostMapping("/listGroupByStatus")
    public TableDataInfo getListByStatus(@RequestBody JSONObject jsonObject){
        try {
            TrayInfo trayInfo = new TrayInfo();
            Integer pageNum = jsonObject.getInteger("pageNum");
            Integer pageSise = jsonObject.getInteger("pageSize");
            trayInfo.setRfidStatus(jsonObject.getInteger("rfidStatus") == 0 ? null : jsonObject.getInteger("rfidStatus"));
            trayInfo.setRfidType(jsonObject.getInteger("rfidType") == 0 ? null : jsonObject.getInteger("rfidType"));
            PageHelper.startPage(pageNum, pageSise);
            List<ResTrayInfoDTO> list = trayInfoService.selectTrayInfoByStatues(trayInfo);
            PageInfo p = new PageInfo<>(list);
            return getDataTable(p.getList());
        }catch (Exception e){
            message = "查询条件有误，请检查";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }

    /**
     * 新增托盘信息
     *
     * @param trayInfo 新增托盘
     * @return 结果
     */

    @ApiOperation(value = "新增托盘信息")
    @PostMapping("/insetTrayInfo")
    @Log(title = "托盘信息管理 [新增托盘信息]", businessType = BusinessType.INSERT)
    public ResultInfo insertTrayInfo(@RequestBody TrayInfo trayInfo)throws  Exception
    {
        try {
            return toAjax(trayInfoService.insertTrayInfo(trayInfo));
        } catch (Exception e) {
            message = "新增托盘信息失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }


    /**
     * 批量新增托盘
     *
     * @param trayInfo  批量新增托盘
     * @return 结果
     */

    @ApiOperation(value = "批量新增托盘")
    @PostMapping("/batchInsetTrayInfo")
    @Log(title = "托盘信息管理 [批量新增托盘]", businessType = BusinessType.INSERT)
    public ResultInfo batchInsertTrayInfo(@RequestBody List<TrayInfo> trayInfo) throws Exception
    {
//        try {
//            return toAjax(trayInfoService.insertBatch(trayInfo));
//        } catch (Exception e) {
//            message = "批量新增托盘信息失败";
//            log.error(message, e);
//            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
//        }

        String message =trayInfoService.insertBatchTrayInfo(trayInfo);
        return ResultInfo.success(message);

    }
    /**
     * 变更托盘状态
     *
     * @param trayInfo 变更托盘状态
     * @return 结果
     */

    @ApiOperation(value = "修改托盘信息")
    @PostMapping("/updateTrayInfo")
    @Log(title = "托盘信息管理 [修改托盘信息]", businessType = BusinessType.UPDATE)
    public ResultInfo modifyTrayInfoStatus(@RequestBody ReqTrayInfoDTO trayInfo)throws BusinessException
    {
        try {
            return toAjax(trayInfoService.modifyTrayInfo(trayInfo));
        } catch (Exception e) {
            message =e.getMessage();
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }


    /**
     * 删除托盘信息
     * @param id：ID
     * @param updateBy： 删除操作人：
     * @return
     */
    @ApiOperation(value = "删除托盘信息")
    @PostMapping("/deleteTrayInfo")
    @Log(title = "托盘信息管理 [删除托盘信息]", businessType = BusinessType.DELETE)
    public ResultInfo deleteTrayInfo(@RequestParam Integer id,
                                     @RequestParam(required = false) String updateBy)throws BusinessException
    {
        try {
            return toAjax(trayInfoService.deleteTrayInfoById(id,updateBy));
        } catch (Exception e) {
            message = "删除托盘信息失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }

    @ApiOperation(value = "提货单装车更新出厂时间")
    @PostMapping("/updateStatusByWayBillNo")
    @Log(title = "托盘信息管理 [根据提货单更新托盘状态]", businessType = BusinessType.UPDATE)
    public ResultInfo updateStatusByWayBillNo(@RequestParam  String factoryCode,
                                              @RequestParam  String orderNo,
                                              @RequestParam (required = false) Integer rfidStatus,
                                              @RequestParam (required = false) String updateBy)throws BusinessException
    {
        try {
            return toAjax(trayInfoService.updateStatusByWayBillNo(factoryCode,orderNo,rfidStatus,updateBy));
        } catch (Exception e) {
            message = "交货单出厂更新出厂时间失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }

    }

    @ApiOperation(value = "冻结托盘")
    @PostMapping("/freeZeTrayInfo")
    @Log(title = "托盘信息管理 [冻结托盘]", businessType = BusinessType.UPDATE)
    public ResultInfo freeZeTrayInfo(@RequestBody ReqTrayInfoDTO trayInfo)throws BusinessException
    {
        try {
            return toAjax(trayInfoService.freeZeTrayInfo(trayInfo));
        } catch (Exception e) {
            message = "冻结托盘失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }


    @ApiOperation(value = "报废或报损托盘")
    @PostMapping("/scrapOrLossTrayInfo")
    @Log(title = "托盘信息管理 [报废或报损托盘]", businessType = BusinessType.UPDATE)
    public ResultInfo scrapOrLossTrayInfo(@RequestBody ReqTrayInfoDTO trayInfo)throws BusinessException
    {
        try {
            return toAjax(trayInfoService.updateTrayInfoByRfid(trayInfo));
        } catch (Exception e) {
            message = "报废或报损托盘失败";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }


    @ApiOperation(value = "满托清单查询")
    @PostMapping("/fullTrayList")
    public TableDataInfo fullTrayList(@RequestBody JSONObject jsonObject)
    {
        try {
            ReqTrayInfoDTO trayInfo =new ReqTrayInfoDTO();
            Integer pageNum = jsonObject.getInteger("pageNum");
            Integer pageSise = jsonObject.getInteger("pageSize");
//            可以通过托盘ID、喷码、绑定DL交货单和入库时间进行查询
            String rfid =jsonObject.getString("rfid");
            String currentCode=jsonObject.getString("currentCode");
            String orderNo =jsonObject.getString("orderNo");
            String startTime =jsonObject.getString("startTime");
            String endTime =jsonObject.getString("endTime");

            trayInfo.setRfid(rfid);
            trayInfo.setCurrentCode(currentCode);
            trayInfo.setOrderNo(orderNo);
            trayInfo.setRfidStatus(Constants.RFID_STATUS_FULL);
            trayInfo.setStartTime(startTime);
            trayInfo.setEndTime(endTime);
            PageHelper.startPage(pageNum, pageSise);
            List<ResTrayInfoDTO> list = trayInfoService.selectTrayInfoFull(trayInfo);
            PageInfo p = new PageInfo<>(list);
            return getDataTable(p.getList());
        }catch (Exception e){
            message = "满托查询异常，请检查";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }
    @ApiOperation(value = "空托清单查询")
    @PostMapping("/emptyTrayList")
    public TableDataInfo emptyTrayList(@RequestBody JSONObject jsonObject)
    {
        try {
            ReqTrayInfoDTO trayInfo =new ReqTrayInfoDTO();
            Integer pageNum = jsonObject.getInteger("pageNum");
            Integer pageSise = jsonObject.getInteger("pageSize");
            String rfid =jsonObject.getString("rfid");
            Integer rfidHealth =jsonObject.getInteger("rfidHealth");
            String startTime =jsonObject.getString("startTime");
            String endTime =jsonObject.getString("endTime");
            trayInfo.setRfidStatus(Constants.RFID_STATUS_EMPTY);
            trayInfo.setRfid(rfid);
            trayInfo.setRfidHealth(rfidHealth);
            trayInfo.setStartTime(startTime);
            trayInfo.setEndTime(endTime);
            PageHelper.startPage(pageNum, pageSise);
            List<ResTrayInfoDTO> list = trayInfoService.selectTrayInfoList(trayInfo);
            PageInfo p = new PageInfo<>(list);
            return getDataTable(p.getList());
        }catch (Exception e){
            message = "空托查询异常，请检查";
            log.error(message, e);
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }
    }

    @GetMapping("/hello")
    public String hello(String cc){
        System.out.println("下联是："+ DateUtils.getTime() +": 宝塔镇河妖");
        return "hello: Today is " +  DateUtils.getTime() + ": "+ cc;
    }

    @Log(title = "托盘导入管理", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ApiOperation(value = "托盘导入管理")
    public ResultInfo importData(MultipartFile file) throws Exception
    {
        ExcelUtil<TrayInfo> util = new ExcelUtil<TrayInfo>(TrayInfo.class);
        List<TrayInfo> trayInfoList =null;
        try {
            // 执行文件导入：
            trayInfoList = util.importExcel(file.getInputStream());
        }catch (Exception e){
            message="导入的格式文件有误!";
            throw new BusinessException(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : message);
        }

        // 获取导入的数据执行数据库操作：
        String message = trayInfoService.importTrayInfo(trayInfoList);
        return ResultInfo.success(message);
    }

    @GetMapping("/importTemplate")
    @ApiOperation(value = "托盘导入模板管理")
    public ResultInfo importTemplate()
    {
        ExcelUtil<TrayInfo> util = new ExcelUtil<TrayInfo>(TrayInfo.class);
        return util.importTemplateExcel("托盘初始化导入模板");
    }
}

