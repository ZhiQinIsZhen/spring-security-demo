package com.lyz.security.common.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释:该优先级比 validation 参数校验注解 高
 * 即 先处理 Trim 再 处理 validation
 * //@JsonDeserialize(using = JsonTrimDeserializer.class)
 * //@JacksonAnnotationsInside
 *
 * @see com.lyz.security.common.controller.filter.JsonTrimDeserializer
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/1/10 10:06
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Trim {
}
