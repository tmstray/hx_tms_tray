package com.huaxin.cloud.tms.tray.common.scheduled;

import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.utils.ApplicationContextHolder;
import com.huaxin.cloud.tms.tray.dao.SpurtcodeInfoMapper;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import com.huaxin.cloud.tms.tray.service.SpurtcodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description 生成喷码，与喷码机交互的定时任务
 * @Author lixin
 * @Date 2019/11/29 9:37
 * @Version 1.0
 **/
@Transactional(rollbackFor = Exception.class)
public class SpurtCodeTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SpurtCodeTask.class);

    private Map map;

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void run() {
        log.info("当前已喷码包数定时更新任务开始执行");
        //1.获取当前包数，目前不确定怎么获取，先定一个1~40的随机数
//        Random random = new Random();
//        int currentNumber = random.nextInt(40) + 1;
        StringRedisTemplate stringRedisTemplate =(StringRedisTemplate)ApplicationContextHolder.getBeanByName("stringRedisTemplate");
        Boolean flag = stringRedisTemplate.hasKey("number");
        if (!flag) {
            //自增流水号，redis流水号每天晚上12点失效
            Calendar cal = Calendar.getInstance();
            long startTmie = cal.getTime().getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1, 0, 0, 0);
            long endTime = cal.getTime().getTime();
            stringRedisTemplate.opsForValue().set("number", "1", endTime - startTmie, TimeUnit.MILLISECONDS);
        }
        int number = Integer.parseInt(stringRedisTemplate.opsForValue().get("number"));
        int currentNumber = number%40;
        //2.更新数据库
        SpurtcodeInfoMapper spurtcodeInfoMapper = (SpurtcodeInfoMapper) ApplicationContextHolder.getBeanByName("spurtcodeInfoMapper");
        SpurtcodeInfo spurtcodeInfo = spurtcodeInfoMapper.selectCurrentSpurtcode();
        SpurtcodeInfoService spurtcodeInfoService = (SpurtcodeInfoService) ApplicationContextHolder.getBeanByName("spurtcodeInfoServiceImpl");
        //更新成功，当前有正在喷的喷码，正常修改当前包数和喷码
        if (spurtcodeInfo != null) {
            //3.如果当前包数为四十，更新当前喷码状态为1，并生成新的喷码，插入数据库，并传输给喷码机
            if (currentNumber == 0) {
                log.info("当前包数为40，重新生成喷码");
                spurtcodeInfo.setCurrentNumber(40);
                spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfo);
                //修改当前喷码状态，当前使用喷码->等待绑定喷码
                spurtcodeInfoMapper.updateCurrentStatus();
                //插入新的喷码数据
                spurtcodeInfoService.beginGenerateSpurtcode(map);
            }else {
                spurtcodeInfo.setCurrentNumber(currentNumber);
                spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfo);
            }
         //更新失败，没有正在喷的喷码，生成一个新喷码，给喷码机使用
        } else {
            spurtcodeInfoService.beginGenerateSpurtcode(map);
        }
        //数据库操作成功时自增流水号,保证mySql和redis一致性
            stringRedisTemplate.opsForValue().increment("number");
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info("当前已喷码包数定时更新任务结束执行");
    }
}
