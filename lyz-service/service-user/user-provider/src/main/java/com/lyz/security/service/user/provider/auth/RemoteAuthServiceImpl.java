package com.lyz.security.service.user.provider.auth;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyz.security.auth.server.bo.*;
import com.lyz.security.auth.server.constant.LoginType;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.auth.server.exception.RemoteAuthServiceException;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.util.DateUtil;
import com.lyz.security.common.util.PatternUtil;
import com.lyz.security.service.user.model.*;
import com.lyz.security.service.user.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 15:12
 */
@DubboService
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private IUserAuthMobileService userAuthMobileService;
    @Resource
    private IUserAuthEmailService userAuthEmailService;
    @Resource
    private IUserLoginLogService userLoginLogService;
    @Resource
    private IUserLogoutLogService userLogoutLogService;
    @Resource
    private IUserAuthorityService userAuthorityService;

    /**
     * 用户注册
     *
     * @param authUserRegisterBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
        if (StringUtils.isNotBlank(authUserRegisterBO.getMobile())
                && userAuthMobileService.count(Wrappers.lambdaQuery(UserAuthMobileDO.builder().mobile(authUserRegisterBO.getMobile()).build())) > 0) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.MOBILE_EXIST);
        }
        if (StringUtils.isNotBlank(authUserRegisterBO.getEmail())
                && userAuthEmailService.count(Wrappers.lambdaQuery(UserAuthEmailDO.builder().email(authUserRegisterBO.getEmail()).build())) > 0) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.EMAIL_EXIST);
        }
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

    /**
     * 根据登陆名查询用户信息
     *
     * @param username
     * @param device
     * @return
     */
    @Override
    public AuthUser loadByUsername(String username, Integer device) {
        AuthUser authUser = AuthUser.builder().username(username).loginType(PatternUtil.checkMobileEmail(username)).build();
        Long userId = getUserId(username, authUser);
        if (Objects.isNull(userId)) {
            return null;
        }
        UserInfoDO userInfoDO = userInfoService.getById(userId);
        authUser.setUserId(userId);
        authUser.setEmail(userInfoDO.getEmail());
        authUser.setMobile(userInfoDO.getMobile());
        authUser.setRegistryTime(userInfoDO.getRegistryTime());
        authUser.setNickName(userInfoDO.getNickName());
        authUser.setRealName(userInfoDO.getRealName());
        authUser.setSalt(userInfoDO.getSalt());
        authUser.setDevice(device);
        Date lastLoginTime = userLoginLogService.lastLoginTime(userId, device);
        Date lastLogoutTime = userLogoutLogService.lastLogoutTime(userId, device);
        authUser.setCheckTime(ObjectUtils.max(lastLoginTime, lastLogoutTime));
        return authUser;
    }

    /**
     * 登陆
     *
     * @param authUserLoginBO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Date login(AuthUserLoginBO authUserLoginBO) {
        UserLoginLogDO userLoginLogDO = CommonCloneUtil.objectClone(authUserLoginBO, UserLoginLogDO.class);
        userLoginLogDO.setLoginTime(DateUtil.currentDate());
        userLoginLogService.save(userLoginLogDO);
        //可能会有时间误差
        return userLoginLogService.getById(userLoginLogDO.getId()).getLoginTime();
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

    /**
     * 获取权限
     *
     * @param authUser
     * @return
     */
    @Override
    public List<AuthGrantedAuthorityBO> authorities(AuthUser authUser) {
        List<UserAuthorityDO> list = userAuthorityService.list(
                Wrappers.lambdaQuery(UserAuthorityDO.builder().userId(authUser.getUserId()).applicationName(authUser.getApplicationName())
                        .build())
        );
        return CommonCloneUtil.listClone(list, AuthGrantedAuthorityBO.class);
    }

    /**
     * 根据username获取对应用户id
     *
     * @param username
     * @param authUser
     * @return
     */
    private Long getUserId(String username, AuthUser authUser) {
        int type = PatternUtil.checkMobileEmail(username);
        authUser.setLoginType(type);
        if (type == LoginType.MOBILE.getType()) {
            UserAuthMobileDO userAuthMobileDO = userAuthMobileService.getOne(
                    Wrappers.lambdaQuery(UserAuthMobileDO.builder().mobile(username).build())
            );
            if (Objects.isNull(userAuthMobileDO)) {
                return null;
            }
            authUser.setPassword(userAuthMobileDO.getPassword());
            return userAuthMobileDO.getUserId();
        }
        if (type == LoginType.EMAIL.getType()) {
            UserAuthEmailDO userAuthEmailDO = userAuthEmailService.getOne(
                    Wrappers.lambdaQuery(UserAuthEmailDO.builder().email(username).build())
            );
            if (Objects.isNull(userAuthEmailDO)) {
                return null;
            }
            authUser.setPassword(userAuthEmailDO.getPassword());
            return userAuthEmailDO.getUserId();
        }
        return null;
    }
}
