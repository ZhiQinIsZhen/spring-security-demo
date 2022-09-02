package com.lyz.security.auth.server.exception;

import com.lyz.security.common.remote.exception.IExceptionCodeService;
import com.lyz.security.common.remote.exception.RemoteServiceException;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:25
 */
public class RemoteAuthServiceException extends RemoteServiceException {

    public RemoteAuthServiceException() {
    }

    public RemoteAuthServiceException(String message) {
        super(message);
    }

    public RemoteAuthServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteAuthServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteAuthServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
