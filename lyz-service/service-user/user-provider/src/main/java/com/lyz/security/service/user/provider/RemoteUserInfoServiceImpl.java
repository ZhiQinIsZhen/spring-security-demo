package com.lyz.security.service.user.provider;

import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.service.user.bo.UserInfoBO;
import com.lyz.security.service.user.remote.RemoteUserInfoService;
import com.lyz.security.service.user.service.IUserInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:41
 */
@DubboService
public class RemoteUserInfoServiceImpl implements RemoteUserInfoService {

    @Resource
    private IUserInfoService userInfoService;

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoBO getByUserId(Long userId) {
        return CommonCloneUtil.objectClone(userInfoService.getById(userId), UserInfoBO.class);
    }
}
