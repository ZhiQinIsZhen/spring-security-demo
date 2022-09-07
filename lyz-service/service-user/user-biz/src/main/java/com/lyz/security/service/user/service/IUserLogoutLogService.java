package com.lyz.security.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.security.service.user.model.UserLogoutLogDO;

import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:10
 */
public interface IUserLogoutLogService extends IService<UserLogoutLogDO> {

    /**
     * 获取上次登出时间
     *
     * @param userId
     * @return
     */
    Date lastLogoutTime(Long userId, Integer device);
}
