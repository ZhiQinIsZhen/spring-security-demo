package com.lyz.security.auth.server.remote;

import com.lyz.security.auth.server.bo.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 获取权限
     *
     * @param authUser
     * @return
     */
    default List<AuthGrantedAuthorityBO> authorities(AuthUser authUser) {
        return new ArrayList<>();
    }
}
