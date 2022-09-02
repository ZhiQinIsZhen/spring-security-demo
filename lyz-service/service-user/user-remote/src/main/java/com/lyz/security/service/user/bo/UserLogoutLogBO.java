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
 * @date 2022/9/1 13:47
 */
@Getter
@Setter
public class UserLogoutLogBO implements Serializable {
    private static final long serialVersionUID = 3070437801653890936L;

    private Long userId;

    /**
     * 登陆方式：1:手机;2:邮箱
     */
    private Integer type;

    private Integer device;

    private Date logoutTime;

    private String logoutIp;
}
