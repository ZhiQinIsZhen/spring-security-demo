package com.lyz.security.auth.client.impl;

import com.lyz.security.auth.client.context.AuthContext;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 11:21
 */
@Configuration
@ConditionalOnBean(value = {AuthenticationManager.class})
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int index = username.indexOf(CommonCoreConstant.DEFAULT_JOINER);
        if (index == -1) {
            throw new UsernameNotFoundException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL.getMessage());
        }
        return AuthContext.Transform.getByAuthUser(
                AuthContext.AuthService.loadByUsername(
                        username.substring(index + 1),
                        Integer.valueOf(username.substring(0, index))
                )
        );
    }
}
