package com.lyz.security.auth.server.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.model.AuthApplicationDO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.service.IAuthApplicationService;
import com.lyz.security.auth.server.util.GenericServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.service.GenericService;

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
@DubboService(group = "auth")
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private IAuthApplicationService authApplicationService;

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
        return GenericServiceUtil.invoke("logout", genericService, authUserLogoutBO);
    }
}
