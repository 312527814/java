package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class My1Controller {

    @Autowired
    private MyTest test;

    @GetMapping("my/index")
    public String index() {


        return "hello index+";
    }


}
