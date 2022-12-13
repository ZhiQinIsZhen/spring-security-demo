package com.lyz.security.api.open.event.logout;

import com.lyz.security.api.open.event.login.LoginEvent;
import com.lyz.security.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 注释:登出监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/13 13:27
 */
@Slf4j
@Service
public class LogoutListener implements ApplicationListener<LogoutEvent> {

    @Async
    @Override
    public void onApplicationEvent(LogoutEvent logoutEvent) {
        log.info("用户Id:[{}], 用户名:[{}], 时间:[{}], 登陆系统了...",
                logoutEvent.getUserId(),
                logoutEvent.getUserName(),
                DateUtil.formatDate(new Date(logoutEvent.getTimestamp()))
        );
    }
}
