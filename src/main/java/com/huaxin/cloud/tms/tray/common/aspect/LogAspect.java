package com.huaxin.cloud.tms.tray.common.aspect;

import java.lang.reflect.Method;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import com.alibaba.fastjson.JSON;
import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.enums.BusinessStatus;
import com.huaxin.cloud.tms.tray.common.enums.HttpMethod;
import com.huaxin.cloud.tms.tray.common.manager.AsyncFactory;
import com.huaxin.cloud.tms.tray.common.manager.AsyncManager;
import com.huaxin.cloud.tms.tray.common.utils.ServletUtils;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.common.utils.ip.IpUtils;
import com.huaxin.cloud.tms.tray.entity.TrayLog;

/**
 * 操作日志记录处理
 * 
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    // 配置织入点
    @Pointcut("@annotation(com.huaxin.cloud.tms.tray.common.annotation.Log)")
    public void logPointCut()
    {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * 
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // *========数据库日志=========*//
            TrayLog trayLog = new TrayLog();
            trayLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            trayLog.setIp(ip);
            // 返回参数
            trayLog.setJsonResult(JSON.toJSONString(jsonResult));

            trayLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            trayLog.setCreateBy("xxx");
            
            if (e != null)
            {
            	trayLog.setStatus(BusinessStatus.FAIL.ordinal());
            	trayLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            trayLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            trayLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, trayLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(trayLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, TrayLog trayLog) throws Exception
    {
        // 设置action动作
    	trayLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
    	trayLog.setTitle(log.title());
        // 设置操作人类别
    	trayLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, trayLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, TrayLog trayLog) throws Exception
    {
        String requestMethod = trayLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
        {
            String params = argsArrayToString(joinPoint.getArgs());
            trayLog.setOperParam(StringUtils.substring(params, 0, 2000));
        }
        else
        {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            trayLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                if (!(paramsArray[i] instanceof MultipartFile))
                {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }
}

