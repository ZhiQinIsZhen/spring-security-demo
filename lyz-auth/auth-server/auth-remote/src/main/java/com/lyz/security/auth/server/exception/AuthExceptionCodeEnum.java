package com.lyz.security.auth.server.exception;

import com.lyz.security.common.remote.exception.IExceptionCodeService;
import lombok.AllArgsConstructor;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:26
 */
@AllArgsConstructor
public enum AuthExceptionCodeEnum implements IExceptionCodeService {
    FORBIDDEN("401", "登陆后进行操作"),
    NO_RIGHT("403", "暂无权限"),
    LOGIN_FAIL("20001", "用户名或者密码错误"),
    AUTHORIZATION_FAIL("20002", "认证失败"),
    AUTHORIZATION_TIMEOUT("20003", "认证超时"),
    REGISTRY_ERROR("20004", "注册错误"),
    LOGIN_ERROR("20005", "登陆错误"),
    ;

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
