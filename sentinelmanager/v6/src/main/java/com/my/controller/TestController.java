package com.my.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/check3/web4")
    public String alive3(@RequestParam("id") int id) {
        if (id == 2) {
            int a = 1 / 0;
        }
        return id + "";
    }


}
