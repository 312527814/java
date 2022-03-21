package com.my.service;

import com.my.pojo.flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年03月14⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
@Service
public class MyService {

    @Autowired
    private com.my.mapper.flowerMapper flowerMapper;
    public String test(String s){
        flower flower = flowerMapper.selectById(10);
        System.out.println(s+"....");
        tt();
        return "test "+s;
    }

    public void  tt(){
        int a=1/0;
    }
}
