package com.my.spi.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-19 19:08
 */
public class MyFilter2 implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("before MyFilter2......");
        Result invoke = invoker.invoke(invocation);
        System.out.println("after MyFilter2......");
        return invoke;
    }
}
