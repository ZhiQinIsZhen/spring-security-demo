package com.lyz.security.api.open.event.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 注释:登陆事件
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/13 13:20
 */
@Getter
@Setter
public class LoginEvent extends ApplicationEvent {

    private final Long userId;

    private final String userName;

    public LoginEvent(Object source, Long userId, String userName) {
        super(source);
        this.userId = userId;
        this.userName = userName;
    }
}
