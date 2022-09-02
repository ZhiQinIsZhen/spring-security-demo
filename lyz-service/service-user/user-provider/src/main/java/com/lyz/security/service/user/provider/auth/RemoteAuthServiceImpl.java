package com.lyz.security.service.user.provider.auth;

import com.lyz.security.auth.server.bo.AuthUserLogoutBO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.util.DateUtil;
import com.lyz.security.service.user.model.UserLogoutLogDO;
import com.lyz.security.service.user.service.IUserLogoutLogService;
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
    private IUserLogoutLogService userLogoutLogService;

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
