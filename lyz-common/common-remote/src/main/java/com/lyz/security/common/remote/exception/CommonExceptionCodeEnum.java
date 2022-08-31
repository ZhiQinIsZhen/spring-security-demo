package com.lyz.security.common.remote.exception;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 16:45
 */
public enum CommonExceptionCodeEnum implements IExceptionCodeService{

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    FORBIDDEN("401", "登陆后进行操作"),
    NO_RIGHT("403", "暂无权限"),
    PARAMS_VALIDATED("10000", "参数校验失败"),
    UNKNOWN_EXCEPTION("10001", "未知异常"),
    AUTHORIZATION_FAIL("10002", "认证失败"),
    AUTHORIZATION_TIMEOUT("10003", "认证超时"),
    LOGIN_FAIL("10004", "用户名或者密码错误"),
    REMOTE_SERVICE_FAIL("10005", "服务异常"),
    ;

    private String code;

    private String message;

    CommonExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
