package com.my.aoptest.controller;


import com.my.aoptest.service.IMyService;
import com.you.YouTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AliveController {

    @Resource
    private YouTest youTest;

    @Resource
    private IMyService myService;

    @GetMapping("alive")
    public String alive() {
        String alive = myService.alive();
        return alive;
    }





}
