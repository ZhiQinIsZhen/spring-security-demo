package com.lyz.security.auth.server.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.router.state.StateRouter;
import org.apache.dubbo.rpc.cluster.router.state.StateRouterFactory;

/**
 * 注释:auth group 路由策略
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/5 13:47
 */
@Activate(order = 1)
public class GroupStateRouterFactory implements StateRouterFactory {

    @Override
    public <T> StateRouter<T> getRouter(Class<T> interfaceClass, URL url) {
        return new GroupStateRouter(url);
    }
}
