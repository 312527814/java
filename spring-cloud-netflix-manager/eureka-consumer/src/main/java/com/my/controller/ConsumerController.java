package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Controller
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;
    @Resource(name = "lbRestTemplate")
    private RestTemplate lbRestTemplate;

    @GetMapping("consumer/get")
    public String get() {
        String host = "producer";
        String forObject = lbRestTemplate.getForObject("http://" + host + "/check/web", String.class);
        String forObject2 = restTemplate.getForObject("http://localhost:8082/check/web", String.class);
        return forObject + "|" + forObject2;
    }
}
