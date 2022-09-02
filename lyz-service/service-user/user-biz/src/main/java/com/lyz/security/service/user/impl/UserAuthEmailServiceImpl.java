package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserAuthEmailMapper;
import com.lyz.security.service.user.model.UserAuthEmailDO;
import com.lyz.security.service.user.service.IUserAuthEmailService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 15:37
 */
@Service
public class UserAuthEmailServiceImpl extends ServiceImpl<UserAuthEmailMapper, UserAuthEmailDO> implements IUserAuthEmailService {
}
