package com.lyz.security.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.service.user.dao.UserAuthorityMapper;
import com.lyz.security.service.user.model.UserAuthorityDO;
import com.lyz.security.service.user.service.IUserAuthorityService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 15:37
 */
@Service
public class UserAuthorityServiceImpl extends ServiceImpl<UserAuthorityMapper, UserAuthorityDO> implements IUserAuthorityService {
}
