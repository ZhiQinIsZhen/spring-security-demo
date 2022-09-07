package com.lyz.security.auth.server.bo;

import lombok.*;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 16:54
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserLoginBO implements Serializable {
    private static final long serialVersionUID = 4615014140715668121L;

    private Long userId;

    /**
     * 登陆方式：1:手机;2:邮箱
     */
    private Integer type;

    private Integer device;

    private String loginIp;

    private String loginName;

    private String loginPwd;

    private String applicationName;
}
