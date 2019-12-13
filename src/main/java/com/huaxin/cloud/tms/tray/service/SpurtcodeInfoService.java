package com.huaxin.cloud.tms.tray.service;

import java.util.List;
import java.util.Map;

import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;

/**
 * 喷码信息Service接口
 * 
 * @author ruoyi
 * @date 2019-10-17
 */
public interface SpurtcodeInfoService 
{

    /**
     *获得当前喷码信息
     * @return SpurtcodeInfo 喷码对象
     **/
    public SpurtcodeInfo selectCurrentSpurtcode();
    /**
     * 根据规则生成喷码，插入数据库，并传给喷码机，开始喷码
     * @return
     */
    public int beginGenerateSpurtcode( Map<String, Object> map);

    /**
     * 查询所有生产线接口
     * @return 生产线集合
     */
    List<Map<String, Object>> selectZtlinesList();

    /**
     * 修改当前喷码
     * @param currentSpurtcode 新喷码
     * @return
     */
    int updateCurrentSpurtcode(String currentSpurtcode);

    /**
     *修改当前已喷水泥包数
     * @param currentNumber 新包数
     * @return
     */
    int updateCurrentNumber(int currentNumber);

    /**
     * 查询喷码规则
     * @return
     */
    String selectRule(Map<String,Object> map);

    /**
     * 修改喷码规则
     * @param prefixRule
     * @return int
     */
    String updateRule(String prefixRule, Map<String, Object> map);

    /**
     * 通过装车道号，查询出厂编号工厂信息
     * @return
     */
    Map<String ,Object> selectOutNum(String zId);
}
