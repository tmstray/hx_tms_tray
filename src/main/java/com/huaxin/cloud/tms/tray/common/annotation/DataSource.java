package com.huaxin.cloud.tms.tray.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huaxin.cloud.tms.tray.common.enums.DataSourceType;

/**
 * 
 * @Description: 自定义多数据源切换注解
 * @author Administrator
 * @date: 2019年12月26日下午2:10:09
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}

