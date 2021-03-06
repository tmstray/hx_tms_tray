package com.huaxin.cloud.tms.tray.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @Description: 程序注解配置
 * @author Administrator
 * @date: 2019年12月26日下午2:09:01
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.huaxin.cloud.tms.tray.**.dao")
public class ApplicationConfig
{

}
