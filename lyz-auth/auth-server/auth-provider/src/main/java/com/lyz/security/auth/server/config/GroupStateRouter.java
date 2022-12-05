package com.lyz.security.auth.server.config;

import com.lyz.security.auth.client.constant.SecurityClientConstant;
import com.lyz.security.auth.server.util.AuthGroupContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.Holder;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.RouterResult;
import org.apache.dubbo.rpc.cluster.router.RouterSnapshotNode;
import org.apache.dubbo.rpc.cluster.router.state.AbstractStateRouter;
import org.apache.dubbo.rpc.cluster.router.state.BitList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/5 13:50
 */
public class GroupStateRouter<T> extends AbstractStateRouter<T> {

    public GroupStateRouter(URL url) {
        super(url);
    }

    @Override
    protected BitList<Invoker<T>> doRoute(BitList<Invoker<T>> invokers, URL url, Invocation invocation, boolean needToPrintMessage, Holder<RouterSnapshotNode<T>> routerSnapshotNodeHolder, Holder<String> messageHolder) throws RpcException {
        String authGroup = AuthGroupContext.getAuthGroup();
        if (StringUtils.isBlank(authGroup) || CollectionUtils.isEmpty(invokers)) {
            return invokers;
        }
        List<Invoker<T>> list = invokers.stream()
                .filter((it) -> it.getUrl().getParameter(SecurityClientConstant.DUBBO_AUTH_GROUP, StringUtils.EMPTY).equals(authGroup))
                .collect(Collectors.toList());
        return new BitList<>(list);
    }
}
