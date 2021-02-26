package com.my.v4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {


    @GetMapping("/check/web4")
    public String alive() {
        return "successweb4";
    }

    @GetMapping("/check2/web4")
    public String alive2() {
        return "alive2successweb4";
    }



}
