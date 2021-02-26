package com.my.controller;

import com.my.mapper.flowerMapper;
import com.my.pojo.flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class Test {

    @Autowired
    private flowerMapper flowerMapper;
    @GetMapping("alive")
    public String alive()  {
        flower flower = flowerMapper.selectById(10);
        return ":"+this.getClass().getClassLoader();
    }
}
