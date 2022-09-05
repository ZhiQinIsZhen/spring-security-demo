package com.lyz.security.auth.client.context;

import com.lyz.security.auth.client.AuthUser;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.core.util.HttpServletContext;
import com.lyz.security.common.util.PatternUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 13:34
 */
@Configuration
public class AuthContext implements ApplicationContextAware, InitializingBean {

    private static ApplicationContext applicationContext;
    private static InheritableThreadLocal<AuthUser> innerContext = new InheritableThreadLocal<>();
    private static RemoteAuthService remoteAuthService;

    /**
     * 用户注册
     *
     * @param authUserRegisterBO
     * @return
     */
    public static Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
        return remoteAuthService.registry(authUserRegisterBO);
    }

    /**
     * 登陆
     */
    public static void login() {

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
        authUserLogoutBO.setType(PatternUtil.checkMobileEmail(authUser.getLoginName()));
        authUserLogoutBO.setDevice(DeviceContext.getDevice(HttpServletContext.getRequest()).getType());
        authUserLogoutBO.setLogoutIp(HttpServletContext.getIpAddress());
        return remoteAuthService.logout(authUserLogoutBO);
    }

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

    @Override
    public void afterPropertiesSet() throws Exception {
        remoteAuthService = applicationContext.getBean("remoteAuthService-auth", RemoteAuthService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
