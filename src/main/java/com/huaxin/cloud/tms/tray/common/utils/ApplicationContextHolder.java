package com.huaxin.cloud.tms.tray.common.utils;

import com.huaxin.cloud.tms.tray.dao.SpurtcodeInfoMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description 手动获得注入bean实例
 * @Author lixin
 * @Date 2019/12/2 13:53
 * @Version 1.0
 **/
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextHolder.context = context;
    }
    //根据bean name 获取实例
    public static Object getBeanByName(String beanName) {
        if (beanName == null || context == null) {
            return null;
        }
        return context.getBean(beanName);
    }
    //只适合一个class只被定义一次的bean（也就是说，根据class不能匹配出多个该class的实例）
    public static Object getBeanByType(Class clazz) {
        if (clazz == null || context == null) {
            return null;
        }
        return context.getBean(clazz);
    }
    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }
}
