package com.my.spi.lb;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-16 19:43
 */
@Adaptive
public class LoadBalanceAdaptive implements LoadBalance {

    public com.alibaba.dubbo.rpc.Invoker select(java.util.List arg0, com.alibaba.dubbo.common.URL arg1, com.alibaba.dubbo.rpc.Invocation arg2) throws com.alibaba.dubbo.rpc.RpcException {
        if (arg1 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg1;
        if (arg2 == null) throw new IllegalArgumentException("invocation == null");
        String methodName = arg2.getMethodName();
        String extName = url.getMethodParameter(methodName, "loadbalance", "random");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.cluster.LoadBalance) name from url(" + url.toString() + ") use keys([loadbalance])");
        com.alibaba.dubbo.rpc.cluster.LoadBalance extension = (com.alibaba.dubbo.rpc.cluster.LoadBalance) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.cluster.LoadBalance.class).getExtension(extName);
        return extension.select(arg0, arg1, arg2);
    }
}
