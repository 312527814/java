package com.my.controller;


import com.my.service.IMyService;
import com.my.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AliveController {

    @Resource
    private IMyService myService;

    @GetMapping("alive")
    public String alive() {
        String alive = myService.alive();
        return alive;
    }


}
