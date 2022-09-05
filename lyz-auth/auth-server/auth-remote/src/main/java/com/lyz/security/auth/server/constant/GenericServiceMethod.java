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
    ;

    private String method;

    private String methodName;
}
