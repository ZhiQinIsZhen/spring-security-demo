package com.lyz.security.auth.server.remote;

import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;

import javax.validation.constraints.NotNull;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 14:14
 */
public interface RemoteAuthService {

    /**
     * 用户注册
     *
     * @param authUserRegisterBO
     * @return
     */
    Boolean registry(AuthUserRegisterBO authUserRegisterBO);

    /**
     * 登陆
     */
    void login();

    /**
     * 登出
     *
     * @param authUserLogoutBO
     * @return
     */
    Boolean logout(@NotNull AuthUserLogoutBO authUserLogoutBO);
}
