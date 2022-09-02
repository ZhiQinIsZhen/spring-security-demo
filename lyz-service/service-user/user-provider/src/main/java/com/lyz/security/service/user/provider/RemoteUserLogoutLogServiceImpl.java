package com.lyz.security.service.user.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.remote.page.BasePageBO;
import com.lyz.security.common.remote.page.RemotePage;
import com.lyz.security.service.user.bo.UserLogoutLogBO;
import com.lyz.security.service.user.model.UserLogoutLogDO;
import com.lyz.security.service.user.remote.RemoteUserLogoutLogService;
import com.lyz.security.service.user.service.IUserLogoutLogService;
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
public class RemoteUserLogoutLogServiceImpl implements RemoteUserLogoutLogService {

    @Resource
    private IUserLogoutLogService userLogoutLogService;

    /**
     * 根据userId分页查询登出日志
     *
     * @param userId
     * @param pageBO
     * @return
     */
    @Override
    public RemotePage<UserLogoutLogBO> pageByUserId(@NotNull Long userId, @NotNull BasePageBO pageBO) {
        Page page = userLogoutLogService.page(
                new Page<>(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(UserLogoutLogDO.builder().userId(userId).build())
        );
        return CommonCloneUtil.pageToRemotePage(page, UserLogoutLogBO.class);
    }
}
