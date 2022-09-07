package com.lyz.security.auth.server.remote;

import com.lyz.security.auth.server.bo.AuthUser;
import com.lyz.security.auth.server.bo.AuthUserLoginBO;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
     * 根据登陆名查询用户信息
     *
     * @param username
     * @param device
     * @return
     */
    AuthUser loadByUsername(String username, Integer device);

    /**
     * 登陆
     *
     * @param authUserLoginBO
     * @return
     */
    Date login(AuthUserLoginBO authUserLoginBO);

    /**
     * 登出
     *
     * @param authUserLogoutBO
     * @return
     */
    Boolean logout(@NotNull AuthUserLogoutBO authUserLogoutBO);
}
