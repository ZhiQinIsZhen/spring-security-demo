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
 * @date 2022/9/1 16:28
 */
@Getter
@Setter
public class UserInfoBO implements Serializable {
    private static final long serialVersionUID = 477985923412638468L;

    private Long userId;

    private String realName;

    private String nickName;

    private String mobile;

    private String email;

    private String salt;

    private Date registryTime;
}
