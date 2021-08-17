package com.my.controller;

import com.my.service.MyService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
    @Autowired
    private MyService myService;

    public MyController() {
        System.out.print("MyController。。。。。。。。。。。。");
    }

    private BeanFactory beanFactory;

    @GetMapping("/get")
    public String get() {
        String test = myService.test();
        System.out.println(test + "get。。。。。。。。。。。。");
        return "/index.jsp";
    }


}
