package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {
    @GetMapping("check/web")
    public String alive()  {
        System.out.println("hello wold....");
        return "hello wold";
    }

    @PostMapping("check/web")
    public String alive2()  {
        System.out.println("hello wold....");
        return "hello wold post";
    }
}
