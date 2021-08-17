package com.my.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Test
    public void DubboSPITest() {
        ExtensionLoader<MySpi> extensionLoader =
                ExtensionLoader.getExtensionLoader(MySpi.class);
        MySpi adaptiveExtension = extensionLoader.getAdaptiveExtension();
        MySpi myspi1 = extensionLoader.getExtension("myspi1");

        MySpi myspi2 = extensionLoader.getExtension("myspi2");
    }

    @Test
    public void LoadBalanceTest() {
        ExtensionLoader<LoadBalance> extensionLoader =
                ExtensionLoader.getExtensionLoader(LoadBalance.class);
        LoadBalance adaptiveExtension = extensionLoader.getAdaptiveExtension();
        LoadBalance random = extensionLoader.getExtension("random");
    }
}
