package com.my.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
