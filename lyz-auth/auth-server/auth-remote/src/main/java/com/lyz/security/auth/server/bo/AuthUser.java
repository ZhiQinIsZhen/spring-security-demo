package com.lyz.security.auth.server.bo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 13:35
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser implements Serializable {
    private static final long serialVersionUID = -1586756175019155350L;

    private Long userId;

    private String username;

    private String password;

    private Integer loginType;

    private Integer device;

    private String realName;

    private String nickName;

    private String mobile;

    private String email;

    private String salt;

    private Date registryTime;

    private String applicationName;

    private Date checkTime;

    private List<Integer> roleIds;

    private String token;

    private Long expiration;

    private List<AuthGrantedAuthorityBO> authorities;
}
