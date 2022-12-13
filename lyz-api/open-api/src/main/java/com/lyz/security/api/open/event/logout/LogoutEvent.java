package com.lyz.security.api.open.event.logout;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 注释:登出事件
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/13 13:20
 */
@Getter
@Setter
public class LogoutEvent extends ApplicationEvent {

    private final Long userId;

    private final String userName;

    public LogoutEvent(Object source, Long userId, String userName) {
        super(source);
        this.userId = userId;
        this.userName = userName;
    }
}
