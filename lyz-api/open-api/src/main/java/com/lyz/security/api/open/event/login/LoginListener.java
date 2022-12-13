package com.lyz.security.api.open.event.login;

import com.lyz.security.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 注释:登陆监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/13 13:27
 */
@Slf4j
@Service
public class LoginListener implements ApplicationListener<LoginEvent> {

    @Async
    @Override
    public void onApplicationEvent(LoginEvent event) {
        log.info("用户Id:[{}], 用户名:[{}], 时间:[{}], 登陆系统了...",
                event.getUserId(),
                event.getUserName(),
                DateUtil.formatDate(new Date(event.getTimestamp()))
        );
    }
}
