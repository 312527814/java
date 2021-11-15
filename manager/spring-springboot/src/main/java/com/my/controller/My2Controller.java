package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My2Controller {

    @Autowired
    private MyTest test;

    @GetMapping("my2/index")
    public String index() {


        return "hello index+";
    }

    @GetMapping("my2/index2")
    public String index2() {


        return "aaaaaaaaa";
    }


}
