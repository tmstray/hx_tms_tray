package com.huaxin.cloud.tms.tray.controller;


import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.page.TableDataInfo;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.entity.BillInfo;
import com.huaxin.cloud.tms.tray.service.BillInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交货单Controller
 */
@Api(tags = "交货单")
@RestController
@RequestMapping("/bill")
@CrossOrigin
public class BillInfoController extends BaseController
{

    @Autowired
    private BillInfoService billInfoService;


    /**
     * 根据lCard 磁卡号查询交货单
     */
    @ApiOperation(value = "根据lCard 磁卡号查询交货单")
    @GetMapping(value = "/{lCard}")
    public ResultInfo getInfo(@PathVariable String lCard)
    {
        return ResultInfo.success(billInfoService.selectBillInfoByLCard(lCard));
    }
    /**
     * 查询交货单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BillInfo billInfo)
    {
        startPage();
        List<BillInfo> list = billInfoService.selectBillInfoList(billInfo);
        return getDataTable(list);
    }


}

