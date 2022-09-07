package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserLogoutLogMapper;
import com.lyz.security.service.user.model.UserLogoutLogDO;
import com.lyz.security.service.user.service.IUserLogoutLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:10
 */
@Service
public class UserLogoutLogServiceImpl extends ServiceImpl<UserLogoutLogMapper, UserLogoutLogDO> implements IUserLogoutLogService {

    /**
     * 获取上次登出时间
     *
     * @param userId
     * @param device
     * @return
     */
    @Override
    public Date lastLogoutTime(Long userId, Integer device) {
        Page<UserLogoutLogDO> page = page(
                new Page<>(1, 1),
                Wrappers.lambdaQuery(UserLogoutLogDO.builder().userId(userId).device(device).build()).orderByDesc(UserLogoutLogDO::getId)
        );
        if (Objects.nonNull(page) && !CollectionUtils.isEmpty(page.getRecords())) {
            return page.getRecords().get(0).getLogoutTime();
        }
        return null;
    }
}
