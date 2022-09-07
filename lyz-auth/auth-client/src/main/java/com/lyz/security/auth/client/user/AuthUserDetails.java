package com.lyz.security.auth.client.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 16:09
 */
@Getter
@Setter
public class AuthUserDetails extends User {
    private static final long serialVersionUID = 5644677804996459865L;

    /**
     * 用户id
     */
    private final Long userId;

    /**
     * 用户邮箱
     */
    private final String email;

    /**
     * 用户手机
     */
    private final String mobile;

    /**
     * 登陆方式(1:手机;2:邮箱)
     */
    private final Integer loginType;

    /**
     * 设备(1移动端:;2:网页端)
     */
    private final Integer device;

    /**
     * 昵称
     */
    private final String nickName;

    /**
     * 真实姓名
     */
    private final String realName;

    /**
     * 应用名
     */
    private final String applicationName;

    /**
     * 用户角色
     */
    private final List<Integer> roleIds;

    /**
     * 加密盐
     */
    private final String salt;

    public AuthUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                           Long userId, String email, String mobile, Integer loginType, Integer device, String nickName,
                           String realName, String applicationName, List<Integer> roleIds, String salt) {
        super(username, password, authorities);
        this.userId = userId;
        this.email = email;
        this.mobile = mobile;
        this.loginType = loginType;
        this.device = device;
        this.nickName = nickName;
        this.realName = realName;
        this.applicationName = applicationName;
        this.roleIds = roleIds;
        this.salt = salt;
    }

    public AuthUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                           boolean credentialsNonExpired, boolean accountNonLocked,
                           Collection<? extends GrantedAuthority> authorities, Long userId, String email, String mobile,
                           Integer loginType, Integer device, String nickName, String realName, String applicationName,
                           List<Integer> roleIds, String salt) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.email = email;
        this.mobile = mobile;
        this.loginType = loginType;
        this.device = device;
        this.nickName = nickName;
        this.realName = realName;
        this.applicationName = applicationName;
        this.roleIds = roleIds;
        this.salt = salt;
    }


}
