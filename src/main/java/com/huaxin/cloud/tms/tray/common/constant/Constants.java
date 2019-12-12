package com.huaxin.cloud.tms.tray.common.constant;


/**
 * 通用常量信息
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";


    /**
     * 托盘状态
     * 1	满盘
     * 2	空拖
     * 3	在途（中转库）
     * 4	异地(客户） 
     * 5    已装车
     */
    public static final Integer RFID_STATUS_FULL = 1;
    public static final Integer RFID_STATUS_EMPTY = 2;
    public static final Integer RFID_STATUS_ONWAY = 3;
    public static final Integer RFID_STATUS_NOTLOCAL = 4;
    public static final Integer RFID_STATUS_LOADEDCAR= 5;



    /**
     * 托盘类型
     * 1	木质
     * 2 塑胶
     * 3	其它
     */
    public static final Integer RFID_TYPE_WOOD = 1;
    public static final Integer RFID_TYPE_PLASTIC = 2;
    public static final Integer RFID_TYPE_OTHER = 3;

    /**
     * 喷码当前状态
     * 0 已绑定
     * 1 等待绑定
     * 2 喷码机正在使用的喷码
     * 3 等待使用的喷码
     */
    public static final Integer CURRENT_STATUS_OK = 0;
    public static final Integer CURRENT_STATUS_WAITFOR = 1;
    public static final Integer CURRENT_STATUS_USED = 2;
    public static final Integer CURRENT_STATUS_WAITUSE= 3;

    /**
     * 托盘健康状态
     * 0 良好
     * 1 报损
     * 2 报废
     * 3 冻结
     */
    public static final Integer RFID_HEALTH_FINE = 0;
    public static final Integer RFID_HEALTH_REPORTLOSS = 1;
    public static final Integer RFID_HEALTH_SCRAP = 2;
    public static final Integer RFID_HEALTH_FROZEN=3;

    /**
     * 喷码规则redis的key
     */
    public  static final String RULE = "rule";

    /**
     * 喷码规则流水号默认前缀
     */
    public  static final String PREFIX = "9";


    /**
     * 出厂编号和工厂信息
     * 1.出厂编号
     * 2.物料编号
     * 3.物料描述
     * 4.工厂编号
     * 5.工厂描述
     */
    public  static final String N_OUTNUM = "N_OutNum";
    public  static final String N_STOCK = "N_Stock";
    public  static final String N_NAME = "N_Name";
    public  static final String F_ID = "F_ID";
    public  static final String F_NAME = "F_Name";

    /**
     * 版本号
     */
    public  static final String VERSION = "1.0";

    /**
     * 水泥单位
     */
    public  static final String UNIT = "包";
}

