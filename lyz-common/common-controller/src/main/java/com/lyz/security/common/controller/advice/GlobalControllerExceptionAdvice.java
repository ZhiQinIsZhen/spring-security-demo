package com.lyz.security.common.controller.advice;

import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.remote.exception.CommonExceptionCodeEnum;
import com.lyz.security.common.remote.exception.RemoteServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

/**
 * 注释:全局异常统一处理
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:47
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalControllerExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public Result exception(Exception exception) {
        log.warn("未知异常", exception);
        return Result.error(CommonExceptionCodeEnum.UNKNOWN_EXCEPTION);
    }

    @ExceptionHandler({RpcException.class})
    public Result rpcException(RpcException exception) {
        log.warn("远程服务调用异常->rpc", exception);
        return Result.error(CommonExceptionCodeEnum.REMOTE_SERVICE_FAIL);
    }

    @ExceptionHandler({BindException.class})
    public Result bindException(BindException exception) {
        if (Objects.nonNull(exception) && exception.hasErrors()) {
            List<ObjectError> errors = exception.getAllErrors();
            for (ObjectError error : errors) {
                log.warn("参数校验 {} ：{}", error.getCodes()[0], error.getDefaultMessage());
                return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED.getCode(), error.getDefaultMessage());
            }
        }
        log.warn("参数校验出错了");
        return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED);
    }

    @ExceptionHandler({ValidationException.class})
    public Result validationException(ValidationException exception) {
        String[] message = exception.getMessage().split(":");
        if (message.length >= 2) {
            log.warn("参数校验 exception ：{}", message);
            return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED.getCode(), message[1].trim());
        }
        return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED);
    }

    @ExceptionHandler({RemoteServiceException.class})
    public Result remoteServiceException(RemoteServiceException exception) {
        log.warn("has remoteServiceException", exception);
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
