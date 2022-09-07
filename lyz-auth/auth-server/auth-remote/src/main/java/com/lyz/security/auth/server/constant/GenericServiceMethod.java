package com.lyz.security.auth.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 9:47
 */
@Getter
@AllArgsConstructor
public enum GenericServiceMethod {

    REGISTRY("registry", "注册"),
    LOGIN("login", "登陆"),
    LOGOUT("logout", "登出"),
    LOAD_BY_USERNAME("loadByUsername", "根据登陆名查询用户信息"),
    AUTHORITIES("authorities", "获取权限"),
    ;

    private String method;

    private String methodName;
}
