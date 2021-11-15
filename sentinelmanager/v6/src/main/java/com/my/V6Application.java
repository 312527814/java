package com.my;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.eureka.EurekaDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class V6Application {

    public static void main(String[] args) {
        SpringApplication.run(V6Application.class, args);
    }

//    @Bean
    public EurekaDataSource<List<FlowRule>> eurekaDataSource() {

//        List<String> serviceUrls = EndpointUtils.getServiceUrlsFromConfig(eurekaClientConfig,
//                eurekaInstanceConfig.getMetadataMap().get("zone"), eurekaClientConfig.shouldPreferSameZoneEureka());

        List<String> serviceUrls = Arrays.asList("http://localhost:8761/eureka");

        EurekaDataSource<List<FlowRule>> eurekaDataSource = new EurekaDataSource("sentinel-app",
                "eurekaInstanceConfig.getInstanceId()", serviceUrls, "flowrules", new Converter<String, List<FlowRule>>() {
            @Override
            public List<FlowRule> convert(String o) {
                return JSON.parseObject(o, new TypeReference<List<FlowRule>>() {
                });
            }
        });

//        EurekaDataSource<List<FlowRule>> eurekaDataSource = new EurekaDataSource(eurekaInstanceConfig.getAppname(),
//                eurekaInstanceConfig.getInstanceId(), serviceUrls, "flowrules", new Converter<String, List<FlowRule>>() {
//            @Override
//            public List<FlowRule> convert(String o) {
//                return JSON.parseObject(o, new TypeReference<List<FlowRule>>() {
//                });
//            }
//        });

        FlowRuleManager.register2Property(eurekaDataSource.getProperty());
        return eurekaDataSource;
    }




}
