package com.my.spi;

import com.alibaba.dubbo.common.extension.Adaptive;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-16 18:54
 */
//@Adaptive
public class MySpiAdaptive implements MySpi {
    @Override
    public void say() {
        System.out.println("MySpiAdaptor 2");
    }
}
