package com.lyz.security.auth.server.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Splitter;
import com.lyz.security.auth.server.bo.AuthUser;
import com.lyz.security.auth.server.bo.AuthUserLoginBO;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.auth.server.constant.GenericServiceMethod;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.auth.server.exception.RemoteAuthServiceException;
import com.lyz.security.auth.server.model.AuthApplicationDO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.service.IAuthApplicationService;
import com.lyz.security.auth.server.util.GenericServiceUtil;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 14:25
 */
@Slf4j
@DubboService
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private IAuthApplicationService authApplicationService;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用户注册
     *
     * @param authUserRegisterBO
     * @return
     */
    @Override
    public Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
        if (StringUtils.isBlank(authUserRegisterBO.getApplicationName())) {
            log.warn("用户注册错误，原因 : applicationName is blank");
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.REGISTRY_ERROR);
        }
        AuthUserRegisterBO registerBO = CommonCloneUtil.objectClone(authUserRegisterBO, AuthUserRegisterBO.class);
        registerBO.setPassword(passwordEncoder.encode(authUserRegisterBO.getPassword()));
        AuthApplicationDO authApplicationDO = authApplicationService.getOne(
                Wrappers.lambdaQuery(AuthApplicationDO.builder().applicationName(registerBO.getApplicationName()).build())
        );
        if (Objects.isNull(authApplicationDO)) {
            log.warn("用户注册错误，原因：没有找到对应应用配置信息，applicationName : {}", registerBO.getApplicationName());
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.REGISTRY_ERROR);
        }
        registerBO.setSalt(RandomUtil.randomChars(16));
        GenericService genericService = GenericServiceUtil.getByClassName(
                RemoteAuthService.class,
                authApplicationDO.getDubboVersion(),
                authApplicationDO.getDubboGroup(),
                authApplicationDO.getDubboTimeout()
        );
        return GenericServiceUtil.invoke(GenericServiceMethod.REGISTRY.getMethod(), Boolean.class, genericService, registerBO);
    }

    /**
     * 根据登陆名查询用户信息
     *
     * @param username
     * @param device
     * @return
     */
    @Override
    public AuthUser loadByUsername(String username, Integer device) {
        List<String> names = Splitter.on(CommonCoreConstant.DEFAULT_JOINER).splitToList(username);
        AuthApplicationDO authApplicationDO = authApplicationService.getOne(
                Wrappers.lambdaQuery(AuthApplicationDO.builder().applicationName(names.get(0)).build())
        );
        if (Objects.isNull(authApplicationDO)) {
            log.warn("查询用户信息失败，原因没有找到对应的应用配置信息，applicationName : {}", names.get(0));
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR);
        }
        GenericService genericService = GenericServiceUtil.getByClassName(
                RemoteAuthService.class,
                authApplicationDO.getDubboVersion(),
                authApplicationDO.getDubboGroup(),
                authApplicationDO.getDubboTimeout()
        );
        AuthUser authUser = GenericServiceUtil.invoke(
                GenericServiceMethod.LOAD_BY_USERNAME.getMethod(),
                AuthUser.class,
                genericService,
                names.get(1), device);
        authUser.setDevice(device);
        authUser.setApplicationName(names.get(0));
        return authUser;
    }

    /**
     * 登陆
     *
     * @param authUserLoginBO
     * @return
     */
    @Override
    public Date login(AuthUserLoginBO authUserLoginBO) {
        if (StringUtils.isBlank(authUserLoginBO.getApplicationName())) {
            log.warn("用户登陆错误，原因 : applicationName is blank");
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR);
        }
        AuthApplicationDO authApplicationDO = authApplicationService.getOne(
                Wrappers.lambdaQuery(AuthApplicationDO.builder().applicationName(authUserLoginBO.getApplicationName()).build())
        );
        if (Objects.isNull(authApplicationDO)) {
            log.warn("登陆失败，原因没有找到对应的应用配置信息，applicationName : {}", authUserLoginBO.getApplicationName());
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR);
        }
        GenericService genericService = GenericServiceUtil.getByClassName(
                RemoteAuthService.class,
                authApplicationDO.getDubboVersion(),
                authApplicationDO.getDubboGroup(),
                authApplicationDO.getDubboTimeout()
        );
        return GenericServiceUtil.invoke(GenericServiceMethod.LOGIN.getMethod(), Date.class, genericService, authUserLoginBO);
    }

    /**
     * 登出
     *
     * @param authUserLogoutBO
     * @return
     */
    @Override
    public Boolean logout(@NotNull AuthUserLogoutBO authUserLogoutBO) {
        if (StringUtils.isBlank(authUserLogoutBO.getApplicationName())) {
            return Boolean.FALSE;
        }
        AuthApplicationDO authApplicationDO = authApplicationService.getOne(
                Wrappers.lambdaQuery(AuthApplicationDO.builder().applicationName(authUserLogoutBO.getApplicationName()).build())
        );
        if (Objects.isNull(authApplicationDO)) {
            return Boolean.FALSE;
        }
        GenericService genericService = GenericServiceUtil.getByClassName(
                RemoteAuthService.class,
                authApplicationDO.getDubboVersion(),
                authApplicationDO.getDubboGroup(),
                authApplicationDO.getDubboTimeout()
        );
        return GenericServiceUtil.invoke(GenericServiceMethod.LOGOUT.getMethod(), Boolean.class, genericService, authUserLogoutBO);
    }
}
