package com.lyz.security.common.core.cglib;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:06
 */
public class SimpleBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    public SimpleBeanCopier() {}

    public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }
}
