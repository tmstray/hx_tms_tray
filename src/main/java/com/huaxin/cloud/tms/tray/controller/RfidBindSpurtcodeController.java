package com.huaxin.cloud.tms.tray.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.enums.BusinessType;
import com.huaxin.cloud.tms.tray.common.page.TableDataInfo;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.dto.Request.ReqScrapDTO;
import com.huaxin.cloud.tms.tray.dto.Request.ReqUpdateBindDTO;
import com.huaxin.cloud.tms.tray.dto.Request.SpurtCodeBindRfidRequestDTO;
import com.huaxin.cloud.tms.tray.service.RfidBindSpurtcodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * RFID绑定喷码信息Controller
 */
@Api(tags = "第一次绑定管理模块")
@RestController
@RequestMapping("/first_bind")
@CrossOrigin
public class RfidBindSpurtcodeController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(RfidBindSpurtcodeController.class);


    @Autowired
    RfidBindSpurtcodeService rfidBindSpurtcodeService;

    /**
     * rfid默认是rfid识别天线识别到rfid后，开始进行绑定
     * 现在不清楚，硬件怎么交互，默认是可以调用接口
     * @param rfid
     * @return
     */
    @Log(title = "托盘rfid和喷码绑定", businessType = BusinessType.INSERT)
    @ApiOperation(value = "托盘rfid和喷码绑定")
    @ApiImplicitParam(name = "rfid", value = "托盘rfid")
    @PostMapping()
    @ResponseBody
    public ResultInfo spurtcodeInfoBindRfid(String rfid) {
        //硬件设备怎么交互不清楚，待确认
        //// TODO: 2019/10/25
         return toAjax(rfidBindSpurtcodeService.insertRfidBindSpurtcode(rfid));
    }

    @ApiOperation(value = "第一次绑定清单")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo spurtcodeInfoBindRfidList(@RequestBody SpurtCodeBindRfidRequestDTO spurtCodeBindRfidRequestDTO) {
        String startTimeStr = spurtCodeBindRfidRequestDTO.getStartTime()+" 00:00:00";
        String endTimeStr = spurtCodeBindRfidRequestDTO.getEndTime()+" 23:59:59";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime=null;
        Date endTime = null;
        try {
            startTime = format.parse(startTimeStr);
            endTime = format.parse(endTimeStr);
        } catch (ParseException e) {
            log.error("开始时间或者结束时间格式化错误");
        }
        PageHelper.startPage(spurtCodeBindRfidRequestDTO.getPageNum(),spurtCodeBindRfidRequestDTO.getPageSize());
        List<Map<String ,Object>> list = rfidBindSpurtcodeService.selectRfidBindSpurtcodeList(startTime,endTime);
        PageInfo p = new PageInfo<>(list);
        return getDataTable(p.getList());
    }

    @ApiOperation(value = "查询一次绑定状态接口")
    @GetMapping()
    @ResponseBody
    public ResultInfo selectFirstBind() {
        Map<String,Object> map= rfidBindSpurtcodeService.selectFirstBind();
        return ResultInfo.success(map);
    }

    @Log(title = "托盘rfid和喷码绑定", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "报废一次绑定关系接口")
    @PutMapping("/scrap")
    @ResponseBody
    public ResultInfo scrap(@RequestBody @Valid ReqScrapDTO reqScrapDTO) {
        return toAjax(rfidBindSpurtcodeService.scrap(reqScrapDTO.getRemark(),reqScrapDTO.getRfid()));
    }

    @Log(title = "修改第一次绑定关系", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改第一次绑定关系")
    @PutMapping()
    @ResponseBody
    public ResultInfo updateBind(@RequestBody @Valid ReqUpdateBindDTO reqUpdateBindDTO) {
        return toAjax(rfidBindSpurtcodeService.updateBind(reqUpdateBindDTO.getCurrentCode(),reqUpdateBindDTO.getRfid()));
    }
    
}
