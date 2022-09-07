package com.lyz.security.auth.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.auth.server.dao.AuthJwtMapper;
import com.lyz.security.auth.server.model.AuthJwtDO;
import com.lyz.security.auth.server.service.IAuthJwtService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 15:20
 */
@Service
public class AuthJwtServiceImpl extends ServiceImpl<AuthJwtMapper, AuthJwtDO> implements IAuthJwtService {
}
