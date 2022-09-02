package com.lyz.security.service.user.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:33
 */
@Getter
@Setter
public class UserLoginLogBO implements Serializable {
    private static final long serialVersionUID = -8978119199629210583L;

    private Long userId;

    /**
     * 登陆方式：1:手机;2:邮箱
     */
    private Integer type;

    private Integer device;

    private Date loginTime;

    private String loginIp;
}
