package com.lyz.security.auth.client.context;

import com.google.common.base.Joiner;
import com.lyz.security.auth.client.constant.SecurityClientConstant;
import com.lyz.security.auth.client.user.AuthGrantedAuthority;
import com.lyz.security.auth.client.user.AuthUserDetails;
import com.lyz.security.auth.server.bo.AuthUser;
import com.lyz.security.auth.server.bo.AuthUserLoginBO;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.remote.RemoteJwtParseService;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.core.util.HttpServletContext;
import com.lyz.security.common.util.PatternUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 13:34
 */
@Configuration
public class AuthContext implements ApplicationContextAware, EnvironmentAware, InitializingBean {

    private static Environment environment;
    private static ApplicationContext applicationContext;
    private static InheritableThreadLocal<AuthUser> innerContext = new InheritableThreadLocal<>();
    private static RemoteAuthService remoteAuthService;
    private static RemoteJwtParseService remoteJwtParseService;
    private static AuthenticationManager authenticationManager;

    /**
     * 获取认证用户
     *
     * @return
     */
    public static AuthUser getAuthUser() {
        return innerContext.get();
    }

    /**
     * 设置认证用户
     *
     * @param authUser
     */
    public static void setAuthUser(AuthUser authUser) {
        innerContext.set(authUser);
    }

    /**
     * 移除认证用户
     */
    public static void remove() {
        innerContext.remove();
    }

    /**
     * 认证服务
     */
    public static class AuthService {

        /**
         * 用户注册
         *
         * @param authUserRegisterBO
         * @return
         */
        public static Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
            authUserRegisterBO.setApplicationName(environment.getProperty(SecurityClientConstant.DUBBO_APPLICATION_NAME_PROPERTY));
            return remoteAuthService.registry(authUserRegisterBO);
        }

        /**
         * 登陆
         *
         * @param authUserLoginBO
         */
        public static AuthUser login(AuthUserLoginBO authUserLoginBO) {
            authUserLoginBO.setApplicationName(environment.getProperty(SecurityClientConstant.DUBBO_APPLICATION_NAME_PROPERTY));
            authUserLoginBO.setDevice(DeviceContext.getDevice(HttpServletContext.getRequest()).getType());
            authUserLoginBO.setType(PatternUtil.checkMobileEmail(authUserLoginBO.getLoginName()));
            authUserLoginBO.setLoginIp(HttpServletContext.getIpAddress(HttpServletContext.getRequest()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(
                            authUserLoginBO.getDevice(),
                            authUserLoginBO.getApplicationName(),
                            authUserLoginBO.getLoginName()
                    ),
                    authUserLoginBO.getLoginPwd()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
            AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Date checkTime = remoteAuthService.login(
                    AuthUserLoginBO.builder()
                            .applicationName(authUserLoginBO.getApplicationName())
                            .userId(authUserDetails.getUserId())
                            .type(authUserLoginBO.getType())
                            .device(authUserLoginBO.getDevice())
                            .loginIp(authUserLoginBO.getLoginIp())
                            .build()
            );
            AuthUser authUser = CommonCloneUtil.objectClone(authUserDetails, AuthUser.class);
            authUser.setCheckTime(checkTime);
            authUser.setToken(JwtService.generateToken(authUser));
            authUser.setExpiration(JwtService.getExpiration(authUser.getToken()));
            return authUser;
        }

        /**
         * 根据登陆名查询用户信息
         *
         * @param username
         * @return
         */
        public static AuthUser loadByUsername(String username, Integer device) {
            AuthUser authUser = remoteAuthService.loadByUsername(username, device);
            return authUser;
        }

        /**
         * 登出
         *
         * @return
         */
        public static Boolean logout() {
            AuthUser authUser = getAuthUser();
            if (Objects.isNull(authUser)) {
                return Boolean.FALSE;
            }
            AuthUserLogoutBO authUserLogoutBO = CommonCloneUtil.objectClone(authUser, AuthUserLogoutBO.class);
            authUserLogoutBO.setType(PatternUtil.checkMobileEmail(authUser.getUsername()));
            authUserLogoutBO.setDevice(DeviceContext.getDevice(HttpServletContext.getRequest()).getType());
            authUserLogoutBO.setLogoutIp(HttpServletContext.getIpAddress());
            return remoteAuthService.logout(authUserLogoutBO);
        }
    }

    /**
     * jwt服务
     */
    public static class JwtService {

        /**
         * 解析token
         *
         * @param token
         * @param applicationName
         * @return
         */
        public static AuthUser parseToken(final String token, final String applicationName) {
            return remoteJwtParseService.parseToken(token, applicationName);
        }

        /**
         * 创建token
         *
         * @param authUser
         * @return
         */
        public static String generateToken(final AuthUser authUser) {
            return remoteJwtParseService.generateToken(authUser);
        }

        /**
         * 获取失效时间
         *
         * @param token
         * @return
         */
        public static Long getExpiration(final String token) {
            return remoteJwtParseService.getExpiration(token);
        }
    }

    public static class Transform {
        /**
         * 转化
         *
         * @param authUser
         * @return
         */
        public static AuthUserDetails getByAuthUser(AuthUser authUser) {
            if (Objects.isNull(authUser)) {
                return null;
            }
            return new AuthUserDetails(
                    authUser.getUsername(),
                    authUser.getPassword(),
                    CommonCloneUtil.listClone(authUser.getAuthorities(), AuthGrantedAuthority.class),
                    authUser.getUserId(),
                    authUser.getEmail(),
                    authUser.getMobile(),
                    authUser.getLoginType(),
                    authUser.getDevice(),
                    authUser.getNickName(),
                    authUser.getRealName(),
                    authUser.getApplicationName(),
                    authUser.getRoleIds(),
                    authUser.getSalt()
            );
        }
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        remoteAuthService = applicationContext.getBean("remoteAuthService-auth", RemoteAuthService.class);
        remoteJwtParseService = applicationContext.getBean("remoteJwtParseService-auth", RemoteJwtParseService.class);
        authenticationManager = applicationContext.getBean("authenticationManagerBean", AuthenticationManager.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
