/*
 * 文件名：com.wintao.ris.common.Code.java
 * <p>
 * <li>�?述：公有返回码常量，用于�?有组件均可以使用该常量返回码 ，其他具体组件可以有具体的组件返回码</li>
 * </p>
 * @Copyright: Copyright (c) 2013 yanxf
 * 修改内容：新�?
 * 修改时间�?2013-4-29 下午10:01:26
 * 修改人：yanxf
 * @since 1com.carfuture.common 1.0
 */

package com.huaxin.cloud.tms.tray.printer.service;

/**
 * <p>
 * 返回码定义，目前主要包括以下几项
 * <li>http协议返回�?</li>
 * <li>全局返回码，定义范围为：100000~100999,默认长度1000</li>
 * <li>httpclient返回码定义，定义范围为：101001~101100,默认长度100</li>
 * </p>
 *
 * @author yanxf
 * @since 1.0
 * @see
 */
public interface Code
{

    /****************************** http protocol code ******************************************/
    /**
     * OK
     */
    public static final int SC_OK = 200;
    /**
     * 重定�?
     */
    public static final int SC_REDIRECT = 302;
    /**
     * 内容修改
     */
    public static final int SC_NOT_MODIFIED = 304;
    /**
     * 错误请求
     */
    public static final int SC_BAD_REQUEST = 400;
    /**
     * 未鉴�?
     */
    public static final int SC_NOT_AUTHORIZED = 401;
    /**
     * 服务器接受请求，但是被拒绝处�?
     */
    public static final int SC_FORBIDDEN = 403;
    /**
     * 服务器已经找到任何匹配Request-URI的资�?
     */
    public static final int SC_NOT_FOUND = 404;
    /**
     * 未接�?
     */
    public static final int SC_NOT_ACCEPTABLE = 406;
    /**
     * 服务器遭遇异常阻止了当前请求的执�?
     */
    public static final int SC_INTERNAL_SERVER_ERROR = 500;
    /**
     *
     */
    public static final int SC_BAD_GATEWAY = 502;
    /**
     * 不能处理当前请求
     */
    public static final int SC_SERVICE_UNAVAILABLE = 503;
    /****************************** http protocol code ******************************************/

    /********************** 全局返回�? start add by yanxf at 20130427 ****************************/
    /**
     * 操作成功
     */
    public static final int SUCCESS = 10000;
    /**
     * 当前操作失败
     */
    public static final int FAILED = 10001; // request Success
    /**
     * 系统�?
     */
    public static final int SYSTEM_BUSY = 10002;
    /**
     * 系统错误
     */
    public static final int SYSTEM_ERROR = 10003;
    /**
     * 数据库操作失�?
     */
    public static final int DATABASE_FAILED = 10004;

    /**
     * 上下文操作失�?
     */
    public static final int CONTEXT_FAILED = 10005;
    /**
     * 安全校验失败
     */
    public static final int SECURITY_FAILED = 10006;
    /**
     * session操作失败（过期）
     */
    public static final int SESSION_FAILED = 10007;

    /**
     * 统称参数为空，针对具体的业务可以定义具体参数为空的返回码
     */
    public static final int PARAM_IS_NULL = 10008;
    /**
     * 参数类型错误
     */
    public static final int PARAMS_INCORRECT_TYPE = 10009;

    /**
     * 请求参数错误
     */
    public static final int PARAMS_INCORRECT_ERROR = 10010;

    /**
     * 此次登录已过�?,请重新登�?
     */
    public static final int LOGIN_EXPIRED = 10011;

    /**
     * 用户不存�?
     */
    public static final int USER_NOT_EXISTS = 10012;

    /**
     * 密码不正�?
     */
    public static final int USER_PASSWORD_ERROR = 10013;

    /**
     * 账号被禁�?
     */
    public static final int USER_DISABLED = 10014;

    /**
     * 您的验证码输入不正确,请重新输�?
     */
    public static final int RANDCODE_ERROR = 10015;



    /********************** 全局返回�? end add by yanxf at 20130427 ******************************/
    /********************** HttpClient返回�? add by yanxf at 20130427 ****************************/

    /**
     * 构�?�httpclient管理器失�?
     */
    public static final int CONSTRUCT_HTTPCLIENT_MANAGER_FAILED = 100501;
    /**
     * 请求的url为空
     */
    public static final int REQUEST_URL_IS_NULL = 100502;

    /**
     * SSL适配错误
     */
    public static final int ADAPTER_HTTPS_INCORRECT = 100503;


    /********************** HttpClient返回�? end add by yanxf at 20130427 ************************/

    public static final int DO_NOT_RESET = 100800;
}
