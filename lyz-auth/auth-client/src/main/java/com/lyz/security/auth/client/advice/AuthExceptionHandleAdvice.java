package com.lyz.security.auth.client.advice;

import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.common.controller.result.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 10:52
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AuthExceptionHandleAdvice {

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public Result badCredentialsException(Exception exception) {
        return Result.error(AuthExceptionCodeEnum.LOGIN_FAIL);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public Result accessDeniedException(Exception exception) {
        return Result.error(AuthExceptionCodeEnum.NO_RIGHT);
    }
}
