package com.my.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.my.inter.CloudProducterClient;
import com.my.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
@RequestMapping("test")
public class TestController {


    @Resource
    private CloudProducterClient cloudProducterClient;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() throws NacosException {
        String s = cloudProducterClient.get();
        return s;
    }


}