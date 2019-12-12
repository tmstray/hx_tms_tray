package com.huaxin.cloud.tms.tray.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 功能描述: Excel注解集
* @param: 
* @return: 
* @auther: Administrator
* @date: 2019/12/5 17:34
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels
{
    Excel[] value();
}