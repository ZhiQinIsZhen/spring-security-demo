package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserAuthMobileMapper;
import com.lyz.security.service.user.model.UserAuthMobileDO;
import com.lyz.security.service.user.service.IUserAuthMobileService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 15:36
 */
@Service
public class UserAuthMobileServiceImpl extends ServiceImpl<UserAuthMobileMapper, UserAuthMobileDO> implements IUserAuthMobileService {
}
