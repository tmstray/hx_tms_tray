package com.huaxin.cloud.tms.tray.controller;

import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.enums.BusinessType;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.exception.CustomException;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.scheduled.SpurtCodeTask;
import com.huaxin.cloud.tms.tray.dto.Request.*;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import com.huaxin.cloud.tms.tray.service.SpurtcodeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 喷码信息Controller
 */
@Api(tags = "喷码管理模块")
@Validated
@RestController
@CrossOrigin
@RequestMapping("/spurtcodes")
public class SpurtcodeInfoController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(SpurtcodeInfoController.class);

    @Resource(name = "scheduledExecutorService")
    private ScheduledExecutorService scheduledExecutorService;

    private ScheduledFuture future1;

    private static Boolean flag = true;

    @Autowired
    private SpurtcodeInfoService spurtcodeInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spurtcode.number}")
    private int number;

    @ApiOperation(value = "获取当前喷码信息")
    @GetMapping("/current-spurtcode")
    public ResultInfo currentSpurtcode() {
        SpurtcodeInfo spurtcodeInfo = spurtcodeInfoService.selectCurrentSpurtcode();
        return ResultInfo.success(spurtcodeInfo);
    }

    /**
     * 查询所有生产线接口
     */
    @ApiOperation(value = "查询所有生产线接口")
    @GetMapping("/ztlines/list")
    public ResultInfo selectZtlinesList() {
        List<Map<String, Object>> list = spurtcodeInfoService.selectZtlinesList();
        return ResultInfo.success(list);
    }

    /**
     * 修改当前喷码
     */
    @Log(title = "修改当前喷码", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改当前喷码")
    @ApiImplicitParam(name = "currentSpurtcode", value = "喷码")
    @PutMapping("/current-spurtcode")
    public ResultInfo updateCurrentSpurtcode(
            @RequestBody @Valid ReqUpdateCurrentSpurtcode reqUpdateCurrentSpurtcode) {
        return toAjax(spurtcodeInfoService.updateCurrentSpurtcode(reqUpdateCurrentSpurtcode.getCurrentSpurtcode()));
    }

    /**
     * 修改当前已喷水泥包数
     */
    @Log(title = "修改当前已喷水泥包数", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改当前已喷水泥包数")
    @PutMapping("/current-number")
    public ResultInfo updateCurrentNumber(
            @RequestBody ReqUpdateCurrentNumber reqUpdateCurrentNumber) {
        int currentNumber = reqUpdateCurrentNumber.getCurrentNumber();
        if (currentNumber < 0 || currentNumber > number) {
            log.error("currentNumber:{},水泥包数取值超出范围", currentNumber);
            throw new CustomException("水泥包数取值超出范围");
        }
        return toAjax(spurtcodeInfoService.updateCurrentNumber(currentNumber));
    }

    @Log(title = "开启喷码计数刷新定时任务", businessType = BusinessType.OTHER)
    @ApiOperation(value = "开启喷码计数刷新定时任务")
    @PutMapping("/start")
    public ResultInfo startCron1(@RequestBody ReqStartDTO reqStartDTO) {
        //保证线程只启动一次
        if (flag) {
            SpurtCodeTask spurtCodeTask = new SpurtCodeTask();
            Map<String, Object> map = spurtcodeInfoService.selectOutNum(reqStartDTO.getzId());
            if (map == null) {
                log.error("result:{},生产编号表数据错误", map);
                throw new BusinessException("生产编号表数据错误");
            }
            spurtCodeTask.setMap(map);
            future1 = scheduledExecutorService.scheduleAtFixedRate(spurtCodeTask, 0,
                    1000, TimeUnit.MILLISECONDS);
            flag = false;
            log.info("定时任务开启");
            return ResultInfo.success("定时任务启动成功");
        }
        return ResultInfo.success("定时任务已经启动，不需要再启动");
    }

    @Log(title = "关闭喷码计数刷新定时任务", businessType = BusinessType.OTHER)
    @PutMapping("/stop")
    @ApiOperation("关闭喷码计数刷新定时任务")
    public ResultInfo stopCron1() {
        if (future1 != null) {
            future1.cancel(true);
            flag = true;
        }
        log.info("定时任务关闭");
        return ResultInfo.success("定时任务关闭");
    }

    @ApiOperation("查询喷码规则")
    @ApiImplicitParam(name = "zId", value = "生产线id")
    @GetMapping("/rule")
    public ResultInfo selectRule(@RequestParam String zId) {
        Map<String, Object> map = spurtcodeInfoService.selectOutNum(zId);
        if (map == null) {
            log.error("result:{},生产编号表数据错误", map);
            throw new BusinessException("生产编号表数据错误");
        }
        String rule = spurtcodeInfoService.selectRule(map);
        return ResultInfo.success("操作成功", rule);
    }

    /**
     * 喷码生成规则：日期+出厂编号后四位+9+7位流水号，例子：20191025000390000001
     * 目前前端只能修改
     *
     * @param
     * @return
     */
    @Log(title = "修改喷码规则", businessType = BusinessType.UPDATE)
    @ApiOperation("修改喷码规则")
    @PutMapping("/rule")
    public ResultInfo updateRule( @RequestBody @Valid ReqUpdateRuleDTO reqUpdateRuleDTO) {
        Map<String, Object> map = spurtcodeInfoService.selectOutNum(reqUpdateRuleDTO.getzId());
        if (map == null) {
            log.error("result:{},生产编号表数据错误", map);
            throw new BusinessException("生产编号表数据错误");
        }
        return ResultInfo.success("修改成功", spurtcodeInfoService.updateRule(reqUpdateRuleDTO.getPrefixRule(), map));
    }


    /**
     * 调用喷码机和计数器
     * @param
     * @return
     */
    @ApiOperation("调用喷码机计数器")
    @PostMapping("/getCurrentCode")
//    public void getCurrentCode(@RequestBody ReqCurrentCodeDTO codeDTO) {
    public void getCurrentCode(Integer number) {
//        Integer currentNumber =codeDTO.getNumber();
        String zId = stringRedisTemplate.opsForValue().get("zId");
        Map<String, Object> outNumMap = spurtcodeInfoService.selectOutNum(zId);
        log.info("1：我被调用了，开始调用喷码机" );
        spurtcodeInfoService.getCounter(number,outNumMap);
        log.info("2：我被调用了，完成喷码机调用" );
    }
}
