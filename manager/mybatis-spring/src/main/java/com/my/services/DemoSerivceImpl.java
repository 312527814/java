package com.my.services;

import com.my.mapper.flowerMapper;
import com.my.pojo.flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoSerivceImpl implements  DemoSerivce {

    @Autowired
    private flowerMapper flowerMapper;
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void test() {
        flower flower = flowerMapper.selectById(10);

        flower flower2 = flowerMapper.selectById(10);
        com.my.pojo.flower flower1 = new flower();
        flower1.setPrice(2d);
        flower1.setName("222");
        flower1.setProduction("produce");
        flowerMapper.insert(flower1);

        int a=1/0;
        flower.toString();
    }
}
