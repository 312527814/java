package com.my;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TestDemoImpl implements TestDemo {

    @Resource
    ProtocolConfig protocolConfig;
    @Resource
    Test test;

    @Override
    public String fun(String s) {
        return "fun你好：" + s + "." + new Date().getSeconds() + ":" + protocolConfig.getPort();
    }

    @Override
    public String fun2(String s) {
        return "fun2你好：" + s + "." + new Date().getSeconds();
    }
}
