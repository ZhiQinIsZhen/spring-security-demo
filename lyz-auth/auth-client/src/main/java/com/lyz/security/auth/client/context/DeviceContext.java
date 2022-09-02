package com.lyz.security.auth.client.context;

import com.lyz.security.auth.server.constant.Device;
import lombok.experimental.UtilityClass;
import org.springframework.mobile.device.LiteDeviceResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/2 13:19
 */
@UtilityClass
public class DeviceContext {

    /**
     * 获取设备类型
     *
     * @param request
     * @return
     */
    public static Device getDevice(HttpServletRequest request) {
        return new LiteDeviceResolver().resolveDevice(request).isMobile() ? Device.MOBILE : Device.WEB;
    }
}
