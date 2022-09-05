package com.lyz.security.auth.client.constant;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 13:43
 */
public interface SecurityClientConstant {

    /**
     * 免登陆资源
     */
    String[] SECURITY_IGNORE_RESOURCES = new String[] {
            "/",
            "/instances/**",
            "/actuator/**",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};

    String OPTIONS_PATTERNS = "/**";

    String DEFAULT_TOKEN_HEADER_KEY = "Authorization";
}
