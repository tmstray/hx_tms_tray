package com.huaxin.cloud.tms.tray.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.huaxin.cloud.tms.tray.counter.ConfigUtil;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.printer.*;
import com.huaxin.cloud.tms.tray.printer.config.ParamConfigTcp;
import com.huaxin.cloud.tms.tray.printer.exception.TcpException;
import com.huaxin.cloud.tms.tray.printer.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.huaxin.cloud.tms.tray.common.annotation.DataSource;
import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.constant.HttpStatus;
import com.huaxin.cloud.tms.tray.common.enums.DataSourceType;
import com.huaxin.cloud.tms.tray.common.exception.BusinessException;
import com.huaxin.cloud.tms.tray.common.exception.CustomException;
import com.huaxin.cloud.tms.tray.dao.SpurtcodeInfoMapper;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import com.huaxin.cloud.tms.tray.service.SpurtcodeInfoService;

/**
 * 喷码信息Service业务层处理
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpurtcodeInfoServiceImpl implements SpurtcodeInfoService {

    private static final Logger log = LoggerFactory.getLogger(SpurtcodeInfoServiceImpl.class);

    @Autowired
    private SpurtcodeInfoMapper spurtcodeInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DWPrintTest dwPrintTest;

    @Autowired
    private SpurtcodeInfoService spurtcodeInfoService;

    /**
     * @return 喷码机信息对象
     * @Author lixin
     * @Description 获得当前喷码信息, 允许查到当前喷码为空的情况
     * @Date 9:54 2019/10/21
     * @Param
     **/
    @Override
    public SpurtcodeInfo selectCurrentSpurtcode() {
        return spurtcodeInfoMapper.selectCurrentSpurtcode();
    }

    /**
     * 根据规则生成喷码，插入数据库，并传给喷码机，开始喷码
     *
     * @param
     * @return
     */
    public String beginGenerateSpurtcode(Map<String, Object> map) {
        int serialNumber = 1;
        String currentCode="";
        Boolean flag = stringRedisTemplate.hasKey("serialNumber");
        if (!flag) {
            //自增流水号，redis流水号每天晚上12点失效
            Calendar cal = Calendar.getInstance();
            long startTmie = cal.getTime().getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1, 0, 0, 0);
            long endTime = cal.getTime().getTime();
            stringRedisTemplate.opsForValue().set("serialNumber", "1", endTime - startTmie, TimeUnit.MILLISECONDS);
            currentCode= beginSpurtcode(serialNumber, map);
        } else {
            serialNumber = Integer.valueOf(stringRedisTemplate.opsForValue().get("serialNumber"));
            currentCode= beginSpurtcode(serialNumber, map);
        }
        //数据库操作成功时自增流水号,保证mySql和redis一致性
        try {
            stringRedisTemplate.opsForValue().increment("serialNumber");
        } catch (Exception e) {
            log.error("redis操作流水号失败", e);
            //redis操作失败时回滚数据库操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException("redis操作流水号失败");
        }
        return currentCode;
    }

    private String  beginSpurtcode(int serialNumber, Map<String, Object> map) {
        //获取工厂信息
        SpurtcodeInfo spurtcodeInfo = new SpurtcodeInfo();
        spurtcodeInfo.setMeterielCode((String) map.get(Constants.N_STOCK));
        spurtcodeInfo.setMeterielDesc((String) map.get(Constants.N_NAME));
        spurtcodeInfo.setFactoryCode((String) map.get(Constants.F_ID));
        spurtcodeInfo.setFactoryName((String) map.get(Constants.F_NAME));
        //1.喷码生成规则：日期+出厂编号后四位+9+7位流水号，例子：20191025000390000001
        //通过redis拿到喷码规则,生成喷码
        String rule = stringRedisTemplate.opsForValue().get(Constants.RULE);
        if (rule == null) {
            //设置默认喷码规则
            rule = setDefaultRule(Constants.PREFIX, map);
        }
        int length = 18 - rule.length() - String.valueOf(serialNumber).length();
        StringBuilder serialNumberStr = new StringBuilder(rule);
        for (int i = 0; i < length; i++) {
            serialNumberStr.append("0");
        }
        serialNumberStr.append(serialNumber);
        String currentCode = serialNumberStr.toString();
        //查询当前生成的喷码，数据库里是否存在，存在，跳过，不存在生成数据插入
        SpurtcodeInfo spurtcodeInfoOrign = spurtcodeInfoMapper.selectSpurtcodeByCurrentCode(currentCode);
        if (spurtcodeInfoOrign != null) {
            stringRedisTemplate.opsForValue().increment("serialNumber");
            return null;
        }
        //2.传给喷码机，目前不清楚喷码机是否能交互，需要确认是否成功，或者默认成功
        // TODO: 2019/10/23
        //调用喷码机
        String message="喷码成功！";
        try {
            log.info("开始调用喷码机，(修改喷码时：) 当前喷码:{}",currentCode); ;
            tcpPrintCurrentCode(currentCode);
        }catch (TcpException e1) {
            message=e1.getMessage();
            e1.printStackTrace();
           log.error("修改当前喷码1：" +message); ;
        } catch (IOException e2) {
            message=e2.getMessage();
            e2.printStackTrace();
            log.error("修改当前喷码2：" +message); ;
        }

        //3.第二步成功反馈或默认成功情况下，将喷码信息插入数据库
        spurtcodeInfo.setCurrentCode(currentCode);
        spurtcodeInfo.setCurrentNumber(1);
        spurtcodeInfo.setVersion(Constants.VERSION);
        spurtcodeInfoMapper.insertSelective(spurtcodeInfo);
        return  currentCode;
    }

    /**
     * 查询所有生产线接口
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Map<String, Object>> selectZtlinesList() {
        return spurtcodeInfoMapper.selectZtlinesList();
    }

    /**
     * 修改当前喷码，马上生效
     */
    @Override
    public int updateCurrentSpurtcode(String currentSpurtcode) {
        int count = spurtcodeInfoMapper.selectRepetitive(currentSpurtcode);
        String message="";
        if (count == 1) {
            log.error("喷码重复");
            throw new CustomException("喷码重复", HttpStatus.BAD_REQUEST);
        }
        try {
            System.out.println("开始调用喷码机，当前喷码:==="+ currentSpurtcode) ;
            tcpPrintCurrentCode(currentSpurtcode);
            System.out.println("开始调用喷码机，当前喷码:==="+ currentSpurtcode) ;
        }catch (TcpException e1) {
            message=e1.getMessage();
            e1.printStackTrace();
            System.out.println("调用喷码机异常1：" +currentSpurtcode) ;
        } catch (IOException e2) {
            message=e2.getMessage();
            e2.printStackTrace();
            System.out.println("调用喷码机异常2：" +message) ;
        }
        return spurtcodeInfoMapper.updateCurrentSpurtcode(currentSpurtcode);
    }

    /**
     * 修改当前已喷水泥包数
     *
     * @param currentNumber 新包数
     * @return
     */
    @Override
    public int updateCurrentNumber(int currentNumber) {
        return spurtcodeInfoMapper.updateCurrentNumber(currentNumber);
    }

    /**
     * 查询喷码规则
     *
     * @return
     */
    @Override
    public String selectRule(Map<String, Object> map) {
        String rule = stringRedisTemplate.opsForValue().get(Constants.RULE);
        if (rule == null) {
            //设置默认喷码规则
            rule = setDefaultRule(Constants.PREFIX, map);
            return rule;
        }
        return rule;
    }

    /**
     * 设置喷码规则
     *
     * @return
     */
    private String setDefaultRule(String prefixRule, Map<String, Object> map) {
        String rule;//获取前缀6位日期字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(new Date()).substring(2);
        //获取出厂编码后四位
        String outNum = (String) map.get(Constants.N_OUTNUM);
        int length = outNum.length();
        String outNumString = outNum.substring(length - 4);
        rule = dateString + outNumString + prefixRule;
        Calendar cal = Calendar.getInstance();
        long startTmie = cal.getTime().getTime();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1, 0, 0, 0);
        long endTime = cal.getTime().getTime();
        //喷码规则凌晨失效
        stringRedisTemplate.opsForValue().set(Constants.RULE, rule, endTime - startTmie, TimeUnit.MILLISECONDS);
        return rule;
    }


    /**
     * 更新喷码规则
     *
     * @param
     * @return
     */
    @Override
    public String updateRule(String prefixRule, Map<String, Object> map) {
        return setDefaultRule(prefixRule, map);
    }

    /**
     *  通过装车道号，查询出厂编号工厂信息
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Map<String, Object> selectOutNum(String zId) {
//        Map map  = new HashMap();
//        map.put("N_OutNum","GH1258454F098");
//        map.put("N_Stock","000001800000000060");
//        map.put("N_Name","P·O42.5(50kg包装)");
//        map.put("F_ID","HS");
//        map.put("F_Name","黄石工厂");
        return spurtcodeInfoMapper.selectOutNum(zId);
//        return map;
    }

    /**
     * 获取喷码机信息
     * @param number
     * @return
     */
    @Override
    public String getCounter(Integer number,Map<String, Object> map) {
//        Map map  = new HashMap();
//        map.put("N_OutNum","GH1258454F098");
//        map.put("N_Stock","000001800000000060");
//        map.put("N_Name","P·O42.5(50kg包装)");
//        map.put("F_ID","HS");
//        map.put("F_Name","黄石工厂");

        String message ="操作成功";
        if(0==number){
            return "";
        }
        int currentNumber = number%40;
        String cuuurentCode="";
        //2.更新数据库
        SpurtcodeInfo spurtcodeInfo = spurtcodeInfoMapper.selectCurrentSpurtcode();
        //查询是否有当前喷码，有当前喷就更新，没有就重新生成喷码
        if (spurtcodeInfo != null) {
            //3.如果当前包数为四十，更新当前喷码状态为1，并生成新的喷码，插入数据库，并传输给喷码机
//            cuuurentCode=spurtcodeInfo.getCurrentCode();
            if (currentNumber == 0) {
                log.info("当前包数为40，重新生成喷码");
                spurtcodeInfo.setCurrentNumber(40);
                spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfo);
                //修改当前喷码状态，当前使用喷码->等待绑定喷码
                spurtcodeInfoMapper.updateCurrentStatus();
                //插入新的喷码数据
                cuuurentCode= beginGenerateSpurtcode(map);
            }else {
                spurtcodeInfo.setCurrentNumber(currentNumber);
                spurtcodeInfoMapper.updateByPrimaryKeySelective(spurtcodeInfo);
            }
            //更新失败，没有正在喷的喷码，生成一个新喷码，给喷码机使用
        } else {
            //没有当前喷码，重新生成当前喷码
            cuuurentCode=beginGenerateSpurtcode(map);
        }
        return message;
    }

    /**
     * 调用喷码机：
     * @param printCode
     * @throws TcpException
     * @throws IOException
     */
    public void tcpPrintCurrentCode(String printCode) throws TcpException, IOException {
        TcpListener listener = new TcpListener();
        ParamConfigTcp paramConfigTcp = new ParamConfigTcp();
        //设置TCP地址
//        paramConfigTcp.setHost("192.168.10.11");
        paramConfigTcp.setHost(ConfigUtil.getProperties("printer.controller.serverIp"));
        //设置TCP端口号
//        paramConfigTcp.setPort(1024);
        paramConfigTcp.setPort(Integer.parseInt(ConfigUtil.getProperties("printer.controller.serverPort")));
        if(StringUtils.isEmpty(printCode)){
            printCode="12222222222000001";
        }

        //加载TCP配置
        listener.init(paramConfigTcp);
        writeCommand(listener,printCode.getBytes("GB2312"));

    }

    public void writeCommand(TcpListener tcpListener, byte[] data) throws IOException, TcpException {
        byte[] head = {0x1B, 0x41, 0x29};
        byte length = (byte) (34 + data.length);
        byte body = 0x20;
        byte[] footer = {0x0D};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(head);
        os.write(length);
        os.write(body);
        os.write(data);
        os.write(footer);
        tcpListener.send(os.toByteArray());
        System.out.println(String.format("数据发送:%s", Utils.bytesToHex(os.toByteArray())));
    }
}
