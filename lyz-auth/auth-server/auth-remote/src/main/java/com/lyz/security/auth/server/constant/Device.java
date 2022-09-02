package com.lyz.security.auth.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:45
 */
@Getter
@AllArgsConstructor
public enum Device {

    MOBILE(1, "移动端"),
    WEB(2, "网页端"),
    ;

    private int type;
    private String desc;
}
