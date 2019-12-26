package com.huaxin.cloud.tms.tray.dao;

import java.util.List;
import java.util.Map;
import com.huaxin.cloud.tms.tray.entity.SpurtcodeInfo;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author Axin
 */
public interface SpurtcodeInfoMapper {

    /**
     * 根据主键删除喷码
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 全字段插入喷码
     * @param record
     * @return
     */
    int insert(SpurtcodeInfo record);

    /**
     * 不为null字段插入喷码
     * @param record
     * @return
     */
    int insertSelective(SpurtcodeInfo record);

    /**
     * 根据主键查询喷码
     * @param id
     * @return
     */
    SpurtcodeInfo selectByPrimaryKey(Integer id);

    /**
     * 修改不为null的字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SpurtcodeInfo record);

    /**
     * 修改全字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(SpurtcodeInfo record);

    /**
     * 查询当前等待绑定的喷码信息
     * @return
     */
    SpurtcodeInfo selectByCurrentStatus();

    /**
     * 获得当前喷码信息
     * @Param
     * @return
     **/
    SpurtcodeInfo selectCurrentSpurtcode();

    /**
     * 更新当前喷码的喷码包数
     * @param currentNumber
     * @return
     */
    int updateCurrentNumber(@Param("currentNumber") int currentNumber);

    /**
     * 更新当前喷码状态，当前使用喷码->等待绑定喷码
     * @return
     */
    int updateCurrentStatus();

    /**
     * 逻辑删除当前喷码
     * @param currentCode
     * @return
     */
    int updateDeleteFlag(@Param("currentCode") String currentCode);

    /**
     * 根据喷码查找喷码
     * @param currentCode
     * @return
     */
    SpurtcodeInfo selectSpurtcodeByCurrentCode(@Param("currentCode") String currentCode);

    /**
     *  通过装车道号，查询出厂编号工厂信息
     * @param zId 装车id
     * @return
     */
    Map<String ,Object> selectOutNum(@Param("zId")String zId);

    /**
     * 查询所有生产线接口
     * @return 生产线对象集合
     */
    List<Map<String, Object>> selectZtlinesList();

    /**
     * 修改当前喷码
     * @param currentSpurtcode
     * @return
     */
    int updateCurrentSpurtcode(@Param("currentSpurtcode") String currentSpurtcode);

    /**
     * 查询重复喷码
     * @param currentSpurtcode
     * @return int
     */
    int selectRepetitive(@Param("currentSpurtcode") String currentSpurtcode);
}