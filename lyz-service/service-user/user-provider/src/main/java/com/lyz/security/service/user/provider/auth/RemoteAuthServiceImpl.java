package com.lyz.security.service.user.provider.auth;

import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.util.DateUtil;
import com.lyz.security.service.user.model.UserAuthEmailDO;
import com.lyz.security.service.user.model.UserAuthMobileDO;
import com.lyz.security.service.user.model.UserInfoDO;
import com.lyz.security.service.user.model.UserLogoutLogDO;
import com.lyz.security.service.user.service.IUserAuthEmailService;
import com.lyz.security.service.user.service.IUserAuthMobileService;
import com.lyz.security.service.user.service.IUserInfoService;
import com.lyz.security.service.user.service.IUserLogoutLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 15:12
 */
@DubboService(group = "user")
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private IUserAuthMobileService userAuthMobileService;
    @Resource
    private IUserAuthEmailService userAuthEmailService;
    @Resource
    private IUserLogoutLogService userLogoutLogService;

    /**
     * 用户注册
     *
     * @param authUserRegisterBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
        UserInfoDO userInfoDO = CommonCloneUtil.objectClone(authUserRegisterBO, UserInfoDO.class);
        userInfoDO.setRegistryTime(DateUtil.currentDate());
        userInfoService.save(userInfoDO);
        if (StringUtils.isNotBlank(userInfoDO.getMobile())) {
            UserAuthMobileDO mobileDO = UserAuthMobileDO
                    .builder()
                    .userId(userInfoDO.getUserId())
                    .mobile(userInfoDO.getMobile())
                    .password(authUserRegisterBO.getPassword())
                    .build();
            userAuthMobileService.save(mobileDO);
        }
        if (StringUtils.isNotBlank(userInfoDO.getEmail())) {
            UserAuthEmailDO emailDO = UserAuthEmailDO
                    .builder()
                    .userId(userInfoDO.getUserId())
                    .email(userInfoDO.getEmail())
                    .password(authUserRegisterBO.getPassword())
                    .build();
            userAuthEmailService.save(emailDO);
        }
        return Boolean.TRUE;
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
    @Transactional(rollbackFor = Exception.class)
    public Boolean logout(AuthUserLogoutBO authUserLogoutBO) {
        UserLogoutLogDO userLogoutLogDO = CommonCloneUtil.objectClone(authUserLogoutBO, UserLogoutLogDO.class);
        userLogoutLogDO.setLogoutTime(DateUtil.currentDate());
        return userLogoutLogService.save(userLogoutLogDO);
    }
}
