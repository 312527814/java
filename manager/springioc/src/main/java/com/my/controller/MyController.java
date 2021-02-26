package com.my.controller;

import com.my.pojp.Person;
import com.my.servlet.BaseServlet;
import com.my.servlet.MyServlet3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
//    @Resource
//    private List<BaseServlet> servlet;
//    @Resource
//    private Map<String, BaseServlet> ss;
//    @Resource
//    private MyServlet3 myServlet3;

//    @Resource
//    private  Person person;

    @Value("${a}")
    private  String name;

}
