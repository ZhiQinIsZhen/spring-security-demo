package com.lyz.security.auth.server.bo;

import lombok.*;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterBO implements Serializable {
    private static final long serialVersionUID = -9050425965297199028L;

    /**
     * 用户真实名称
     */
    private String realName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 用户邮箱地址
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 加密盐
     */
    private String salt;
}
