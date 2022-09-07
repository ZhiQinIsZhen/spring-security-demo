package com.lyz.security.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.security.service.user.model.UserLoginLogDO;

import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 15:34
 */
public interface IUserLoginLogService extends IService<UserLoginLogDO> {

    /**
     * 获取上次登录时间
     *
     * @param userId
     * @param device
     * @return
     */
    Date lastLoginTime(Long userId, Integer device);
}
