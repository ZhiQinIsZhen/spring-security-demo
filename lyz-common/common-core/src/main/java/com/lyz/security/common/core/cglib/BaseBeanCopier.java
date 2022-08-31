package com.lyz.security.common.core.cglib;

import com.google.common.base.Function;
import com.lyz.security.common.remote.exception.CommonExceptionCodeEnum;
import com.lyz.security.common.remote.exception.RemoteServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:04
 */
@Slf4j
public class BaseBeanCopier<F, T> implements Function<F, T> {

    private BeanCopier beanCopier;

    protected BeanCopier getBeanCopier() {
        return beanCopier;
    }

    public void init() {
        this.beanCopier = BeanCopier.create(sourceClass, targetClass, false);
    }

    private Class<T> targetClass;

    private Class<F> sourceClass;

    private BaseBeanCopier<T, F> reverse;

    public BaseBeanCopier<T, F> reverse() {
        return reverse;
    }

    public Function<T, F> getReverse() {
        if (this.reverse != null) {
            return this.reverse;
        }
        BaseBeanCopier<T, F> reverse = this.reverse;
        synchronized (this) {
            if (reverse == null) {
                reverse = new BaseBeanCopier<T, F>();
                reverse.setSourceClass(this.getTargetClass());
                reverse.setTargetClass(this.getSourceClass());
                reverse.init();
            }
        }
        return reverse;
    }

    public void setReverse(BaseBeanCopier<T, F> reverse) {
        this.reverse = reverse;
    }

    protected Class<T> getTargetClass() {
        return targetClass;
    }

    protected Class<F> getSourceClass() {
        return sourceClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public void setSourceClass(Class<F> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public T afterCopy(F source, T target) {
        return target;
    }

    public T copy(F input) {
        try {
            if (Objects.isNull(input)) {
                return null;
            }
            T o = targetClass.newInstance();
            beanCopier.copy(input, o, null);
            return afterCopy(input, o);
        } catch (Exception e) {
            log.error("create object fail, class: {}", targetClass.getName(), e);
            throw new RemoteServiceException(CommonExceptionCodeEnum.REMOTE_SERVICE_FAIL);
        }
    }

    @Override
    public T apply(F f) {
        return copy(f);
    }
}
