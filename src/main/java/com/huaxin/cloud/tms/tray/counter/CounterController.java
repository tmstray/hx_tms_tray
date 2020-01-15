package com.huaxin.cloud.tms.tray.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;

/**
 * @author fuwenhao
 */
@RestController
@RequestMapping("/transport")
public class CounterController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CounterService transportService;

    private static Boolean initFlag = true;

    @GetMapping("/start")
    public ResultInfo start(String zId) {
        //开始计数时，提供一个装车道号zId，存入redis,给生成喷码时使用
        stringRedisTemplate.opsForValue().set("zId",zId);
        if (initFlag) {
            //flag为true,开始计数，并把flag置为false
            transportService.startCountNum();
            initFlag = false;
            return ResultInfo.success("开始计数成功");
        }
        return ResultInfo.success("计数已被启动，不需要重复启动");
    }

    @GetMapping("/stop")
    public ResultInfo stop() {
            if(!initFlag){
                transportService.endCountNum();
                initFlag = true;
                return ResultInfo.success("结束计数成功");
            }
        return ResultInfo.success("计数器未启动，不需要结束计数");
    }

    /**
     * 返回boolean，告诉前端，计数器按钮状态，true,为计数器正在计数，false为计数停止计数
     * @return
     */
    @GetMapping("/getStatus")
    public ResultInfo getCurrentPrintStatus(){
            return ResultInfo.success(!initFlag);
    }


}

