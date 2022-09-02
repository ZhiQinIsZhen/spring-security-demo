package com.lyz.security.common.core.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lyz.security.common.core.cglib.PageBeanCopier;
import com.lyz.security.common.core.cglib.SimpleBeanCopier;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import com.lyz.security.common.remote.page.RemotePage;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 17:14
 */
@UtilityClass
public class CommonCloneUtil {

    /**
     * 用户深拷贝{@link SimpleBeanCopier}的副本
     */
    private static final SimpleBeanCopier copier = new SimpleBeanCopier();

    /**
     * 缓存容器
     */
    private static final Map<String, SimpleBeanCopier> COPIER_MAP = new ConcurrentHashMap<>(256);

    /**
     * 缓存bean拷贝
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> Y objectClone(T source, Class<Y> targetClass) {
        if (source == null) {
            return null;
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(source.getClass(), targetClass);
        return (Y) simpleBeanCopier.copy(source);
    }

    /**
     * list对象拷贝
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> List<Y> listClone(List<T> sourceList, Class<Y> targetClass) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.size() == 0) {
            return Lists.newArrayList();
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourceList.get(0).getClass(), targetClass);
        return Lists.transform(sourceList, simpleBeanCopier);
    }

    /**
     * page对象拷贝
     *
     * @param sourceRemotePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> RemotePage<Y> remotePageClone(RemotePage<T> sourceRemotePage, Class<Y> targetClass) {
        if (sourceRemotePage == null) {
            return null;
        }
        if (sourceRemotePage.getTotal() == 0 || CollectionUtils.isEmpty(sourceRemotePage.getList())) {
            return new RemotePage<>(
                    Lists.newArrayList(),
                    sourceRemotePage.getTotal(),
                    sourceRemotePage.getPages(),
                    sourceRemotePage.getPageNum(),
                    sourceRemotePage.getPageSize(),
                    sourceRemotePage.isHasNextPage()
            );
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourceRemotePage.getList().get(0).getClass(), targetClass);
        return PageBeanCopier.pageToPage(sourceRemotePage, simpleBeanCopier);
    }

    /**
     * mybatis page 转化为 remotePage
     *
     * @param page
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> RemotePage<Y> pageToRemotePage(Page<T> page, Class<Y> targetClass) {
        if (page == null) {
            return null;
        }
        return new RemotePage<Y>(
                CollectionUtils.isEmpty(page.getRecords()) ? Lists.newArrayList() : listClone(page.getRecords(), targetClass),
                page.getTotal(),
                page.getPages(),
                page.getCurrent(),
                page.getSize(),
                page.hasNext()
        );
    }

    /**
     * 缓存池获取{@link SimpleBeanCopier}
     *
     * @param sourceClass
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    private static <T,Y> SimpleBeanCopier getCopier(final Class<Y> sourceClass, final Class<T> targetClass) {
        String key = Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(sourceClass.getName(), targetClass.getName());
        SimpleBeanCopier copier = COPIER_MAP.get(key);
        if (Objects.nonNull(copier)) {
            return copier;
        }
        copier = getClone();
        copier.setSourceClass(sourceClass);
        copier.setTargetClass(targetClass);
        copier.init();
        COPIER_MAP.putIfAbsent(key, copier);
        return copier;
    }

    /**
     * 字节码拷贝{@link SimpleBeanCopier}
     *
     * @return
     */
    private static SimpleBeanCopier getClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oop = new ObjectOutputStream(bos);
            oop.writeObject(copier);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SimpleBeanCopier) ois.readObject();
        } catch (Exception e) {
            return new SimpleBeanCopier();
        }
    }
}
