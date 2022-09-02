package com.lyz.security.service.user.remote;

import com.lyz.security.common.remote.page.BasePageBO;
import com.lyz.security.common.remote.page.RemotePage;
import com.lyz.security.service.user.bo.UserLogoutLogBO;

import javax.validation.constraints.NotNull;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:32
 */
public interface RemoteUserLogoutLogService {

    /**
     * 根据userId分页查询登出日志
     *
     * @param userId
     * @param pageBO
     * @return
     */
    RemotePage<UserLogoutLogBO> pageByUserId(@NotNull Long userId, @NotNull BasePageBO pageBO);
}
