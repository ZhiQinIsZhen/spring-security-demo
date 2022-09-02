package com.lyz.security.auth.server.bo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 14:19
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserLogoutBO implements Serializable {
    private static final long serialVersionUID = -2204028880510973050L;

    private Long userId;

    /**
     * 登陆方式：1:手机;2:邮箱
     */
    private Integer type;

    private Integer device;

    private String logoutIp;

    private String applicationName;
}
