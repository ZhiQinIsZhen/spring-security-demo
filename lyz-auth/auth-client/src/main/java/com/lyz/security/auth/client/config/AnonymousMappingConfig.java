package com.lyz.security.auth.client.config;

import com.google.common.collect.Sets;
import com.lyz.security.auth.client.annotation.Anonymous;
import com.lyz.security.common.util.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Objects;
import java.util.Set;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 9:54
 */
@Slf4j
@Configuration
public class AnonymousMappingConfig implements ApplicationContextAware, InitializingBean, Ordered {

    private static ApplicationContext applicationContext;
    private static Set<String> anonymousMappings = Sets.newHashSet();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * 获取免鉴权的mappings
     * can see annotation {@link Anonymous}
     *
     * @return
     */
    public static Set<String> getAnonymousMappings() {
        return anonymousMappings;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        if (Objects.isNull(handlerMapping)) {
            return;
        }
        handlerMapping.getHandlerMethods().forEach((k, v) -> {
            boolean hasAnonymous = v.getBeanType().isAnnotationPresent(Anonymous.class) ? true : v.hasMethodAnnotation(Anonymous.class) ? true : false;
            if (hasAnonymous) {
                anonymousMappings.addAll(k.getPatternsCondition().getPatterns());
            }
        });
        log.info("Anonymous mappings : {}", JsonMapperUtil.toJSONString(anonymousMappings));
    }
}
