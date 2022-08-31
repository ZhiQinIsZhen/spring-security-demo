package com.lyz.security.common.controller.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:50
 */
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(basePackages = {"com.lyz.security.common.controller"})
public class CommonControllerAutoConfig extends WebMvcConfigurationSupport {

    /**
     * 允许加载本地静态资源
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }
}
