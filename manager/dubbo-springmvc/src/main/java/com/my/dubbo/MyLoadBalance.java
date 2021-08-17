package com.my.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

import java.util.HashMap;
import java.util.List;

public class MyLoadBalance implements LoadBalance {
    private HashMap<List, Integer> map = new HashMap();

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        Integer integer = invokers.size();
        boolean b = map.containsKey(invokers);
        if (b) {
            integer = map.get(invokers);
        }
        int index = integer % invokers.size();
        map.put(invokers, integer + 1);
        Invoker<T> tInvoker = invokers.get(index);
        return tInvoker;
    }
}
