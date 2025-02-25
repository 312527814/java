package com.my.controller;

import com.my.pojo.flower;
import com.my.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {

    @Autowired
    private MyService myService;

    @Autowired
    private com.my.mapper.flowerMapper flowerMapper;

    @GetMapping("alive")
    public String alive() {
        flower flower = flowerMapper.selectById(10);

        flower flower2 = flowerMapper.selectById(10);
        return ":" + this.getClass().getClassLoader();
    }

    @GetMapping("alive2")
    @Transactional
    public String test() {

        flowerMapper.updateById(1);

        flowerMapper.updateById(4);
        return "";
    }

    @GetMapping("alive3")
    public String test3() {

        flower flower2 = flowerMapper.selectById(10);
        String test = myService.test("ss");
        return "test";
    }
}
