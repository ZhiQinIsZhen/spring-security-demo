package com.lyz.security.service.user.exception;

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
public enum UserExceptionCodeEnum implements IExceptionCodeService {
    MOBILE_EXIST("40001", "该手机号码已注册"),
    EMAIL_EXIST("40002", "该邮箱地址已注册"),
    REGISTER_ERROR("40003", "注册失败"),
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
