package com.my.controller;

import com.my.MybatisSpringboot;
import com.my.mapper.flowerMapper;
import com.my.pojo.flower;
import com.my.service.MyService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年03月14⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisSpringboot.class)
public class TestControllerTest {

    @MockBean
    //@Spy
    private com.my.mapper.flowerMapper flowerMapper;


    @Resource
    private  TestController controller;

    @Before
    public void  before(){
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void  alive(){
        flower flower = new flower();
        flower.setId(1);
        when(flowerMapper.selectById(anyInt())).thenReturn(flower);
        String alive = controller.alive();
    }

}