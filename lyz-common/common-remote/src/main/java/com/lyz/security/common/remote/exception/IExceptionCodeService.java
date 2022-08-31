package com.lyz.security.common.remote.exception;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 16:45
 */
public interface IExceptionCodeService {

    /**
     * 获取异常code
     *
     * @return
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return
     */
    String getMessage();
}
