package com.lyz.security.service.user.exception;

import com.lyz.security.common.remote.exception.IExceptionCodeService;
import com.lyz.security.common.remote.exception.RemoteServiceException;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:25
 */
public class RemoteUserServiceException extends RemoteServiceException {

    public RemoteUserServiceException() {
    }

    public RemoteUserServiceException(String message) {
        super(message);
    }

    public RemoteUserServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteUserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteUserServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
