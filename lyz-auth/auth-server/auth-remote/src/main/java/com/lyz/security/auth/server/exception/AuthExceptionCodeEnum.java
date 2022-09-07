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
    AUTHORIZATION_TIMEOUT("20003", "认证过期"),
    REGISTRY_ERROR("20004", "注册错误"),
    LOGIN_ERROR("20005", "登陆错误"),
    OTHERS_LOGIN("20006", "该账号已在其他地方登陆"),
    MOBILE_EXIST("20007", "该手机号码已注册"),
    EMAIL_EXIST("20008", "该邮箱地址已注册"),
    REGISTER_ERROR("20009", "注册失败"),
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
