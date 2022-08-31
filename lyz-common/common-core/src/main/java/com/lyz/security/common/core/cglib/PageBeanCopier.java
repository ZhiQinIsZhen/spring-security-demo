package com.lyz.security.common.core.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lyz.security.common.remote.page.Page;

import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:06
 */
public class PageBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    /**
     * 自定义page to page
     *
     * @param sourcePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> Page<T> pageToPage(Page<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        if (sourcePageInfo == null) {
            return null;
        }
        List<T> targetList = Objects.nonNull(sourcePageInfo.getList()) ?
                Lists.transform(sourcePageInfo.getList(), function) :
                Lists.newArrayList();
        return new Page<T>(
                targetList,
                sourcePageInfo.getTotal(),
                sourcePageInfo.getPages(),
                sourcePageInfo.getPageNum(),
                sourcePageInfo.getPageSize(),
                sourcePageInfo.isHasNextPage()
        );
    }
}
