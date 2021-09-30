package com.my.controller;

import com.my.openfeign.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Controller
public class ConsumerController implements ApplicationContextAware {

    ApplicationContext applicationContext;
    @Resource
    private RestTemplate restTemplate;
    @Resource(name = "lbRestTemplate")
    private RestTemplate lbRestTemplate;

    @LoadBalanced
    @Autowired(required = false)
    private Test test;

    @Autowired
    private TestService testService;

    @GetMapping("consumer/get")
    public String get() {
        String host = "producer1";
        String forObject2 = restTemplate.getForObject("http://localhost:8084/api/check/web", String.class);

        String forObject = lbRestTemplate.getForObject("http://" + host + "/api/check/web", String.class);
        return forObject + "|" + forObject2;
    }

    @GetMapping("consumer/get2")
    public String get2() {
        String s = testService.checkWeb();
        return s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
