package com.lyz.security.auth.server.listener;

import com.lyz.security.auth.server.constant.Device;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 注释:接口预热处理
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/2 11:36
 */
@Service
public class InterfaceHotListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private RemoteAuthService remoteAuthService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        remoteAuthService.loadByUsername("open-api:15988654730", Device.WEB.getType());
    }
}
