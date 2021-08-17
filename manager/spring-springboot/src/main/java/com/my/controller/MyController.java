package com.my.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
public class MyController {

    int i = 0;
    @Resource
    Environment environment;

    @GetMapping("index")
    public String index() {

        System.out.println("currentTheard=>" + Thread.currentThread().getName());
        String port = environment.getProperty("server.port");
        return "hello index+" + port;
    }
}
