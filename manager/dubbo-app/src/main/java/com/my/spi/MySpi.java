package com.my.spi;

import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-16 18:54
 */
@SPI("mysqi1")
public interface MySpi {
    void  say();
}
