package com.lyz.security.service.user.remote;

import com.lyz.security.service.user.bo.UserInfoBO;

import javax.validation.constraints.NotNull;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:28
 */
public interface RemoteUserInfoService {

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoBO getByUserId(@NotNull Long userId);
}
