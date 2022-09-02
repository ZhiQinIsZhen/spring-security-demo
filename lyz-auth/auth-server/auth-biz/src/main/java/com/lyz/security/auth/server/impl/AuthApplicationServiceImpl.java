package com.lyz.security.auth.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.security.auth.server.dao.AuthApplicationMapper;
import com.lyz.security.auth.server.model.AuthApplicationDO;
import com.lyz.security.auth.server.service.IAuthApplicationService;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 14:43
 */
@Service
public class AuthApplicationServiceImpl extends ServiceImpl<AuthApplicationMapper, AuthApplicationDO> implements IAuthApplicationService {
}
