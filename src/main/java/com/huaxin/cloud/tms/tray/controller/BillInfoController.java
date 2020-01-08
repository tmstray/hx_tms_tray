package com.huaxin.cloud.tms.tray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.service.BillInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @Description: 磁卡-订单
 * @author Administrator
 * @date: 2020年1月7日下午4:21:04
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
     * 
     * @Description: 根据磁卡号查询交货单
     * @author Administrator
     * @date: 2020年1月7日下午4:27:24
     * @param card
     * @return
     */
    @ApiOperation(value = "根据磁卡号查询交货单")
    @GetMapping(value = "/{card}")
    public ResultInfo getCardBillInfo(@PathVariable String card)
    {
        return ResultInfo.success(billInfoService.selectCardBillInfoByCard(card));
    }


    /**
     * 根据lCard 磁卡号查询交货单
     */
//    @GetMapping(value = "/{lCard}")
//    public ResultInfo getInfo(@PathVariable String lCard)
//    {
//        return ResultInfo.success(billInfoService.selectBillInfoByLCard(lCard));
//    }
    /**
     * 查询交货单列表
     */
//    @PostMapping("/list")
//    public TableDataInfo list(BillInfo billInfo)
//    {
//        startPage();
//        List<BillInfo> list = billInfoService.selectBillInfoList(billInfo);
//        return getDataTable(list);
//    }


}

