package com.lyz.security.auth.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:44
 */
@Getter
@AllArgsConstructor
public enum LoginType {

    MOBILE(1, "手机号码登陆"),
    EMAIL(2, "邮箱登陆"),
    ;

    private int type;
    private String desc;
}
