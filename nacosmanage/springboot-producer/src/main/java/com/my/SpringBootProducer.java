package com.my;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Hello world!
 */
@SpringBootApplication
public class SpringBootProducer {
    @NacosInjected
    private  NamingService namingService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducer.class, args);
    }

    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            Instance instance = new Instance();
            instance.setPort(8084);
            instance.setIp("10.108.2.121");
            namingService.registerInstance("123",instance);
            System.out.println(instance.toString());
        };
    }
}





