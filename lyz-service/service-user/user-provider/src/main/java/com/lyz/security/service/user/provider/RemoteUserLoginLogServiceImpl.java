package com.lyz.security.service.user.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.remote.page.BasePageBO;
import com.lyz.security.common.remote.page.RemotePage;
import com.lyz.security.service.user.bo.UserLoginLogBO;
import com.lyz.security.service.user.model.UserLoginLogDO;
import com.lyz.security.service.user.remote.RemoteUserLoginLogService;
import com.lyz.security.service.user.service.IUserLoginLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:39
 */
@DubboService
public class RemoteUserLoginLogServiceImpl implements RemoteUserLoginLogService {

    @Resource
    private IUserLoginLogService userLoginLogService;

    /**
     * 根据userId分页查询登陆日志
     *
     * @param userId
     * @param pageBO
     * @return
     */
    @Override
    public RemotePage<UserLoginLogBO> pageByUserId(@NotNull Long userId, @NotNull BasePageBO pageBO) {
        Page page = userLoginLogService.page(
                new Page<>(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(UserLoginLogDO.builder().userId(userId).build())
        );
        return CommonCloneUtil.pageToRemotePage(page, UserLoginLogBO.class);
    }
}
