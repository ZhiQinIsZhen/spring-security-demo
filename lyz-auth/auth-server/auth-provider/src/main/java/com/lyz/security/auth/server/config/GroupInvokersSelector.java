package com.lyz.security.auth.server.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.AbstractRouter;
import org.apache.dubbo.rpc.cluster.router.RouterResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/13 9:44
 */
public class GroupInvokersSelector extends AbstractRouter {

    public GroupInvokersSelector(URL url) {
        super(url);
    }

    public GroupInvokersSelector() {
    }

    @Override
    public <T> RouterResult<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation, boolean needToPrintMessage) throws RpcException {
        if (CollectionUtils.isEmpty(invokers)) {
            return super.route(invokers, url, invocation, needToPrintMessage);
        }
        List<Invoker<T>> list = invokers.stream()
                .filter((it) -> it.getUrl().getGroup(StringUtils.EMPTY).equals("user"))
                .collect(Collectors.toList());
        return super.route(list, url, invocation, needToPrintMessage);
    }
}
