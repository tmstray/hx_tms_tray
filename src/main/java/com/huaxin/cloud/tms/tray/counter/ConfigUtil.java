package com.huaxin.cloud.tms.tray.counter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 获取外部jar 外部配置文件 tcp协议配置
 *
 * @author xiongjinfeng 2010/12/13
 */
public class ConfigUtil {

    private static Properties properties;

    static {
        properties = new Properties();
        BufferedReader bufferedReader = null;
        try {
//            System.out.println(System.getProperty("user.dir") + "\\config\\config.properties");
            bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/config/config.properties"));
//            bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取外部参数对应的值
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

    /**
     * 返回int类型的参数
     * @param key
     * @return
     */
    public static int getPropertiesByInt(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }
}
