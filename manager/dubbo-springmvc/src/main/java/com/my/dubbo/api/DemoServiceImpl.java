package com.my.dubbo.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoServiceImpl implements DemoService, ApplicationContextAware {

    //    @Autowired
    private ProtocolConfig config;

    private ApplicationContext applicationContext;

    public String sayHello(String name) {
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = RpcContext.getContext().getUrl();
        int port = url.getPort();
        String protocol = url.getProtocol();
        return protocol + ":" + port + "//Hello " + name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
