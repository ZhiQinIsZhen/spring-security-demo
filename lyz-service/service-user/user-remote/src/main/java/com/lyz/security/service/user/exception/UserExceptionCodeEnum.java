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
