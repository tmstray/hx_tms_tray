package com.huaxin.cloud.tms.tray.counter;

import org.springframework.stereotype.Service;


@Service
public class CounterServiceImpl implements CounterService {
    private final String counterDoOut = ConfigUtil.getProperties("counter.controller.doOut");;
    private final String counterIp = ConfigUtil.getProperties("counter.controller.ip");;

    @Override
    public void startCountNum() {
        //计数器

        //设置状态为1，计数器开始计数
        MonitorApplicationRunner.counterMap.put(counterIp + counterDoOut + "status", 1);
        MonitorApplicationRunner.counterMap.put(counterIp + counterDoOut + "num", 0);

    }

    public void endCountNum(){
        MonitorApplicationRunner.counterMap.put(counterIp + counterDoOut + "status", 0);
        MonitorApplicationRunner.counterMap.put(counterIp + counterDoOut + "num", 0);
    }
}
