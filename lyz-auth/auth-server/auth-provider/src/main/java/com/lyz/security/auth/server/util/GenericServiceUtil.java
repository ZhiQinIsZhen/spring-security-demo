package com.lyz.security.auth.server.util;

import com.lyz.security.auth.server.exception.RemoteAuthServiceException;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.remote.exception.CommonExceptionCodeEnum;
import lombok.experimental.UtilityClass;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 14:53
 */
@UtilityClass
public class GenericServiceUtil {

    private static final Object LOCK = new Object();

    private static final Map<String, GenericService> GENERIC_SERVICE_MAP = new ConcurrentHashMap<>(64);

    /**
     * 获取dubbo的GenericService
     * dk1.8 ConcurrentHashMap.computeIfAbsent有死循环的可能性
     * 所以在此基础上又加了一把单例锁
     * 如果升级到9以上，可以优化为直接使用ConcurrentHashMap.computeIfAbsent方法
     *
     * @param className
     * @param version
     * @param group
     * @param timeout
     * @return
     */
    public static GenericService getByClassName(String className, String version, String group, Integer timeout) {
        if (!GENERIC_SERVICE_MAP.containsKey(className)) {
            synchronized (LOCK) {
                if (!GENERIC_SERVICE_MAP.containsKey(className)) {
                    ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
                    // 弱类型接口名
                    reference.setInterface(className);
                    reference.setVersion(version);
                    // 声明为泛化接口
                    reference.setGeneric(true);
                    reference.setGroup(group);
                    reference.setTimeout(timeout);
                    GENERIC_SERVICE_MAP.put(className, reference.get());
                }
            }
        }
        return GENERIC_SERVICE_MAP.get(className);
    }

    public static GenericService getByClassName(Class clazz, String version, String group, Integer timeout) {
        return getByClassName(clazz.getName(), version, group, timeout);
    }

    /**
     * 调用方法
     *
     * @param methodName
     * @param genericService
     * @param os
     * @param <T>
     * @return
     */
    public static <T> T invoke(String methodName, Class<T> targetClass, GenericService genericService, Object... os) {
        //获取入参的类型
        String[] paramTypes = null;
        if (Objects.nonNull(os)) {
            paramTypes = new String[os.length];
            for (int i = 0, j = os.length; i < j; i++) {
                paramTypes[i] = os[i].getClass().getName();
            }
        }
        Object result = genericService.$invoke(methodName, paramTypes, os);
        if (result instanceof Map) {
            try {
                return CommonCloneUtil.mapToBean((Map<String, Object>) result, targetClass);
            } catch (Exception e) {
                throw new RemoteAuthServiceException(CommonExceptionCodeEnum.REMOTE_SERVICE_FAIL);
            }
        }
        return (T) result;
    }
}
