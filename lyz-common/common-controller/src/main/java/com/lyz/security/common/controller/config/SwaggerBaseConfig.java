package com.lyz.security.common.controller.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:49
 */
public class SwaggerBaseConfig {

    protected final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerBaseConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档")
                .termsOfServiceUrl("http://127.0.0.1:7070/")
                .contact(new Contact("lyz", "https://github.com/ZhiQinIsZhen", "liyangzhen0114@foxmail.com"))
                .version("1.0.0")
                .build();
    }
}
