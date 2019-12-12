package com.huaxin.cloud.tms.tray.common.exception;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import com.huaxin.cloud.tms.tray.common.constant.HttpStatus;
import com.huaxin.cloud.tms.tray.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.utils.StringPool;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;

/**
 * 全局异常处理器
 * 
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultInfo baseException(BaseException e)
    {
        log.error(e.getMessage(),e);
        return ResultInfo.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultInfo businessException(BusinessException e)
    {
        log.error(e.getMessage(),e);
        return ResultInfo.error(e.getMessage());
    }
    
    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultInfo customException(CustomException e)
    {
        log.error(e.getMessage(),e);
        if (StringUtils.isNull(e.getCode()))
        {
            return ResultInfo.error(e.getMessage());
        }
        return ResultInfo.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return ResultInfo.error("系统内部异常",e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(StringPool.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString(),e);
        return new ResultInfo(HttpStatus.BAD_REQUEST,message.toString());

    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultInfo handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), StringPool.DOT);
            message.append(pathArr[1]).append(violation.getMessage()).append(StringPool.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString(),e);
        return new ResultInfo(HttpStatus.BAD_REQUEST,message.toString());
    }

}

