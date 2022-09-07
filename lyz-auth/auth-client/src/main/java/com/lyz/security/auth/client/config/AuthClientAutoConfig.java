package com.lyz.security.auth.client.config;

import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.remote.RemoteJwtParseService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

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

    @Resource
    private WebSecurityClientConfig webSecurityClientConfig;

    @DubboReference(group = "auth")
    private RemoteAuthService remoteAuthService;
    @DubboReference(group = "auth")
    private RemoteJwtParseService remoteJwtParseService;

    @Bean("remoteAuthService-auth")
    public RemoteAuthService remoteAuthService() {
        return remoteAuthService;
    }

    @Bean("remoteJwtParseService-auth")
    public RemoteJwtParseService remoteJwtParseService() {
        return remoteJwtParseService;
    }
}
