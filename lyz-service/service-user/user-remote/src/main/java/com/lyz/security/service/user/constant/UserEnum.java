package com.lyz.security.service.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:34
 */
public class UserEnum {

    @Getter
    @AllArgsConstructor
    public enum LoginType {
        MOBILE(1, "手机号码登陆"),
        EMAIL(2, "邮箱登陆"),
        ;

        private int type;
        private String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Device {
        MOBILE(1, "移动端"),
        WEB(2, "网页端"),
        ;

        private int type;
        private String desc;
    }
}
