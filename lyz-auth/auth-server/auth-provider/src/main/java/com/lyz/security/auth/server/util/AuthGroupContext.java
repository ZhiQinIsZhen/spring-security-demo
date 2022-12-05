package com.lyz.security.auth.server.util;

import lombok.experimental.UtilityClass;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/5 11:22
 */
@UtilityClass
public class AuthGroupContext {

    private static InheritableThreadLocal<String> innerContext = new InheritableThreadLocal<>();

    public static String getAuthGroup() {
        return innerContext.get();
    }

    public static void setAuthGroup(String authGroup) {
        innerContext.set(authGroup);
    }

    public static void remove() {
        innerContext.remove();
    }
}
