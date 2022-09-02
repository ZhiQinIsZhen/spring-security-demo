package com.lyz.security.auth.client.annotation;

import java.lang.annotation.*;

/**
 * 注释:匿名访问注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 10:17
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
