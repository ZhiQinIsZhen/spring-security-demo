package com.lyz.security.auth.server.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.RouterFactory;

/**
 * 注释:group 路由策略
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/13 9:54
 */
@Activate(order = 300)
public class GroupRouterFactory implements RouterFactory {

    @Override
    public Router getRouter(URL url) {
        return new GroupInvokersSelector();
    }
}
