package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserLoginLogMapper;
import com.lyz.security.service.user.model.UserLoginLogDO;
import com.lyz.security.service.user.service.IUserLoginLogService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 15:38
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLogDO> implements IUserLoginLogService {
}
