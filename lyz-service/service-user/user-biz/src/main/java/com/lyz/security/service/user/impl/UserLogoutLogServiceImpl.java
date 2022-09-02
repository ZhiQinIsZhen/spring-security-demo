package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserLogoutLogMapper;
import com.lyz.security.service.user.model.UserLogoutLogDO;
import com.lyz.security.service.user.service.IUserLogoutLogService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 11:10
 */
@Service
public class UserLogoutLogServiceImpl extends ServiceImpl<UserLogoutLogMapper, UserLogoutLogDO> implements IUserLogoutLogService {
}
