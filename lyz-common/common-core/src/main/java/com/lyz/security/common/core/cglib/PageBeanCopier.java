package com.lyz.security.common.core.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lyz.security.common.remote.page.RemotePage;

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
     * @param sourceRemotePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> RemotePage<T> pageToPage(RemotePage<F> sourceRemotePageInfo, Function<? super F, ? extends T> function) {
        if (sourceRemotePageInfo == null) {
            return null;
        }
        List<T> targetList = Objects.nonNull(sourceRemotePageInfo.getList()) ?
                Lists.transform(sourceRemotePageInfo.getList(), function) :
                Lists.newArrayList();
        return new RemotePage<T>(
                targetList,
                sourceRemotePageInfo.getTotal(),
                sourceRemotePageInfo.getPages(),
                sourceRemotePageInfo.getPageNum(),
                sourceRemotePageInfo.getPageSize(),
                sourceRemotePageInfo.isHasNextPage()
        );
    }
}
