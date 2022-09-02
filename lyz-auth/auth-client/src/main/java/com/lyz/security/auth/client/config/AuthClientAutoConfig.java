package com.lyz.security.auth.client.config;

import com.lyz.security.auth.server.remote.RemoteAuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 15:29
 */
@Configuration
@ComponentScan(value = {"com.lyz.security.auth.client"})
public class AuthClientAutoConfig {

    @DubboReference(group = "auth")
    private RemoteAuthService remoteAuthService;

    @Bean("remoteAuthService-auth")
    public RemoteAuthService remoteAuthService() {
        return remoteAuthService;
    }
}
