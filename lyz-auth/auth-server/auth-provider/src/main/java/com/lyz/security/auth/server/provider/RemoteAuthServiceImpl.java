package com.lyz.security.auth.server.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.auth.server.constant.GenericServiceMethod;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.auth.server.exception.RemoteAuthServiceException;
import com.lyz.security.auth.server.model.AuthApplicationDO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.service.IAuthApplicationService;
import com.lyz.security.auth.server.util.GenericServiceUtil;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
        AuthUserRegisterBO registerBO = CommonCloneUtil.objectClone(authUserRegisterBO, AuthUserRegisterBO.class);
        registerBO.setPassword(passwordEncoder.encode(authUserRegisterBO.getPassword()));
        AuthApplicationDO authApplicationDO = authApplicationService.getOne(
                Wrappers.lambdaQuery(AuthApplicationDO.builder().applicationName(registerBO.getApplicationName()).build())
        );
        if (Objects.isNull(authApplicationDO)) {
            log.warn("用户注册错误，原因：没有找到对应应用配置信息，mobile：{}，email：{}", registerBO.getMobile(), registerBO.getEmail());
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.REGISTRY_ERROR);
        }
        registerBO.setSalt(RandomUtil.randomChars(4));
        GenericService genericService = GenericServiceUtil.getByClassName(
                RemoteAuthService.class,
                authApplicationDO.getDubboVersion(),
                authApplicationDO.getDubboGroup(),
                authApplicationDO.getDubboTimeout()
        );
        return GenericServiceUtil.invoke(GenericServiceMethod.REGISTRY.getMethod(), genericService, registerBO);
    }

    @Override
    public void login() {

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
        return GenericServiceUtil.invoke(GenericServiceMethod.LOGOUT.getMethod(), genericService, authUserLogoutBO);
    }
}
