package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class Index {
    @GetMapping("index")
    public  String  index(){
        return "hello index";
    }
}
