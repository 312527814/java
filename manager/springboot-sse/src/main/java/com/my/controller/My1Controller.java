package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My1Controller {


    @GetMapping("my/index")
    public String index() {


        return "hello index+";
    }


}
