package com.lyz.security.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 16:53
 */
@UtilityClass
public class JsonMapperUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String toJSONString(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T readValue(String content, Class<T> clazz) {
        if (StringUtils.isBlank(content) || Objects.isNull(clazz)) {
            return null;
        }
        return OBJECT_MAPPER.readValue(content, clazz);
    }

    @SneakyThrows
    public static void writeValue(OutputStream out, Object value) {
        OBJECT_MAPPER.writeValue(out, value);
    }
}
